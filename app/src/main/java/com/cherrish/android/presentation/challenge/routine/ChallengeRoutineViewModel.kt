package com.cherrish.android.presentation.challenge.routine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cherrish.android.core.common.extension.updateSuccess
import com.cherrish.android.core.common.state.UiState
import com.cherrish.android.data.repository.ChallengeRepository
import com.cherrish.android.presentation.challenge.ChallengeSideEffect
import com.cherrish.android.presentation.challenge.routine.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ChallengeRoutineViewModel @Inject constructor(
    private val challengeRepository: ChallengeRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<UiState<ChallengeRoutineUiState>>(UiState.Loading)
    val uiState: StateFlow<UiState<ChallengeRoutineUiState>> =
        _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<ChallengeSideEffect>()
    val sideEffect: SharedFlow<ChallengeSideEffect> =
        _sideEffect.asSharedFlow()

    init {
        loadRoutines()
    }

    private fun loadRoutines() {
        viewModelScope.launch {
            challengeRepository.getChallengeRoutineData()
                .onSuccess { response ->
                    val routines = response
                        .map { it.toUiModel() }
                        .toPersistentList()

                    _uiState.update {
                        UiState.Success(
                            ChallengeRoutineUiState(
                                routines = routines
                            )
                        )
                    }
                }
        }
    }

    fun onRoutineClick(id: Int) {
        _uiState.updateSuccess { state ->
            state.copy(
                routines = state.routines.map {
                    it.copy(isSelected = it.id == id)
                }.toPersistentList(),
                selectedRoutineId = id
            )
        }
    }

    fun onNextClick() {
        val currentState = _uiState.value
        if (currentState is UiState.Success) {
            currentState.data.selectedRoutineId?.let { id ->
                viewModelScope.launch {
                    _sideEffect.emit(
                        ChallengeSideEffect.NavigateToChallengeLoading(
                            routineId = id
                        )
                    )
                }
            }
        }
    }
}
