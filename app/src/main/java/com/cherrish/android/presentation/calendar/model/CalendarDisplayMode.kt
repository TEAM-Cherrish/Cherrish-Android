package com.cherrish.android.presentation.calendar.model

import java.time.LocalDate
import kotlinx.collections.immutable.persistentMapOf

sealed interface CalendarDisplayMode {
    data class Normal(
        val procedureCountByDate: Map<LocalDate, Int> = persistentMapOf()
    ) : CalendarDisplayMode

    data class Downtime(
        val downtimeByDate: Map<LocalDate, DownTimeStatus> = persistentMapOf(),
        val selectedProcedureId: Long? = null
    ) : CalendarDisplayMode
}
