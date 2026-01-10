package com.cherrish.android.presentation.calendar.model

import androidx.compose.runtime.Immutable
import java.time.YearMonth

@Immutable
data class CalendarMonth(
    val yearMonth: YearMonth,
    val weekDays: List<List<CalendarDay>>
)
