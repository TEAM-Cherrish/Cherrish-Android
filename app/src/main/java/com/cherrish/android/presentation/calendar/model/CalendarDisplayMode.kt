package com.cherrish.android.presentation.calendar.model

import java.time.LocalDate

sealed interface CalendarDisplayMode {
    data object Normal : CalendarDisplayMode

    data class ShowDowntime(
        val downtimeByDate: Map<LocalDate, DownTimeStatus>
    ) : CalendarDisplayMode
}
