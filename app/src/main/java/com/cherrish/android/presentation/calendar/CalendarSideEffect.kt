package com.cherrish.android.presentation.calendar

sealed interface CalendarSideEffect {
    data object NavigateToProcedure : CalendarSideEffect
}
