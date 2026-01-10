package com.cherrish.android.presentation.calendar.model

import java.time.LocalDate

sealed interface CalendarDisplayMode {
    data class Normal(
        val procedureCountByDate: Map<LocalDate, Int>
    ) : CalendarDisplayMode

    data class Downtime(
        val downtimeByDate: Map<LocalDate, DownTimeStatus>
    ) : CalendarDisplayMode
}
