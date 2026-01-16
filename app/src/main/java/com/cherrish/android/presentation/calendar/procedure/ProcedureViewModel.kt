package com.cherrish.android.presentation.calendar.procedure

import androidx.lifecycle.ViewModel
import com.cherrish.android.core.common.extension.updateSuccess
import com.cherrish.android.core.common.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class ProcedureViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<ProcedureUiState>>(
        UiState.Success(ProcedureUiState.FakeNormal)
    )
    val uiState: StateFlow<UiState<ProcedureUiState>> = _uiState.asStateFlow()

    /**
     * Entry(시술 여부 선택)에서 칩을 누르면 즉시 flow/step이 결정되어 다음 화면으로 진입합니다.
     * - 0: 아직 선택 전이에요 -> NoTreat (Category부터 시작)
     * - 1: 선택한 시술이 있어요 -> Treat (RecoverySchedule부터 시작)
     */
    fun onExistenceClick(index: Int) {
        _uiState.updateSuccess { current ->
            current.copy(
                existenceSelectedIndex = index
            )
        }
    }

    fun onRecoveryOptionClick(index: Int) {
        _uiState.updateSuccess { it.copy(recoverySelectedIndex = index) }
    }

    fun onDowntimeClick(downtime: Int) {
        _uiState.updateSuccess { current ->
            val newValue = if (current.selectedDowntime == downtime) null else downtime
            current.copy(selectedDowntime = newValue)
        }
    }

    fun onWorryClick(worryId: Long) {
        _uiState.updateSuccess { current ->
            val newId = if (current.selectedWorryId == worryId) null else worryId

            val newName = current.worries.firstOrNull { it.id == newId }?.content.orEmpty()

            current.copy(
                selectedWorryId = newId,
                selectedWorryName = newName,
                procedureItems = if (newId != null) current.procedureItems else persistentListOf()
            )
        }
    }

    fun onYearChange(value: String) {
        _uiState.updateSuccess { it.copy(year = value) }
    }

    fun onMonthChange(value: String) {
        _uiState.updateSuccess { it.copy(month = value) }
    }

    fun onDayChange(value: String) {
        _uiState.updateSuccess { it.copy(day = value) }
    }

    fun onProcedureCardClick(cardId: Long) {
        _uiState.updateSuccess { current ->
            val newId = if (current.selectedProcedureCardId == cardId) null else cardId
            current.copy(selectedProcedureCardId = newId)
        }
    }

    fun onNextClick() {
        _uiState.updateSuccess { current ->
            if (!current.isNextEnabled) return@updateSuccess current

            if (current.flow == ProcedureFlow.Entry) {
                val selected = current.existenceSelectedIndex ?: return@updateSuccess current

                // 0: 선택 전 -> NoTreat (Category부터)
                // 1: 선택 있음 -> Treat (RecoverySchedule부터)
                val nextFlow = if (selected == 1) ProcedureFlow.NoTreat else ProcedureFlow.Treat
                val nextStep = if (nextFlow == ProcedureFlow.NoTreat) {
                    ProcedureStep.Category
                } else {
                    ProcedureStep.RecoverySchedule
                }

                return@updateSuccess current.copy(
                    flow = nextFlow,
                    step = nextStep,
                    selectedWorryId = null,
                    recoverySelectedIndex = null,
                    year = "",
                    month = "",
                    day = "",
                    selectedDowntime = null
                )
            }

            val nextStep = current.nextStep()
            current.copy(step = nextStep)
        }
    }

    fun onBackClick() {
        _uiState.updateSuccess { current ->
            if (current.flow == ProcedureFlow.Entry) return@updateSuccess current

            val prevStepOrEntry = current.prevStepOrEntry()
            when (prevStepOrEntry) {
                is PrevResult.ToEntry -> current.toEntryState()
                is PrevResult.ToStep -> current.copy(step = prevStepOrEntry.step)
            }
        }
    }
}

/** Entry로 돌아갈 때 상태 초기화 */
private fun ProcedureUiState.toEntryState(): ProcedureUiState {
    return copy(
        flow = ProcedureFlow.Entry,
        step = ProcedureStep.Category,
        existenceSelectedIndex = null,
        selectedWorryId = null,
        recoverySelectedIndex = null,
        year = "",
        month = "",
        day = "",
        selectedDowntime = null
    )
}

/** 다음 스텝 계산 */
private fun ProcedureUiState.nextStep(): ProcedureStep {
    return when (flow) {
        // NoTreat: Category -> RecoverySchedule -> Filtering -> Downtime
        ProcedureFlow.NoTreat -> when (step) {
            ProcedureStep.Category -> ProcedureStep.RecoverySchedule
            ProcedureStep.RecoverySchedule -> ProcedureStep.Filtering
            ProcedureStep.Filtering -> ProcedureStep.Downtime
            ProcedureStep.Downtime -> ProcedureStep.Downtime
            else -> step
        }

        // Treat: RecoverySchedule -> FilteringWithSearch -> Downtime
        ProcedureFlow.Treat -> when (step) {
            ProcedureStep.RecoverySchedule -> ProcedureStep.FilteringWithSearch
            ProcedureStep.FilteringWithSearch -> ProcedureStep.Downtime
            ProcedureStep.Downtime -> ProcedureStep.Downtime
            else -> step
        }

        ProcedureFlow.Entry -> step
    }
}

/** 뒤로가기 결과 타입 */
private sealed interface PrevResult {
    data object ToEntry : PrevResult
    data class ToStep(val step: ProcedureStep) : PrevResult
}

/** 이전 스텝 계산 */
private fun ProcedureUiState.prevStepOrEntry(): PrevResult {
    return when (flow) {
        // NoTreat: Category -> RecoverySchedule -> Filtering -> Downtime
        ProcedureFlow.NoTreat -> when (step) {
            ProcedureStep.Category -> PrevResult.ToEntry
            ProcedureStep.RecoverySchedule -> PrevResult.ToStep(ProcedureStep.Category)
            ProcedureStep.Filtering -> PrevResult.ToStep(ProcedureStep.RecoverySchedule)
            ProcedureStep.Downtime -> PrevResult.ToStep(ProcedureStep.Filtering)
            else -> PrevResult.ToEntry
        }

        // Treat: RecoverySchedule -> FilteringWithSearch -> Downtime
        ProcedureFlow.Treat -> when (step) {
            ProcedureStep.RecoverySchedule -> PrevResult.ToEntry
            ProcedureStep.FilteringWithSearch -> PrevResult.ToStep(ProcedureStep.RecoverySchedule)
            ProcedureStep.Downtime -> PrevResult.ToStep(ProcedureStep.FilteringWithSearch)
            else -> PrevResult.ToEntry
        }

        ProcedureFlow.Entry -> PrevResult.ToEntry
    }
}
