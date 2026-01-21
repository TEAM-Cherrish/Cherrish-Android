package com.cherrish.android.presentation.calendar.procedure.util

import com.cherrish.android.presentation.calendar.procedure.model.DowntimeValidationType
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class DowntimeDayLogic(
    private val startDay: LocalDate,
    private val endDay: LocalDate,
    private val downtimeDay: Int
) {
    val downtimeValidationType: DowntimeValidationType
        get() {
            val diffDays = ChronoUnit.DAYS.between(startDay, endDay).toInt()

            return if (downtimeDay >= diffDays) {
                DowntimeValidationType.EXCEEDS_GOAL
            } else {
                DowntimeValidationType.VALID
            }
        }
}
