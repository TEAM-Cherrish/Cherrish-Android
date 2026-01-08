package com.cherrish.android.presentation.calendar.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cherrish.android.presentation.calendar.model.CalendarDay
import com.cherrish.android.presentation.calendar.model.CalendarDisplayMode
import com.cherrish.android.presentation.calendar.model.CalendarMonth
import com.cherrish.android.presentation.calendar.util.daysOfWeek
import com.cherrish.android.presentation.calendar.util.generateMonthData
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import kotlinx.collections.immutable.toImmutableList

@Composable
fun BasicCalendar(
    displayMode: CalendarDisplayMode,
    modifier: Modifier = Modifier,
    yearMonth: YearMonth = YearMonth.now(),
    firstDayOfWeek: DayOfWeek = DayOfWeek.SUNDAY,
    dayContent: @Composable (CalendarDay) -> Unit
) {
    val (procedureCountByDate, downtimeByDate) = when (displayMode) {
        is CalendarDisplayMode.Normal -> {
            displayMode.procedureCountByDate to emptyMap()
        }
        is CalendarDisplayMode.Downtime -> {
            emptyMap<LocalDate, Int>() to displayMode.downtimeByDate
        }
    }

    val monthData = generateMonthData(
        yearMonth = yearMonth,
        firstDayOfWeek = firstDayOfWeek,
        procedureCountByDate = procedureCountByDate,
        downtimeByDate = downtimeByDate
    ).calendarMonth

    val daysOfWeek = daysOfWeek(firstDayOfWeek = firstDayOfWeek).toImmutableList()

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        DaysOfWeekTitle(
            daysOfWeek = daysOfWeek
        )

        Spacer(Modifier.height(4.dp))

        CalendarMonthGrid(
            month = monthData,
            dayContent = dayContent
        )
    }
}

@Composable
private fun CalendarMonthGrid(
    month: CalendarMonth,
    dayContent: @Composable (CalendarDay) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        for (week in month.weekDays) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                for (day in week) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                    ) {
                        dayContent(day)
                    }
                }
            }
        }
    }
}
