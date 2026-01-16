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
        UiState.Success(
            ProcedureUiState.FakeNormal.copy(
                procedureItems = ProcedureUiState.FakeProcedureCardItems.procedureItems
            )
        )
    )

    val uiState: StateFlow<UiState<ProcedureUiState>> = _uiState.asStateFlow()

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

    fun onComplete() {
        _uiState.updateSuccess { current ->
            // TODO: 서버에 시술 정보 저장
            // API 호출 후 성공하면 초기 상태로 리셋
            ProcedureUiState.FakeNormal
        }
    }
}

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

private fun ProcedureUiState.nextStep(): ProcedureStep {
    return when (flow) {
        ProcedureFlow.NoTreat -> when (step) {
            ProcedureStep.Category -> ProcedureStep.RecoverySchedule
            ProcedureStep.RecoverySchedule -> ProcedureStep.Filtering
            ProcedureStep.Filtering -> ProcedureStep.Downtime
            ProcedureStep.Downtime -> ProcedureStep.Downtime
            else -> step
        }

        ProcedureFlow.Treat -> when (step) {
            ProcedureStep.RecoverySchedule -> ProcedureStep.FilteringWithSearch
            ProcedureStep.FilteringWithSearch -> ProcedureStep.Downtime
            ProcedureStep.Downtime -> ProcedureStep.Downtime
            else -> step
        }

        ProcedureFlow.Entry -> step
    }
}

private sealed interface PrevResult {
    data object ToEntry : PrevResult
    data class ToStep(val step: ProcedureStep) : PrevResult
}

private fun ProcedureUiState.prevStepOrEntry(): PrevResult {
    return when (flow) {
        ProcedureFlow.NoTreat -> when (step) {
            ProcedureStep.Category -> PrevResult.ToEntry
            ProcedureStep.RecoverySchedule -> PrevResult.ToStep(ProcedureStep.Category)
            ProcedureStep.Filtering -> PrevResult.ToStep(ProcedureStep.RecoverySchedule)
            ProcedureStep.Downtime -> PrevResult.ToStep(ProcedureStep.Filtering)
            else -> PrevResult.ToEntry
        }

        ProcedureFlow.Treat -> when (step) {
            ProcedureStep.RecoverySchedule -> PrevResult.ToEntry
            ProcedureStep.FilteringWithSearch -> PrevResult.ToStep(ProcedureStep.RecoverySchedule)
            ProcedureStep.Downtime -> PrevResult.ToStep(ProcedureStep.FilteringWithSearch)
            else -> PrevResult.ToEntry
        }

        ProcedureFlow.Entry -> PrevResult.ToEntry
    }
}
