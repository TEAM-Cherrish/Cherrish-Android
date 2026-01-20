package com.cherrish.android.data.model

import com.cherrish.android.core.util.toKoreanName
import com.cherrish.android.data.remote.dto.response.HomeResponseDto
import com.cherrish.android.data.remote.dto.response.RecentProcedureDto
import com.cherrish.android.data.remote.dto.response.UpcomingProcedureDto
import java.time.DayOfWeek
import java.time.LocalDate

data class HomeResponseModel(
    val date: String,
    val dayOfWeek: String,
    val challengeName: String?,
    val cherryLevel: Int,
    val challengeRate: Int,
    val recentProcedures: List<RecentProcedureModel>,
    val upcomingProcedures: List<UpcomingProcedureModel>
)

data class RecentProcedureModel(
    val procedureName: String,
    val daysSince: Int,
    val downtimePhase: String
)

data class UpcomingProcedureModel(
    val upcomingPlanDate: LocalDate,
    val procedureName: String,
    val procedureCount: Int,
    val dDay: Int
)

fun HomeResponseDto.toModel() = HomeResponseModel(
    date = this.date,
    dayOfWeek = this.dayOfWeek,
    challengeName = this.challengeName,
    cherryLevel = this.cherryLevel,
    challengeRate = this.challengeRate,
    recentProcedures = this.recentProcedures.map { it.toModel() },
    upcomingProcedures = this.upcomingProcedures.mapNotNull { it.toModelOrNull() }
)

fun RecentProcedureDto.toModel() = RecentProcedureModel(
    procedureName = this.name,
    daysSince = this.daysSince,
    downtimePhase = this.currentPhase
)

fun UpcomingProcedureDto.toModelOrNull(): UpcomingProcedureModel? {
    val date = this.date.trim()
    val localDate = runCatching { LocalDate.parse(date) }.getOrNull() ?: return null

    return UpcomingProcedureModel(
        upcomingPlanDate = localDate,
        procedureName = this.name,
        procedureCount = this.count,
        dDay = this.dDay
    )
}

fun HomeResponseModel.toTodayDateString(): String {
    val localDate = LocalDate.parse(date)
    val dayKor = DayOfWeek.valueOf(dayOfWeek).toKoreanName()
    return "${localDate.year}년 ${localDate.monthValue}월 ${localDate.dayOfMonth}일 ($dayKor)"
}
