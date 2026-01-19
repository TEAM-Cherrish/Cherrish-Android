package com.cherrish.android.presentation.challenge.missionprogress.model

import androidx.compose.runtime.Immutable

@Immutable
data class ChallengeInfoModel(
    val id: Long,
    val challengeTitle: String,
    val challengeTotalDays: Int
)
