package com.cherrish.android.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeResponseDto(
    @SerialName("date")
    val date: String,
    @SerialName("dayOfWeek")
    val dayOfWeek: String,
    @SerialName("challengeName")
    val challengeName: String?,
    @SerialName("cherryLevel")
    val cherryLevel: Int,
    @SerialName("challengeRate")
    val challengeRate: Int,
    @SerialName("recentProcedures")
    val recentProcedures: List<RecentProcedureDto>,
    @SerialName("upcomingProcedures")
    val upcomingProcedures: List<UpcomingProcedureDto>
)

@Serializable
data class RecentProcedureDto(
    @SerialName("name")
    val name: String,
    @SerialName("daysSince")
    val daysSince: Int,
    @SerialName("currentPhase")
    val currentPhase: String
)

@Serializable
data class UpcomingProcedureDto(
    @SerialName("date")
    val date: String,
    @SerialName("name")
    val name: String,
    @SerialName("count")
    val count: Int,
    @SerialName("dDay")
    val dDay: Int
)
