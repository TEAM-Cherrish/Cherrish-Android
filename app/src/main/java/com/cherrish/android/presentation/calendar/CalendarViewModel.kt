package com.cherrish.android.presentation.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cherrish.android.core.common.extension.onLogFailure
import com.cherrish.android.core.common.extension.updateSuccess
import com.cherrish.android.core.common.state.UiState
import com.cherrish.android.data.repository.CalendarRepository
import com.cherrish.android.presentation.calendar.model.CalendarDisplayMode
import com.cherrish.android.presentation.calendar.model.DownTimeStatus
import com.cherrish.android.presentation.calendar.model.ProcedureInfoModel
import com.cherrish.android.presentation.calendar.util.formatProcedureDay
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import java.time.YearMonth
import javax.inject.Inject
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val calendarRepository: CalendarRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<CalendarUiState>>(UiState.Loading)
    val uiState: StateFlow<UiState<CalendarUiState>> = _uiState.asStateFlow()

    init {
        loadMonthlyCalendar(yearMonth = YearMonth.now())
    }

    fun onDateClick(date: LocalDate) {
        _uiState.updateSuccess { currentState ->
            if (currentState.calendarDisplayMode is CalendarDisplayMode.Downtime) {
                viewModelScope.launch {
                    calendarRepository.getCalendarDaily(
                        date = date.toString()
                    ).onSuccess { response ->
                        _uiState.updateSuccess { state ->
                            state.copy(
                                calendarDisplayMode = CalendarDisplayMode.Normal(
                                    procedureCountByDate = state.cachedProcedureCountByDate
                                ),
                                selectedDate = date,
                                procedureInfoList = response.events.map { event ->
                                    ProcedureInfoModel(
                                        procedureId = event.userProcedureId,
                                        procedureName = event.name,
                                        procedureDay = formatProcedureDay(event.scheduledAt),
                                        downTimeDuration = event.downtimeDays
                                    )
                                }.toImmutableList()
                            )
                        }
                    }.onLogFailure { }
                }
                currentState
            } else {
                loadDailyCalendar(date)
                currentState.copy(selectedDate = date)
            }
        }
    }

    fun onMonthChanged(yearMonth: YearMonth) {
        val newSelectedDate = if (yearMonth == YearMonth.now()) {
            LocalDate.now()
        } else {
            yearMonth.atDay(1)
        }

        loadMonthlyCalendarWithDate(yearMonth, newSelectedDate)
    }

    fun onEventClick(procedureId: Long) {
        _uiState.updateSuccess { currentState ->
            val currentMode = currentState.calendarDisplayMode

            if (currentMode is CalendarDisplayMode.Downtime &&
                currentMode.selectedProcedureId == procedureId
            ) {
                currentState.copy(
                    calendarDisplayMode = CalendarDisplayMode.Normal(
                        procedureCountByDate = currentState.cachedProcedureCountByDate
                    )
                )
            } else {
                loadDowntimeDetail(procedureId)
                currentState
            }
        }
    }

    private fun loadMonthlyCalendar(yearMonth: YearMonth) {
        loadMonthlyCalendarWithDate(yearMonth, LocalDate.now())
    }

    private fun loadMonthlyCalendarWithDate(yearMonth: YearMonth, selectedDate: LocalDate) {
        viewModelScope.launch {
            val dailyDeferred = async {
                calendarRepository.getCalendarDaily(date = selectedDate.toString())
            }

            calendarRepository.getCalendarMonthly(
                year = yearMonth.year,
                month = yearMonth.monthValue
            ).onSuccess { response ->
                val procedureCountByDate = response.dailyProcedureCounts.mapKeys { (day, _) ->
                    yearMonth.atDay(day)
                }.mapValues { it.value.toInt() }

                dailyDeferred.await().onSuccess { dailyResponse ->
                    _uiState.update {
                        UiState.Success(
                            CalendarUiState(
                                selectedYearMonth = yearMonth,
                                calendarDisplayMode = CalendarDisplayMode.Normal(
                                    procedureCountByDate
                                ),
                                selectedDate = selectedDate,
                                procedureInfoList = dailyResponse.events.map { event ->
                                    ProcedureInfoModel(
                                        procedureId = event.userProcedureId,
                                        procedureName = event.name,
                                        procedureDay = formatProcedureDay(event.scheduledAt),
                                        downTimeDuration = event.downtimeDays
                                    )
                                }.toImmutableList(),
                                cachedProcedureCountByDate = procedureCountByDate
                            )
                        )
                    }
                }
            }.onLogFailure { }
        }
    }

    private fun loadDailyCalendar(date: LocalDate) {
        viewModelScope.launch {
            calendarRepository.getCalendarDaily(
                date = date.toString()
            ).onSuccess { response ->
                _uiState.updateSuccess { currentState ->
                    currentState.copy(
                        procedureInfoList = response.events.map { event ->
                            ProcedureInfoModel(
                                procedureId = event.userProcedureId,
                                procedureName = event.name,
                                procedureDay = formatProcedureDay(event.scheduledAt),
                                downTimeDuration = event.downtimeDays
                            )
                        }.toImmutableList()
                    )
                }
            }.onLogFailure { }
        }
    }

    private fun loadDowntimeDetail(userProcedureId: Long) {
        viewModelScope.launch {
            calendarRepository.getCalendarEventDowntime(userProcedureId).onSuccess { response ->
                _uiState.updateSuccess { currentState ->
                    val downtimeByDate = buildMap<LocalDate, DownTimeStatus> {
                        response.sensitiveDays.forEach { dateString ->
                            put(LocalDate.parse(dateString), DownTimeStatus.SENSITIVE)
                        }
                        response.cautionDays.forEach { dateString ->
                            put(LocalDate.parse(dateString), DownTimeStatus.CAUTION)
                        }
                        response.recoveryDays.forEach { dateString ->
                            put(LocalDate.parse(dateString), DownTimeStatus.RECOVERY)
                        }
                    }

                    currentState.copy(
                        calendarDisplayMode = CalendarDisplayMode.Downtime(
                            downtimeByDate = downtimeByDate,
                            selectedProcedureId = userProcedureId
                        )
                    )
                }
            }.onLogFailure { }
        }
    }
}
