package com.cherrish.android.presentation.challenge.missionprogress

import androidx.compose.runtime.Immutable
import com.cherrish.android.presentation.challenge.missionprogress.model.ChallengeInfoModel
import com.cherrish.android.presentation.challenge.missionprogress.model.DailyTodoRoutineModel

@Immutable
data class ChallengeMissionProgressUiState(
    val challenge: ChallengeInfoModel,
    val currentDay: Int,
    val cherryType: CherryType,
    val remainingCount: Int,
    val routines: List<DailyTodoRoutineModel>
)
