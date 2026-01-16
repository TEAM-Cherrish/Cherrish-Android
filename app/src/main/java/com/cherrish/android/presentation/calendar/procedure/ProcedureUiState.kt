package com.cherrish.android.presentation.calendar.procedure

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cherrish.android.presentation.calendar.procedure.model.ProcedureCardItemUiModel
import com.cherrish.android.presentation.calendar.procedure.model.ProcedureWorryUiModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
sealed interface ProcedureFlow {
    /** 첫 화면(프로그레스바/헤더 없음) */
    data object Entry : ProcedureFlow

    /** "선택한 시술이 있어요" 선택 시: RecoverySchedule -> FilteringWithSearch -> Downtime */
    data object Treat : ProcedureFlow

    /** "아직 선택 전이에요" 선택 시: Category -> RecoverySchedule -> Filtering -> Downtime */
    data object NoTreat : ProcedureFlow
}

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
    /** Entry 화면에서는 프로그레스 바를 숨김 */
    val showStepProgressBar: Boolean = flow != ProcedureFlow.Entry

    /** 상단 탑 앱 바 타이틀 */
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
        !showStepProgressBar -> 84.dp
        step == ProcedureStep.Filtering -> 20.dp
        step == ProcedureStep.FilteringWithSearch -> 24.dp
        else -> 60.dp
    }

    val totalSteps: Int = when (flow) {
        ProcedureFlow.Entry -> 0
        ProcedureFlow.Treat -> 3 // RecoverySchedule -> FilteringWithSearch -> Downtime
        ProcedureFlow.NoTreat -> 4 // Category -> RecoverySchedule -> Filtering -> Downtime
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
    }
}

@Immutable
sealed interface ProcedureStep {
    data object Category : ProcedureStep
    data object RecoverySchedule : ProcedureStep
    data object Downtime : ProcedureStep
    data object Filtering : ProcedureStep
    data object FilteringWithSearch : ProcedureStep
}
