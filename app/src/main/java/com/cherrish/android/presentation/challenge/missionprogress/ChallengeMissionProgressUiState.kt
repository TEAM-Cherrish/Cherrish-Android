package com.cherrish.android.presentation.challenge.missionprogress

import androidx.compose.runtime.Immutable
import com.cherrish.android.data.model.ChallengeRoutineResponseModel
import com.cherrish.android.presentation.challenge.missionprogress.model.ChallengeInfoModel
import kotlinx.collections.immutable.ImmutableList

@Immutable
data class ChallengeMissionProgressUiState(
    val challenge: ChallengeInfoModel,
    val currentDay: Int,
    val cherryType: CherryType,
    val remainingCount: Int,
    val progressPercentage: Int,
    val routines: ImmutableList<ChallengeRoutineResponseModel>

) {
    val hasCompletedAny: Boolean
        get() = routines.any { it.isCompleted }
}
