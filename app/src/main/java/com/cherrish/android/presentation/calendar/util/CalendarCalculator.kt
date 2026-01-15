package com.cherrish.android.presentation.calendar.util

import java.time.DayOfWeek
import java.time.LocalDateTime

fun DayOfWeek.daysUntil(other: DayOfWeek) = (other.value - value + 7) % 7

fun formatProcedureDay(scheduledAt: String): String {
    val dateTime = LocalDateTime.parse(scheduledAt)
    val month = dateTime.month.value
    val day = dateTime.dayOfMonth
    val dayOfWeek = dateTime.dayOfWeek.toKoreanName()
    return "${month}월 ${day}일 $dayOfWeek"
}

fun DayOfWeek.toKoreanName(): String = when (this) {
    DayOfWeek.MONDAY -> "월요일"
    DayOfWeek.TUESDAY -> "화요일"
    DayOfWeek.WEDNESDAY -> "수요일"
    DayOfWeek.THURSDAY -> "목요일"
    DayOfWeek.FRIDAY -> "금요일"
    DayOfWeek.SATURDAY -> "토요일"
    DayOfWeek.SUNDAY -> "일요일"
}
