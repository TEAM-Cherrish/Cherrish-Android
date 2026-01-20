package com.cherrish.android.presentation.calendar

import java.time.LocalDate

sealed interface CalendarSideEffect {
    data class NavigateToProcedure(val startDate: LocalDate) : CalendarSideEffect
}
