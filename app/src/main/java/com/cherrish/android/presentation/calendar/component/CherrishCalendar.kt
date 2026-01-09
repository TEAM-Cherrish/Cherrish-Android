package com.cherrish.android.presentation.calendar.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.presentation.calendar.model.CalendarDay
import com.cherrish.android.presentation.calendar.model.CalendarDisplayMode
import com.cherrish.android.presentation.calendar.model.DownTimeStatus
import com.cherrish.android.presentation.calendar.util.nextMonth
import com.cherrish.android.presentation.calendar.util.previousMonth
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun CherrishCalendar(
    yearMonth: YearMonth,
    selectedDate: LocalDate?,
    displayMode: CalendarDisplayMode,
    onDateClick: (LocalDate) -> Unit,
    onMonthChange: (YearMonth) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        CalendarMonthHeader(
            onLeftArrowClick = { onMonthChange(yearMonth.previousMonth) },
            onRightArrowClick = { onMonthChange(yearMonth.nextMonth) },
            yearMonth = yearMonth
        )

        Spacer(modifier = Modifier.height(4.dp))

        BasicCalendar(
            yearMonth = yearMonth,
            displayMode = displayMode,
            dayContent = { day ->
                DayItem(
                    day = day,
                    isSelected = day is CalendarDay.Date && day.date == selectedDate,
                    onDateClick = onDateClick
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CherrishCalendarPreview() {
    CherrishTheme {
        var yearMonth by remember { mutableStateOf(YearMonth.now()) }
        var selectedDate by remember { mutableStateOf<LocalDate?>(LocalDate.now()) }
        val today = LocalDate.now()

        CherrishCalendar(
            yearMonth = yearMonth,
            selectedDate = selectedDate,
            displayMode = CalendarDisplayMode.Normal(
                procedureCountByDate = mapOf(
                    today.minusDays(2) to 3,
                    today to 1,
                    today.plusDays(10) to 2
                )
            ),
            onDateClick = { selectedDate = it },
            onMonthChange = { yearMonth = it }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CherrishCalendarDowntimePreview() {
    CherrishTheme {
        var yearMonth by remember { mutableStateOf(YearMonth.now()) }
        var selectedDate by remember { mutableStateOf<LocalDate?>(LocalDate.now()) }
        val today = LocalDate.now()

        CherrishCalendar(
            yearMonth = yearMonth,
            selectedDate = selectedDate,
            displayMode = CalendarDisplayMode.Downtime(
                downtimeByDate = mapOf(
                    today.minusDays(1) to DownTimeStatus.CAUTION,
                    today.minusDays(2) to DownTimeStatus.CAUTION,
                    today to DownTimeStatus.SENSITIVE,
                    today.plusDays(1) to DownTimeStatus.SENSITIVE,
                    today.plusDays(2) to DownTimeStatus.RECOVERY
                )
            ),
            onDateClick = { selectedDate = it },
            onMonthChange = { yearMonth = it }
        )
    }
}
