package com.cherrish.android.data.model

import com.cherrish.android.data.remote.dto.response.ChallengeRoutineCompleteResponseDto

data class ChallengeRoutineCompleteResponseModel(
    val routineId: Long,
    val name: String,
    val isComplete: Boolean
)

fun ChallengeRoutineCompleteResponseDto.toModel() = ChallengeRoutineCompleteResponseModel(
    routineId = this.routineId,
    name = this.name,
    isComplete = this.isComplete
)
