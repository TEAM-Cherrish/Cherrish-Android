package com.cherrish.android.presentation.calendar.procedure

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cherrish.android.presentation.calendar.procedure.model.ProcedureCardDisplayMode
import com.cherrish.android.presentation.calendar.procedure.model.ProcedureCardItemUiModel
import com.cherrish.android.presentation.calendar.procedure.model.ProcedureFlow
import com.cherrish.android.presentation.calendar.procedure.model.ProcedureStep
import com.cherrish.android.presentation.calendar.procedure.model.ProcedureWorryUiModel
import kotlin.Long
import kotlin.String
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

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

    val selectedDowntime: Int? = null,

    val procedureItems: ImmutableList<ProcedureCardItemUiModel> = persistentListOf(),
    val selectedProcedureCardId: Long? = null,

    val selectedWorryName: String = ""
) {
    val showStepProgressBar: Boolean = flow != ProcedureFlow.Entry
    val title: String = when (flow) {
        ProcedureFlow.Entry -> "시술 여부 선택"

        ProcedureFlow.Treat -> when (step) {
            ProcedureStep.RecoverySchedule -> "목표 디데이 설정"
            ProcedureStep.FilteringWithSearch -> "시술 필터링"
            ProcedureStep.Downtime -> "다운타임 설정"
            else -> "시술 관리"
        }

        ProcedureFlow.NoTreat -> when (step) {
            ProcedureStep.Category -> "시술 카테고리 선택"
            ProcedureStep.RecoverySchedule -> "목표 디데이 설정"
            ProcedureStep.Filtering -> "시술 필터링"
            ProcedureStep.Downtime -> "다운타임 설정"
            else -> "시술 관리"
        }
    }

    val contentTopPadding: Dp = when {
        flow == ProcedureFlow.Entry -> 84.dp
        step == ProcedureStep.Filtering -> 20.dp
        step == ProcedureStep.FilteringWithSearch -> 24.dp
        step == ProcedureStep.Downtime -> 20.dp
        else -> 60.dp
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
                hasChoice && hasDate
            }

            ProcedureStep.FilteringWithSearch -> selectedProcedureCardId != null
            ProcedureStep.Downtime -> selectedDowntime != null
            else -> false
        }

        ProcedureFlow.NoTreat -> when (step) {
            ProcedureStep.Category -> selectedWorryId != null
            ProcedureStep.RecoverySchedule -> {
                val hasChoice = recoverySelectedIndex != null
                val hasDate = year.isNotBlank() && month.isNotBlank() && day.isNotBlank()
                hasChoice && hasDate
            }

            ProcedureStep.Filtering -> selectedProcedureCardId != null
            ProcedureStep.Downtime -> selectedDowntime != null
            else -> false
        }
    }

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

        val FakeProcedureCardItems = ProcedureUiState(
            procedureItems = persistentListOf(
                ProcedureCardItemUiModel(
                    id = 1L,
                    name = "레이저 토닝",
                    category = "색소 개선 | 톤업",
                    minDowntimeDays = 3,
                    maxDowntimeDays = 5,
                    displayMode = ProcedureCardDisplayMode.Basic
                ),
                ProcedureCardItemUiModel(
                    id = 2L,
                    name = "레이저 토닝",
                    category = "색소 개선 | 톤업",
                    minDowntimeDays = 1,
                    maxDowntimeDays = 3,
                    displayMode = ProcedureCardDisplayMode.Basic
                ),
                ProcedureCardItemUiModel(
                    id = 3L,
                    name = "레이저 토닝",
                    category = "색소 개선 | 톤업",
                    minDowntimeDays = 5,
                    maxDowntimeDays = 10,
                    displayMode = ProcedureCardDisplayMode.Basic
                ),
                ProcedureCardItemUiModel(
                    id = 4L,
                    name = "레이저 토닝",
                    category = "색소 개선 | 톤업",
                    minDowntimeDays = 3,
                    maxDowntimeDays = 5,
                    displayMode = ProcedureCardDisplayMode.Basic
                ),
                ProcedureCardItemUiModel(
                    id = 5L,
                    name = "레이저 토닝",
                    category = "색소 개선 | 톤업",
                    minDowntimeDays = 3,
                    maxDowntimeDays = 5,
                    displayMode = ProcedureCardDisplayMode.Basic
                ),
                ProcedureCardItemUiModel(
                    id = 6L,
                    name = "레이저 토닝",
                    category = "색소 개선 | 톤업",
                    minDowntimeDays = 3,
                    maxDowntimeDays = 5,
                    displayMode = ProcedureCardDisplayMode.Basic
                ),
                ProcedureCardItemUiModel(
                    id = 7L,
                    name = "레이저 토닝",
                    category = "색소 개선 | 톤업",
                    minDowntimeDays = 3,
                    maxDowntimeDays = 5,
                    displayMode = ProcedureCardDisplayMode.Basic
                ),
                ProcedureCardItemUiModel(
                    id = 8L,
                    name = "레이저 토닝",
                    category = "색소 개선 | 톤업",
                    minDowntimeDays = 3,
                    maxDowntimeDays = 5,
                    displayMode = ProcedureCardDisplayMode.Basic
                ),
                ProcedureCardItemUiModel(
                    id = 9L,
                    name = "레이저 토닝",
                    category = "색소 개선 | 톤업",
                    minDowntimeDays = 3,
                    maxDowntimeDays = 5,
                    displayMode = ProcedureCardDisplayMode.Basic
                )
            )
        )
    }
}
