package com.cherrish.android.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChallengesHomecareRoutinesResponseDto(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val description: String?,
)

@Serializable
data class ChallengesAiRecommendResponseDto(
    @SerialName("data")
    val data: List<RoutinesDto>
)

@Serializable
data class RoutinesDto(
    @SerialName("routine")
    val routine : String
)

@Serializable
data class ChallengeMissionProgressDataDto(
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

    @SerialName("todayRoutines")
    val todayRoutines: List<TodayRoutineDto>,

    @SerialName("cheeringMessage")
    val cheeringMessage: String
)

@Serializable
data class TodayRoutineDto(
    @SerialName("routineId")
    val routineId: Long,

    @SerialName("name")
    val name: String,

    @SerialName("scheduledDate")
    val scheduledDate: String,

    @SerialName("isComplete")
    val isComplete: Boolean
)
