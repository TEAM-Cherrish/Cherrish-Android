package com.cherrish.android.presentation.calendar.util

import java.time.DayOfWeek

fun DayOfWeek.daysUntil(other: DayOfWeek) = (other.value - value + 7) % 7
