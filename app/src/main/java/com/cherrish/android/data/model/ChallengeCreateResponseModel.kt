package com.cherrish.android.data.model

import com.cherrish.android.data.remote.dto.response.ChallengeCreateDataDto
import com.cherrish.android.data.remote.dto.response.ChallengeRoutineDto

data class ChallengeCreateResponseModel(
    val challengeId: Long,
    val title: String,
    val totalDays: Int,
    val startDate: String,
    val endDate: String,
    val totalRoutineCount: Int,
    val routines: List<ChallengeRoutineResponseModel>
)

fun ChallengeCreateDataDto.toModel() = ChallengeCreateResponseModel(
    challengeId = challengeId,
    title = title,
    totalDays = totalDays,
    startDate = startDate,
    endDate = endDate,
    totalRoutineCount = totalRoutineCount,
    routines = routines.map { it.toModel() }
)

fun ChallengeRoutineDto.toModel() = ChallengeRoutineResponseModel(
    routineId = routineId,
    routineName = name,
    scheduledDate = scheduledDate,
    isCompleted = isComplete
)
