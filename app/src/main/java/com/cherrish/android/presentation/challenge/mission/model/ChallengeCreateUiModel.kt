package com.cherrish.android.presentation.challenge.mission.model

import com.cherrish.android.data.model.ChallengeCreateResponseModel
import com.cherrish.android.data.model.ChallengeRoutineResponseModel

data class ChallengeCreateUiModel(
    val challengeId: Long,
    val title: String,
    val totalDays: Int,
    val startDate: String,
    val endDate: String,
    val totalRoutineCount: Int,
    val routines: List<ChallengeRoutineUiModel>
)

data class ChallengeRoutineUiModel(
    val routineId: Long,
    val name: String,
    val scheduledDate: String,
    val isComplete: Boolean
)

fun ChallengeCreateResponseModel.toUiModel() = ChallengeCreateUiModel(
    challengeId = challengeId,
    title = title,
    totalDays = totalDays,
    startDate = startDate,
    endDate = endDate,
    totalRoutineCount = totalRoutineCount,
    routines = routines.map { it.toUiModel() }
)

fun ChallengeRoutineResponseModel.toUiModel() = ChallengeRoutineUiModel(
    routineId = routineId,
    name = routineName,
    scheduledDate = scheduledDate,
    isComplete = isCompleted
)
