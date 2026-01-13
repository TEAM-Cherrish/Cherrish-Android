package com.cherrish.android.presentation.calendar.model

import androidx.compose.runtime.Immutable
import java.time.LocalDate
import kotlinx.collections.immutable.persistentMapOf

sealed interface CalendarDisplayMode {
    @Immutable
    data class Normal(
        val procedureCountByDate: Map<LocalDate, Int>? = persistentMapOf()
    ) : CalendarDisplayMode

    @Immutable
    data class Downtime(
        val downtimeByDate: Map<LocalDate, DownTimeStatus> = persistentMapOf(),
        val selectedProcedureId: Long? = null
    ) : CalendarDisplayMode
}
