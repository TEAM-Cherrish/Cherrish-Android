package com.cherrish.android.presentation.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cherrish.android.core.common.extension.onLogFailure
import com.cherrish.android.core.common.extension.updateSuccess
import com.cherrish.android.core.common.state.UiState
import com.cherrish.android.data.repository.CalendarRepository
import com.cherrish.android.presentation.calendar.model.CalendarDisplayMode
import com.cherrish.android.presentation.calendar.util.yearMonth
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import java.time.YearMonth
import javax.inject.Inject
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val calendarRepository: CalendarRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<CalendarUiState>>(
        UiState.Success(CalendarUiState.FakeNormal)
    )
    val uiState: StateFlow<UiState<CalendarUiState>> = _uiState.asStateFlow()

    init {
        loadMonthlyCalendar(yearMonth = YearMonth.now())
    }

    private fun loadMonthlyCalendar(yearMonth: YearMonth) {
        viewModelScope.launch {
            _uiState.update { UiState.Loading }

            calendarRepository.getCalendarMonthly(
                year = yearMonth.year,
                month = yearMonth.monthValue
            ).onSuccess { response ->
                val procedureCountByDate = response.dailyProcedureCounts?.mapKeys { (day, _) ->
                    yearMonth.atDay(day)
                }?.mapValues { it.value.toInt() }

                _uiState.update {
                    UiState.Success(
                        CalendarUiState(
                            selectedYearMonth = yearMonth,
                            calendarDisplayMode = CalendarDisplayMode.Normal(procedureCountByDate),
                            selectedDate = null,
                            procedureInfoList = persistentListOf()
                        )
                    )
                }
            }.onLogFailure { }
        }
    }

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
