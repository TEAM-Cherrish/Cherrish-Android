package com.cherrish.android.presentation.challenge.loading

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.cherrish.android.core.common.extension.onLogFailure
import com.cherrish.android.core.common.state.UiState
import com.cherrish.android.data.repository.ChallengeRepository
import com.cherrish.android.presentation.challenge.navigation.ChallengeLoading
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ChallengeLoadingViewModel @Inject constructor(
    private val challengeRepository: ChallengeRepository,
    saveStatedHandle: SavedStateHandle
) : ViewModel() {
    private val routineIdArg = saveStatedHandle.toRoute<ChallengeLoading>().routineId

    private val _uiState =
        MutableStateFlow<UiState<ChallengeLoadingUiState>>(UiState.Loading)
    val uiState: StateFlow<UiState<ChallengeLoadingUiState>> = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<ChallengeLoadingSideEffect>()
    val sideEffect: SharedFlow<ChallengeLoadingSideEffect> = _sideEffect.asSharedFlow()

    fun postAiRecommendations() {
        viewModelScope.launch {
            challengeRepository.postAiRecommendations(
                homecareRoutineId = routineIdArg
            ).onSuccess { response ->
                _uiState.update {
                    UiState.Success(
                        ChallengeLoadingUiState(
                            routines = response.routines.toPersistentList()
                        )
                    )
                }

                delay(1000)

                _sideEffect.emit(
                    ChallengeLoadingSideEffect.NavigateToChallengeMission(
                        routineId = routineIdArg,
                        routines = response.routines
                    )
                )
            }.onLogFailure { }
        }
    }
}
