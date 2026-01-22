package com.cherrish.android.presentation.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cherrish.android.core.common.extension.collectLatestSideEffect
import com.cherrish.android.core.common.state.UiState
import com.cherrish.android.core.designsystem.component.LoadingScreen
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.presentation.calendar.component.CherrishCalendar
import com.cherrish.android.presentation.calendar.component.ProcedureScheduleCard
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun CalendarRoute(
    paddingValues: PaddingValues,
    navigateToProcedure: (LocalDate) -> Unit,
    viewModel: CalendarViewModel = hiltViewModel()
) {
    viewModel.sideEffect.collectLatestSideEffect { sideEffect ->
        when (sideEffect) {
            is CalendarSideEffect.NavigateToProcedure -> {
                navigateToProcedure(sideEffect.startDate)
            }
        }
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (val state = uiState) {
        is UiState.Loading -> {
            LoadingScreen()
        }

        is UiState.Failure -> {
        }

        is UiState.Success -> {
            CalendarScreen(
                uiState = state.data,
                paddingValues = paddingValues,
                onMonthChange = viewModel::onMonthChange,
                onDateClick = viewModel::onDateClick,
                onEventClick = viewModel::onEventClick,
                onAddButtonClick = viewModel::onAddButtonClick
            )
        }

        else -> {}
    }
}

@Composable
private fun CalendarScreen(
    paddingValues: PaddingValues,
    uiState: CalendarUiState,
    onMonthChange: (YearMonth) -> Unit,
    onDateClick: (LocalDate) -> Unit,
    onEventClick: (Long) -> Unit,
    onAddButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = CherrishTheme.colors.gray100)
            .padding(paddingValues)
            .padding(top = 40.dp, bottom = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        CherrishCalendar(
            yearMonth = uiState.selectedYearMonth,
            selectedDate = uiState.selectedDate,
            displayMode = uiState.calendarDisplayMode,
            onDateClick = onDateClick,
            onMonthChange = onMonthChange,
            modifier = Modifier.padding(horizontal = 5.dp)
        )

        ProcedureScheduleCard(
            displayMode = uiState.calendarDisplayMode,
            procedureInfo = uiState.procedureInfoList,
            onProcedureClick = onEventClick,
            onAddProcedureClick = onAddButtonClick,
            modifier = Modifier
                .padding(horizontal = 17.dp)
                .weight(1f)
        )
    }
}

@Preview
@Composable
private fun CalendarScreenNormalPreview() {
    CherrishTheme {
        CalendarScreen(
            paddingValues = PaddingValues(0.dp),
            uiState = CalendarUiState.FakeNormal,
            onDateClick = { },
            onMonthChange = { },
            onEventClick = { },
            onAddButtonClick = { }
        )
    }
}

@Preview
@Composable
private fun CalendarScreenDowntimePreview() {
    CherrishTheme {
        CalendarScreen(
            paddingValues = PaddingValues(0.dp),
            uiState = CalendarUiState.FakeDowntime,
            onDateClick = { },
            onMonthChange = { },
            onEventClick = { },
            onAddButtonClick = { }
        )
    }
}

@Preview
@Composable
private fun CalendarScreenNoSchedulePreview() {
    CherrishTheme {
        CalendarScreen(
            paddingValues = PaddingValues(0.dp),
            uiState = CalendarUiState.FakeEmpty,
            onDateClick = { },
            onMonthChange = { },
            onEventClick = { },
            onAddButtonClick = { }
        )
    }
}
