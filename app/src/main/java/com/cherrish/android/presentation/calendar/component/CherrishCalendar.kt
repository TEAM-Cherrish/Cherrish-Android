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
    onDateClick: (LocalDate) -> Unit,
    onMonthChange: (YearMonth) -> Unit,
    procedureCountByDate: Map<LocalDate, Int>,
    displayMode: CalendarDisplayMode,
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
            procedureCountByDate = procedureCountByDate,
            displayMode = displayMode,
            dayContent = { day ->
                DayItem(
                    day = day,
                    onClick = {
                        if (day is CalendarDay.Date) {
                            onDateClick(day.date)
                        }
                    },
                    isSelected = day is CalendarDay.Date && day.date == selectedDate,
                    showDowntime = displayMode is CalendarDisplayMode.ShowDowntime
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

        val procedureCountByDate = remember {
            mapOf(
                LocalDate.now() to 2,
                LocalDate.now().plusDays(1) to 1,
                LocalDate.now().plusDays(5) to 3
            )
        }

        CherrishCalendar(
            yearMonth = yearMonth,
            selectedDate = selectedDate,
            procedureCountByDate = procedureCountByDate,
            onDateClick = { selectedDate = it },
            onMonthChange = { newMonth ->
                yearMonth = newMonth
            },
            displayMode = CalendarDisplayMode.Normal
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CherrishCalendarDowntimePreview() {
    CherrishTheme {
        var yearMonth by remember { mutableStateOf(YearMonth.now()) }
        var selectedDate by remember { mutableStateOf<LocalDate?>(LocalDate.now()) }

        val procedureCountByDate = remember {
            mapOf(
                LocalDate.now() to 2,
                LocalDate.now().plusDays(1) to 1,
                LocalDate.now().plusDays(5) to 3
            )
        }

        val downtimeByDate = remember {
            mapOf(
                LocalDate.now() to DownTimeStatus.CAUTION,
                LocalDate.now().plusDays(1) to DownTimeStatus.CAUTION,
                LocalDate.now().plusDays(2) to DownTimeStatus.SENSITIVE,
                LocalDate.now().plusDays(3) to DownTimeStatus.RECOVERY
            )
        }

        CherrishCalendar(
            yearMonth = yearMonth,
            selectedDate = selectedDate,
            procedureCountByDate = procedureCountByDate,
            onDateClick = { selectedDate = it },
            onMonthChange = { newMonth ->
                yearMonth = newMonth
            },
            displayMode = CalendarDisplayMode.ShowDowntime(downtimeByDate)
        )
    }
}
