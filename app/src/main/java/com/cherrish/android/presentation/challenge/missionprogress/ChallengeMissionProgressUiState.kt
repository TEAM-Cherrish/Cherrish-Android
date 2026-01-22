package com.cherrish.android.presentation.challenge.missionprogress

import androidx.compose.runtime.Immutable
import com.cherrish.android.presentation.challenge.missionprogress.model.ChallengeRoutineUiModel
import kotlinx.collections.immutable.ImmutableList

@Immutable
data class ChallengeMissionProgressUiState(
    val challengeId: Long,
    val challengeName: String,
    val currentDay: Int,
    val cherryType: CherryType,
    val remainingCount: Int,
    val progressPercentage: Int,
    val routines: ImmutableList<ChallengeRoutineUiModel>,
    val remainingGuideText: String,
    val completeButtonText: String

)
