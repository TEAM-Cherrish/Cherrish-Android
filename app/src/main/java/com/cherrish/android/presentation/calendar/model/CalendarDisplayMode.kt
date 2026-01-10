package com.cherrish.android.presentation.calendar.model

import androidx.compose.runtime.Immutable
import java.time.LocalDate

sealed interface CalendarDisplayMode {
    @Immutable
    data class Normal(
        val procedureCountByDate: Map<LocalDate, Int>
    ) : CalendarDisplayMode

    @Immutable
    data class Downtime(
        val downtimeByDate: Map<LocalDate, DownTimeStatus>,
        val selectedProcedureId: Long? = null
    ) : CalendarDisplayMode
}
