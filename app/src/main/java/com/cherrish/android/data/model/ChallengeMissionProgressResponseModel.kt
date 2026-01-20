package com.cherrish.android.data.model

import com.cherrish.android.data.remote.dto.response.ChallengeMissionProgressResponseDto
import com.cherrish.android.data.remote.dto.response.ChallengeRoutineResponseDto

data class ChallengeMissionProgressResponseModel(
    val challengeId: Long,
    val title: String,
    val currentDay: Int,
    val progressPercentage: Int,
    val cherryLevel: Int,
    val cherryLevelName: String,
    val progressToNextLevel: Double,
    val remainingRoutinesToNextLevel: Int,
    val todayRoutines: List<ChallengeRoutineResponseModel>,
    val cheeringMessage: String
)

data class ChallengeRoutineResponseModel(
    val routineId: Long,
    val routineName: String,
    val scheduledDate: String,
    val isCompleted: Boolean
)

fun ChallengeMissionProgressResponseDto.toModel() = ChallengeMissionProgressResponseModel(
    challengeId = this.challengeId,
    title = this.title,
    currentDay = this.currentDay,
    progressPercentage = this.progressPercentage,
    cherryLevel = this.cherryLevel,
    cherryLevelName = this.cherryLevelName,
    progressToNextLevel = this.progressToNextLevel,
    remainingRoutinesToNextLevel = this.remainingRoutinesToNextLevel,
    todayRoutines = this.todayRoutines.map { it.toModel() },
    cheeringMessage = this.cheeringMessage
)

fun ChallengeRoutineResponseDto.toModel() = ChallengeRoutineResponseModel(
    routineId = this.routineId,
    routineName = this.name,
    scheduledDate = this.scheduledDate,
    isCompleted = this.isComplete
)
