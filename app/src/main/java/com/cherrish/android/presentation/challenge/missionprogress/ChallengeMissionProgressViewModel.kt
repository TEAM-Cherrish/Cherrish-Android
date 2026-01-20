package com.cherrish.android.presentation.challenge.missionprogress

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cherrish.android.core.common.extension.onLogFailure
import com.cherrish.android.core.common.extension.updateSuccess
import com.cherrish.android.core.common.state.UiState
import com.cherrish.android.data.repository.ChallengeMissionProgressRepository
import com.cherrish.android.presentation.challenge.missionprogress.model.ChallengeRoutineUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    init {
        loadMissions()
    }

    private fun loadMissions() {
        viewModelScope.launch {
            _uiState.update { UiState.Loading }

            challengeMissionProgressRepository.getChallengeMissions().onSuccess { response ->
                _uiState.update {
                    UiState.Success(
                        ChallengeMissionProgressUiState(
                            challengeId = response.challengeId,
                            challengeName = response.title,
                            currentDay = response.currentDay,
                            progressPercentage = response.progressPercentage,
                            cherryType = CherryType.entries.first {
                                it.step == response.cherryLevel
                            },
                            remainingCount = response.remainingRoutinesToNextLevel,
                            routines = response.todayRoutines.map { routine ->
                                ChallengeRoutineUiModel(
                                    routineId = routine.routineId,
                                    routineName = routine.routineName,
                                    isCompleted = routine.isCompleted
                                )
                            }
                                .toPersistentList()
                        )
                    )
                }
            }.onLogFailure {}
        }
    }

    fun onTodoClick(id: Long) {
        _uiState.updateSuccess { state ->
            state.copy(
                routines = state.routines.map {
                    if (it.routineId == id) {
                        it.copy(isCompleted = !it.isCompleted)
                    } else {
                        it
                    }
                }.toPersistentList()
            )
        }
    }

    fun onCompletedTodayClick() {
        val state = _uiState.value
        if (state !is UiState.Success) return
        if (!state.data.hasCompletedAny) return
    }
}
