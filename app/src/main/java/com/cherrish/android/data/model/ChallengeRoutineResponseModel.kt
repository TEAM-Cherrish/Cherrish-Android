package com.cherrish.android.data.model

import androidx.compose.runtime.Immutable

@Immutable
data class ChallengeRoutineResponseModel(
    val id: Long,
    val routine: String,
    val isSelected: Boolean
)

fun ChallengeRoutineResponseModel.toDto() = ChallengeRoutineResponseModel(
    id = this.id,
    routine = this.routine,
    isSelected = this.isSelected
)
