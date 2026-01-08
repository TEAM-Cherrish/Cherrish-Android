package com.cherrish.android.presentation.calendar.model

import androidx.compose.runtime.Immutable
import java.time.LocalDate

@Immutable
sealed interface CalendarDay {

    @Immutable
    data object Empty : CalendarDay

    @Immutable
    data class Date(
        val date: LocalDate,
        val procedureCount: Int = 0,
        val downtimeStatus: DownTimeStatus = DownTimeStatus.NONE
    ) : CalendarDay
}
