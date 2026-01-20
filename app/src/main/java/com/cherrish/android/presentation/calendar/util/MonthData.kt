package com.cherrish.android.presentation.calendar.util

import com.cherrish.android.core.util.daysUntil
import com.cherrish.android.presentation.calendar.model.CalendarDay
import com.cherrish.android.presentation.calendar.model.CalendarDisplayMode
import com.cherrish.android.presentation.calendar.model.CalendarMonth
import com.cherrish.android.presentation.calendar.model.DownTimeStatus
import java.time.DayOfWeek
import java.time.YearMonth

data class MonthData(
    private val month: YearMonth,
    private val inDays: Int,
    private val displayMode: CalendarDisplayMode
) {
    private val monthLength = month.lengthOfMonth()
    private val totalDays = inDays + monthLength
    private val totalWeeks = (totalDays + 6) / 7
    private val totalCells = totalWeeks * 7
    private val firstDay = month.atStartOfMonth()
    private val rows = (0 until totalCells).chunked(7)

    val calendarMonth: CalendarMonth = CalendarMonth(
        month,
        rows.map { week ->
            week.map { dayOffset -> getDay(dayOffset) }
        }
    )

    private fun getDay(dayOffset: Int): CalendarDay {
        if (dayOffset < inDays || dayOffset >= inDays + monthLength) {
            return CalendarDay.Empty
        }

        val date = firstDay.plusDays((dayOffset - inDays).toLong())

        return when (displayMode) {
            is CalendarDisplayMode.Normal -> {
                val count = displayMode.procedureCountByDate[date] ?: 0
                CalendarDay.Date.Normal(date = date, procedureCount = count)
            }
            is CalendarDisplayMode.Downtime -> {
                val status = displayMode.downtimeByDate[date] ?: DownTimeStatus.NONE
                val isDDay = displayMode.dDayDate == date

                CalendarDay.Date.Downtime(
                    date = date,
                    status = status,
                    isDDay = isDDay
                )
            }
        }
    }
}

fun generateMonthData(
    yearMonth: YearMonth,
    firstDayOfWeek: DayOfWeek,
    displayMode: CalendarDisplayMode
): MonthData {
    val firstDay = yearMonth.atStartOfMonth()
    val inDays = firstDayOfWeek.daysUntil(firstDay.dayOfWeek)
    return MonthData(yearMonth, inDays, displayMode)
}
