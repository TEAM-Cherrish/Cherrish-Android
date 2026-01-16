package com.cherrish.android.presentation.challenge.missionprogress

import com.cherrish.android.presentation.challenge.missionprogress.model.ChallengeInfoModel
import com.cherrish.android.presentation.challenge.missionprogress.model.TemporaryRoutineModel

data class ChallengeMissionProgressUiState(
    val challenge: ChallengeInfoModel,
    val currentDay: Int,
    val routines: List<RoutineItemUiState>,
    val cherryType: CherryType,
    val remainingCount: Int
) {
    val isCompleteButtonEnabled: Boolean
        get() = routines.any { it.isCompleted }
}

data class RoutineItemUiState(
    val routine: TemporaryRoutineModel,
    val isCompleted: Boolean
)
