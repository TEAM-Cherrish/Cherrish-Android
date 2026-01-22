package com.cherrish.android.presentation.calendar.procedure

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cherrish.android.presentation.calendar.procedure.model.DowntimeValidationType
import com.cherrish.android.presentation.calendar.procedure.model.ProcedureCardItemUiModel
import com.cherrish.android.presentation.calendar.procedure.model.ProcedureFlow
import com.cherrish.android.presentation.calendar.procedure.model.ProcedureStep
import com.cherrish.android.presentation.calendar.procedure.model.ProcedureWorryUiModel
import com.cherrish.android.presentation.calendar.procedure.model.SelectedProcedureModel
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.ChronoUnit
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

@Immutable
data class ProcedureUiState(
    val step: ProcedureStep = ProcedureStep.Category,
    val flow: ProcedureFlow = ProcedureFlow.Entry,

    val existenceSelectedIndex: Int? = null,

    val worries: ImmutableList<ProcedureWorryUiModel> = persistentListOf(),
    val selectedWorryId: Long? = null,

    val recoverySelectedIndex: Int? = null,
    val year: String = "",
    val month: String = "",
    val day: String = "",
    val startDay: LocalDate = LocalDate.now(),

    val selectedDowntime: Int? = null,

    val procedureItems: ImmutableList<ProcedureCardItemUiModel> = persistentListOf(),
    val selectedProcedureCardId: Long? = null,

    val selectedProcedureCardIds: ImmutableList<Long> = persistentListOf(),
    val selectedProcedureItems: ImmutableList<ProcedureCardItemUiModel> = persistentListOf(),

    val selectedWorryName: String = "",

    val searchQuery: String = "",
    val searchedQuery: String = "",

    val showDowntimeBottomSheet: Boolean = false,
    val selectedProcedureForDowntime: ProcedureCardItemUiModel? = null,
    val downtimePickerValue: Int = 0,
    val procedureDowntimeMap: Map<Long, Int> = emptyMap(),

    val screenHeightDp: Float = 0f
) {
    val dateErrorMessage: String? = run {
        if (year.isBlank() || month.isBlank() || day.isBlank()) {
            return@run null
        }

        try {
            val yearInt = year.toInt()
            val monthInt = month.toInt()
            val dayInt = day.toInt()

            val yearMonth = YearMonth.of(yearInt, monthInt)
            if (dayInt > yearMonth.lengthOfMonth()) {
                return@run "올바른 날짜 형식이 아니에요."
            }

            val today = LocalDate.now()

            val inputDate = LocalDate.of(yearInt, monthInt, dayInt)

            when {
                inputDate.isBefore(startDay) -> "목표일은 시술 날짜 이후로만 설정할 수 있어요."
                else -> null
            }
        } catch (e: Exception) {
            ""
        }
    }

    val showStepProgressBar: Boolean = flow != ProcedureFlow.Entry

    val title: String = when (flow) {
        ProcedureFlow.Entry -> "시술 여부 선택"

        ProcedureFlow.Treat -> when (step) {
            ProcedureStep.RecoverySchedule -> "목표 디데이 설정"
            ProcedureStep.FilteringWithSearch -> "시술 필터링"
            ProcedureStep.Downtime -> "다운타임 설정"
            else -> ""
        }

        ProcedureFlow.NoTreat -> when (step) {
            ProcedureStep.Category -> "시술 카테고리 선택"
            ProcedureStep.RecoverySchedule -> "목표 디데이 설정"
            ProcedureStep.Filtering -> "시술 필터링"
            ProcedureStep.Downtime -> "다운타임 설정"
            else -> ""
        }
    }

    val contentTopPadding: Dp = when {
        flow == ProcedureFlow.Entry -> 84.dp
        step == ProcedureStep.Filtering -> 20.dp
        step == ProcedureStep.FilteringWithSearch -> 20.dp
        step == ProcedureStep.Downtime -> 20.dp
        else -> 60.dp
    }

    val showBottomSheet: Boolean = (
        step == ProcedureStep.Filtering ||
            step == ProcedureStep.FilteringWithSearch
        ) && selectedProcedureCardIds.isNotEmpty()

    val maxSheetHeight: Dp = (screenHeightDp * 0.3f).dp

    val lazyColumnBottomPadding: Dp = run {
        val isFilteringStep = step == ProcedureStep.Filtering ||
            step == ProcedureStep.FilteringWithSearch

        if (isFilteringStep && showBottomSheet) {
            maxSheetHeight
        } else {
            20.dp
        }
    }

    val totalSteps: Int = when (flow) {
        ProcedureFlow.Entry -> 0
        ProcedureFlow.Treat -> 3
        ProcedureFlow.NoTreat -> 4
    }

    val currentStepIndex: Int = when (flow) {
        ProcedureFlow.Entry -> 0

        ProcedureFlow.Treat -> when (step) {
            ProcedureStep.RecoverySchedule -> 0
            ProcedureStep.FilteringWithSearch -> 1
            ProcedureStep.Downtime -> 2
            else -> 0
        }

        ProcedureFlow.NoTreat -> when (step) {
            ProcedureStep.Category -> 0
            ProcedureStep.RecoverySchedule -> 1
            ProcedureStep.Filtering -> 2
            ProcedureStep.Downtime -> 3
            else -> 0
        }
    }

    val isNextEnabled: Boolean = when (flow) {
        ProcedureFlow.Entry -> existenceSelectedIndex != null

        ProcedureFlow.Treat -> when (step) {
            ProcedureStep.RecoverySchedule -> {
                val hasChoice = recoverySelectedIndex != null
                val hasDate = year.isNotBlank() && month.isNotBlank() && day.isNotBlank()
                val hasNoError = dateErrorMessage == null
                hasChoice && hasDate && hasNoError
            }

            ProcedureStep.FilteringWithSearch -> selectedProcedureCardIds.isNotEmpty()
            ProcedureStep.Downtime -> {
                selectedProcedureCardIds.all { it in procedureDowntimeMap }
            }
            else -> false
        }

        ProcedureFlow.NoTreat -> when (step) {
            ProcedureStep.Category -> selectedWorryId != null
            ProcedureStep.RecoverySchedule -> {
                val hasChoice = recoverySelectedIndex != null
                val hasDate = year.isNotBlank() && month.isNotBlank() && day.isNotBlank()
                val hasNoError = dateErrorMessage == null
                hasChoice && hasDate && hasNoError
            }

            ProcedureStep.Filtering -> selectedProcedureCardIds.isNotEmpty()
            ProcedureStep.Downtime -> {
                selectedProcedureCardIds.all { it in procedureDowntimeMap }
            }
            else -> false
        }
    }

    val selectedProcedures: ImmutableList<SelectedProcedureModel>
        get() {
            val procedureMap = selectedProcedureItems.associateBy { it.id }
            return selectedProcedureCardIds
                .mapNotNull { id -> procedureMap[id] }
                .map {
                    SelectedProcedureModel(
                        procedureId = it.id,
                        procedureName = it.name,
                        minDowntimeDays = it.minDowntimeDays,
                        maxDowntimeDays = it.maxDowntimeDays
                    )
                }
                .toImmutableList()
        }

    val selectedProcedureCardItems: ImmutableList<ProcedureCardItemUiModel>
        get() {
            val procedureMap = selectedProcedureItems.associateBy { it.id }
            return selectedProcedureCardIds
                .mapNotNull { id -> procedureMap[id] }
                .toImmutableList()
        }

    private val targetDate: LocalDate?
        get() = try {
            if (year.isNotBlank() && month.isNotBlank() && day.isNotBlank()) {
                LocalDate.of(year.toInt(), month.toInt(), day.toInt())
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }

    val downtimeDay: Int
        get() = downtimePickerValue

    val spareTimeDay: Int
        get() {
            val target = targetDate ?: return 0
            val totalDays = ChronoUnit.DAYS.between(startDay, target).toInt()
            return (totalDays - downtimePickerValue).coerceAtLeast(0)
        }

    val downtimeValidationType: DowntimeValidationType
        get() {
            if (downtimePickerValue == 0) {
                return DowntimeValidationType.INVALID
            }
            val target = targetDate ?: return DowntimeValidationType.INVALID
            val diffDays = ChronoUnit.DAYS.between(startDay, target).toInt()
            return if (diffDays - downtimePickerValue <= 0) {
                DowntimeValidationType.EXCEEDS_GOAL
            } else {
                DowntimeValidationType.VALID
            }
        }

    val downtimeStartMonth: Int
        get() = startDay.monthValue

    val downtimeStartDay: Int
        get() = startDay.dayOfMonth

    val downtimeEndMonth: Int
        get() = targetDate?.monthValue ?: 0

    val downtimeEndDay: Int
        get() = targetDate?.dayOfMonth ?: 0

    companion object {

        val FakeNormal = ProcedureUiState(
            worries = persistentListOf(
                ProcedureWorryUiModel(id = 1L, content = "피부결 ∙ 각질"),
                ProcedureWorryUiModel(id = 2L, content = "색소 ∙ 잡티"),
                ProcedureWorryUiModel(id = 3L, content = "홍조"),
                ProcedureWorryUiModel(id = 4L, content = "탄력 ∙ 주름"),
                ProcedureWorryUiModel(id = 5L, content = "모공"),
                ProcedureWorryUiModel(id = 6L, content = "트러블")
            )
        )
    }
}
