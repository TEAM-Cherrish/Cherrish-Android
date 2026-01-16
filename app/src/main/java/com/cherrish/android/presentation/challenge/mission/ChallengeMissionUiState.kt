package com.cherrish.android.presentation.challenge.mission

import com.cherrish.android.presentation.challenge.mission.model.ChallengeRoutineMissionModel

data class ChallengeMissionUiState(
    val mission: ChallengeRoutineMissionModel,
    val isSelected: Boolean = false
)

data class ChallengeMissionScreenUiState(
    val missions: List<ChallengeMissionUiState>
) {
    val selectedMissions: List<ChallengeRoutineMissionModel>
        get() = missions.filter { it.isSelected }.map { it.mission }
}
