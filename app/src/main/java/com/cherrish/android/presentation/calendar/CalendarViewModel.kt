package com.cherrish.android.presentation.calendar

import androidx.lifecycle.ViewModel
import com.cherrish.android.core.common.extension.updateSuccess
import com.cherrish.android.core.common.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import java.time.YearMonth
import javax.inject.Inject
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class CalendarViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<CalendarUiState>>(
        UiState.Success(CalendarUiState.FakeNormal)
    )
    val uiState: StateFlow<UiState<CalendarUiState>> = _uiState.asStateFlow()

    fun onMonthChanged(yearMonth: YearMonth) {
        _uiState.updateSuccess { currentState ->
            currentState.copy(
                selectedYearMonth = yearMonth,
                selectedDate = null,
                procedureInfoList = persistentListOf()
            )
        }
    }

    fun onDateClick(date: LocalDate) {
    }

    fun onEventClick(procedureId: Long) {
    }
}
