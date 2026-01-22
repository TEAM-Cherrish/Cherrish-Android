package com.cherrish.android.presentation.challenge.missionprogress

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cherrish.android.core.common.extension.onLogFailure
import com.cherrish.android.core.common.extension.updateSuccess
import com.cherrish.android.core.common.state.UiState
import com.cherrish.android.data.model.ChallengeMissionProgressResponseModel
import com.cherrish.android.data.repository.ChallengeMissionProgressRepository
import com.cherrish.android.presentation.challenge.missionprogress.model.ChallengeRoutineUiModel
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
class ChallengeMissionProgressViewModel @Inject constructor(
    private val challengeMissionProgressRepository: ChallengeMissionProgressRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<UiState<ChallengeMissionProgressUiState>>(UiState.Loading)
    val uiState: StateFlow<UiState<ChallengeMissionProgressUiState>> =
        _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<ChallengeMissionProgressSideEffect>()
    val sideEffect: SharedFlow<ChallengeMissionProgressSideEffect> = _sideEffect.asSharedFlow()

    init {
        loadMissions()
    }

    private fun loadMissions() {
        viewModelScope.launch {
            _uiState.update { UiState.Loading }

            challengeMissionProgressRepository.getChallengeMissions().onSuccess { response ->
                _uiState.update {
                    UiState.Success(response.toUiState())
                }
            }.onLogFailure {}
        }
    }

    fun onTodoClick(id: Long) {
        _uiState.updateSuccess { state ->
            state.copy(
                routines = state.routines.map { routine ->
                    if (routine.routineId == id) {
                        routine.copy(isCompleted = !routine.isCompleted)
                    } else {
                        routine
                    }
                }.toPersistentList()
            )
        }
        viewModelScope.launch {
            challengeMissionProgressRepository
                .patchChallengeRoutinesComplete(routineId = id)
                .onLogFailure { }
        }
    }

    private var isPostingAdvanceDay = false

    fun onCompletedTodayClick() {
        if (isPostingAdvanceDay) return

        isPostingAdvanceDay = true
        viewModelScope.launch {
            try {
                challengeMissionProgressRepository
                    .postChallengeAdvanceDay()
                    .onSuccess { response ->
                        _uiState.update {
                            UiState.Success(response.toUiState())
                        }
                    }
                    .onLogFailure { e ->
                        if (e is retrofit2.HttpException && e.code() == 404) {
                            _sideEffect.emit(
                                ChallengeMissionProgressSideEffect.NavigateToChallengeStart
                            )
                        }
                    }
            } finally {
                isPostingAdvanceDay = false
            }
        }
    }
}

private fun ChallengeMissionProgressResponseModel.toUiState(): ChallengeMissionProgressUiState {
    val cherryType = CherryType.entries.first { it.step == cherryLevel }
    val isMaxLevel = cherryType == CherryType.KKUKKU
    val remainingText = if (isMaxLevel) {
        "챌린지 완료까지 ${remainingRoutinesToNextLevel}개의 미션을 수행해야 해요!"
    } else {
        "체리가 크려면 ${remainingRoutinesToNextLevel}개의 미션을 수행해야 해요!"
    }
    val completeButtonText = if (isMaxLevel) {
        "챌린지 종료하기"
    } else {
        "오늘 미션 종료하기"
    }

    return ChallengeMissionProgressUiState(
        challengeId = challengeId,
        challengeName = title,
        currentDay = currentDay,
        progressPercentage = progressPercentage,
        cherryType = cherryType,
        remainingCount = remainingRoutinesToNextLevel,
        routines = todayRoutines.map { routine ->
            ChallengeRoutineUiModel(
                routineId = routine.routineId,
                routineName = routine.routineName,
                isCompleted = routine.isCompleted
            )
        }.toPersistentList(),
        remainingGuideText = remainingText,
        completeButtonText = completeButtonText
    )
}

sealed interface ChallengeMissionProgressSideEffect {
    data object NavigateToChallengeStart : ChallengeMissionProgressSideEffect
}
