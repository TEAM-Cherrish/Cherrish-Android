package com.cherrish.android.presentation.challenge.missionprogress

import com.cherrish.android.presentation.challenge.missionprogress.model.ChallengeInfoModel
import com.cherrish.android.presentation.challenge.missionprogress.model.TemporaryRoutineModel

data class ChallengeMissionProgressUiState(
    val challenge : ChallengeInfoModel,
    val currentDay: Int,
    val routines : List<RoutineItemUiState>,
    val currentStep : Int
){

}

data class RoutineItemUiState(
    val routine: TemporaryRoutineModel,
    val isCompleted: Boolean
)
