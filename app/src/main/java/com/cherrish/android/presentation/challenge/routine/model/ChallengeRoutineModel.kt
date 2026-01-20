package com.cherrish.android.presentation.challenge.routine.model

import androidx.compose.runtime.Immutable

@Immutable
data class ChallengeRoutineModel(
    val id: Long,
    val routine: String,
    val isSelected: Boolean = false
)
