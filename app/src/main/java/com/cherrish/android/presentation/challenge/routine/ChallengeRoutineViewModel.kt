package com.cherrish.android.presentation.challenge.routine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cherrish.android.core.common.extension.updateSuccess
import com.cherrish.android.core.common.state.UiState
import com.cherrish.android.data.repository.ChallengeRepository
import com.cherrish.android.presentation.challenge.ChallengeSideEffect
import com.cherrish.android.presentation.challenge.routine.model.ChallengeRoutineModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
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
        _uiState.updateSuccess {
            ChallengeRoutineUiState(
                routines = persistentListOf(
                    ChallengeRoutineModel(id = 1L, routine = "피부 컨디션"),
                    ChallengeRoutineModel(id = 2L, routine = "생활 습관"),
                    ChallengeRoutineModel(id = 3L, routine = "체형 관리"),
                    ChallengeRoutineModel(id = 4L, routine = "웰니스 · 마음챙김")
                )
            )
        }
    }

    fun onRoutineClick(id: Long) {
        _uiState.updateSuccess { state ->
            state.copy(
                routines = state.routines.map {
                    it.copy(isSelected = it.id == id)
                }.toPersistentList()
            )
        }
    }

    fun onNextClick() {
        val selectedId = 1L // 하드코딩 추후 UI 받아온 거 수정
        viewModelScope.launch {
            challengeRepository
                .postAiRecommendations(
                    homecareRoutineId = selectedId.toInt()
                )
                .onSuccess {
                    _sideEffect.emit(
                        ChallengeSideEffect.NavigateToMission
                    )
                }
                .onFailure {
                }
        }
    }

    fun onBackClick() {
    }
    fun onCloseClick() {}

    private companion object {
        const val TAG = "ChallengeRoutineViewModel"
    }
}
