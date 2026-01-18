package com.cherrish.android.presentation.challenge.mission.model

import androidx.compose.runtime.Immutable

@Immutable
data class ChallengeMissionModel(
    val id: Int,
    val missionContent: String,
    val isSelected: Boolean
)
