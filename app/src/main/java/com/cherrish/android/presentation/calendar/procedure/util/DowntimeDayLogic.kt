package com.cherrish.android.presentation.calendar.procedure.util

import com.cherrish.android.presentation.calendar.procedure.model.DowntimeValidationType
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class DowntimeDayLogic(
    private val endDay: LocalDate,
    private val downtimeDay: Int
) {
    private val startDate: LocalDate = LocalDate.now()
    private val endDate: LocalDate = endDay

    val downtimeValidationType: DowntimeValidationType
        get() {
            val diffDays = ChronoUnit.DAYS.between(startDate, endDate).toInt()

            return if (downtimeDay >= diffDays) {
                DowntimeValidationType.EXCEEDS_GOAL
            } else {
                DowntimeValidationType.VALID
            }
        }
}