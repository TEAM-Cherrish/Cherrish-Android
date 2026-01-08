package com.cherrish.android.presentation.calendar.component

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
        CalendarHeader(
            onLeftArrowClick = { onMonthChange(yearMonth.previousMonth) },
            onRightArrowClick = { onMonthChange(yearMonth.nextMonth) },
            yearMonth = yearMonth
        )

        BasicCalendar(
            yearMonth = yearMonth,
            displayMode = displayMode,
            dayContent = { day ->
                DayItem(
                    day = day,
                    onClick = { onDateClick((day as CalendarDay.Date).date) },
                    isSelected = day is CalendarDay.Date && day.date == selectedDate,
                    showDowntime = displayMode is CalendarDisplayMode.Downtime
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

        val displayMode = remember {
            CalendarDisplayMode.Normal(
                procedureCountByDate = mapOf(
                    LocalDate.now() to 2,
                    LocalDate.now().plusDays(1) to 1,
                    LocalDate.now().plusDays(5) to 3
                )
            )
        }

        CherrishCalendar(
            yearMonth = yearMonth,
            selectedDate = selectedDate,
            displayMode = displayMode,
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

        val displayMode = remember {
            CalendarDisplayMode.Downtime(
                downtimeByDate = mapOf(
                    LocalDate.now() to DownTimeStatus.CAUTION,
                    LocalDate.now().plusDays(1) to DownTimeStatus.CAUTION,
                    LocalDate.now().plusDays(2) to DownTimeStatus.SENSITIVE,
                    LocalDate.now().plusDays(3) to DownTimeStatus.RECOVERY
                )
            )
        }

        CherrishCalendar(
            yearMonth = yearMonth,
            selectedDate = selectedDate,
            displayMode = displayMode,
            onDateClick = { selectedDate = it },
            onMonthChange = { yearMonth = it }
        )
    }
}
