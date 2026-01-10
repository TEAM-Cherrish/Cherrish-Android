package com.cherrish.android.presentation.calendar

import androidx.compose.runtime.Immutable
import com.cherrish.android.presentation.calendar.model.CalendarDisplayMode
import com.cherrish.android.presentation.calendar.model.DownTimeStatus
import com.cherrish.android.presentation.calendar.model.ProcedureInfoModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import java.time.LocalDate
import java.time.YearMonth

@Immutable
data class CalendarUiState(
    val selectedYearMonth: YearMonth = YearMonth.now(),
    val calendarDisplayMode: CalendarDisplayMode = CalendarDisplayMode.Normal(),
    val selectedDate: LocalDate? = LocalDate.now(),
    val procedureInfoList: ImmutableList<ProcedureInfoModel> = persistentListOf(),
) {
    companion object {
        private val today = LocalDate.of(2026, 1, 7)

        private val mockProcedures = persistentListOf(
            ProcedureInfoModel(
                procedureId = 1L,
                procedureName = "레이저 토닝",
                procedureDay = "1월 7일 수요일",
                downTimeDuration = 5
            ),
            ProcedureInfoModel(
                procedureId = 2L,
                procedureName = "레이저 토닝",
                procedureDay = "1월 7일 수요일",
                downTimeDuration = 5
            ),
            ProcedureInfoModel(
                procedureId = 3L,
                procedureName = "울쎄라",
                procedureDay = "1월 7일 수요일",
                downTimeDuration = 3
            )
        )

        val FakeNormal = CalendarUiState(
            selectedYearMonth = YearMonth.of(2026, 1),
            calendarDisplayMode = CalendarDisplayMode.Normal(
                procedureCountByDate = mapOf(
                    today to 3,
                    LocalDate.of(2026, 1, 9) to 1,
                    LocalDate.of(2026, 1, 19) to 2
                )
            ),
            selectedDate = today,
            procedureInfoList = mockProcedures
        )

        val FakeDowntime = CalendarUiState(
            selectedYearMonth = YearMonth.of(2026, 1),
            calendarDisplayMode = CalendarDisplayMode.Downtime(
                downtimeByDate = mapOf(
                    today to DownTimeStatus.SENSITIVE,
                    today.plusDays(1) to DownTimeStatus.SENSITIVE,
                    today.plusDays(2) to DownTimeStatus.CAUTION,
                    today.plusDays(3) to DownTimeStatus.RECOVERY
                ),
                selectedProcedureId = 1L
            ),
            selectedDate = today,
            procedureInfoList = mockProcedures
        )

        val FakeEmpty = CalendarUiState(
            selectedYearMonth = YearMonth.of(2026, 1),
            calendarDisplayMode = CalendarDisplayMode.Normal(),
            selectedDate = LocalDate.of(2026, 1, 15),
            procedureInfoList = persistentListOf()
        )
    }
}