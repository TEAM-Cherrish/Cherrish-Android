package com.cherrish.android.presentation.challenge.missionprogress

import com.cherrish.android.presentation.challenge.missionprogress.model.ChallengeInfoModel
import com.cherrish.android.presentation.challenge.missionprogress.model.DailyTodoRoutineModel

data class ChallengeMissionProgressUiState(
    val challenge: ChallengeInfoModel,
    val currentDay: Int,
    val cherryType: CherryType,
    val remainingCount: Int,
    val routines: List<DailyTodoRoutineModel>
)
