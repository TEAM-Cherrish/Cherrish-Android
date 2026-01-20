package com.cherrish.android.core.util

import java.time.DayOfWeek
import java.time.LocalDateTime

fun DayOfWeek.daysUntil(other: DayOfWeek) = (other.value - value + 7) % 7

fun formatProcedureDay(scheduledAt: String): String {
    val dateTime = LocalDateTime.parse(scheduledAt)
    val month = dateTime.month.value
    val day = dateTime.dayOfMonth
    val dayOfWeek = dateTime.dayOfWeek.toKoreanName()
    return "${month}월 ${day}일 ${dayOfWeek}요일"
}

fun DayOfWeek.toKoreanName(): String = when (this) {
    DayOfWeek.MONDAY -> "월"
    DayOfWeek.TUESDAY -> "화"
    DayOfWeek.WEDNESDAY -> "수"
    DayOfWeek.THURSDAY -> "목"
    DayOfWeek.FRIDAY -> "금"
    DayOfWeek.SATURDAY -> "토"
    DayOfWeek.SUNDAY -> "일"
}
