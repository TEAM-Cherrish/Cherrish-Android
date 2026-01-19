package com.cherrish.android.presentation.challenge.missionprogress

import androidx.lifecycle.ViewModel
import com.cherrish.android.core.common.extension.updateSuccess
import com.cherrish.android.core.common.state.UiState
import com.cherrish.android.presentation.challenge.missionprogress.model.ChallengeInfoModel
import com.cherrish.android.presentation.challenge.missionprogress.model.DailyTodoRoutineModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class ChallengeMissionProgressViewModel @Inject constructor() : ViewModel() {

    private val _uiState =
        MutableStateFlow<UiState<ChallengeMissionProgressUiState>>(UiState.Loading)
    val uiState: StateFlow<UiState<ChallengeMissionProgressUiState>> =
        _uiState.asStateFlow()

    init {
        loadMissions()
    }

    private fun loadMissions() {
        _uiState.value = UiState.Success(
            ChallengeMissionProgressUiState(
                challenge = ChallengeInfoModel(
                    id = 1L,
                    challengeTitle = "피부 컨디션 챌린지",
                    challengeTotalDays = 8
                ),
                currentDay = 8,
                cherryType = CherryType.BBANGBBANG,
                remainingCount = 3,
                progressPercentage = 25,
                routines = persistentListOf(
                    DailyTodoRoutineModel(1L, "선크림 바르기", true),
                    DailyTodoRoutineModel(2L, "진정 토너+세럼", false),
                    DailyTodoRoutineModel(3L, "진정 토너+세럼", false),
                    DailyTodoRoutineModel(4L, "진정 토너+세럼", false)
                )
            )
        )
    }
    fun onTodoClick(id: Long) {
        _uiState.updateSuccess { state ->
            state.copy(
                routines = state.routines.map {
                    if (it.id == id) {
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
