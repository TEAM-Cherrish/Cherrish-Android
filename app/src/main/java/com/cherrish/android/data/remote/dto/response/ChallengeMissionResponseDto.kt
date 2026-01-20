package com.cherrish.android.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChallengeMissionResponseDto(
    @SerialName("challengeId")
    val challengeId: Long,
    @SerialName("title")
    val title: String,
    @SerialName("currentDay")
    val currentDay: Int,
    @SerialName("progressPercentage")
    val progressPercentage: Int,
    @SerialName("cherryLevel")
    val cherryLevel: Int,
    @SerialName("cherryLevelName")
    val cherryLevelName: String,
    @SerialName("progressToNextLevel")
    val progressToNextLevel: Double,
    @SerialName("remainingRoutinesToNextLevel")
    val remainingRoutinesToNextLevel: Int,
    @SerialName("routines")
    val routines: List<ChallengeRoutineResponseDto>,
    @SerialName("cheeringMessage")
    val cheeringMessage: String
)

@Serializable
data class ChallengeRoutineResponseDto(
    @SerialName("routineId")
    val routineId: Long,
    @SerialName("name")
    val name: String,
    @SerialName("scheduledDate")
    val scheduledDate: String,
    @SerialName("isComplete")
    val isComplete: Boolean
)
