package com.cherrish.android.presentation.challenge.mission

import com.cherrish.android.presentation.challenge.mission.model.ChallengeMissionModel

data class ChallengeMissionUiState(
    val mission: ChallengeMissionModel,
    val isSelected: Boolean = false
)

data class ChallengeMissionScreenUiState(
    val missions: List<ChallengeMissionUiState>
) {
    val selectedMissions: List<ChallengeMissionModel>
        get() = missions.filter { it.isSelected }.map { it.mission }
}
