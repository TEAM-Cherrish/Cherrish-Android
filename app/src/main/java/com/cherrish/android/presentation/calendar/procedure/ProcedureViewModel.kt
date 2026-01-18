package com.cherrish.android.presentation.calendar.procedure

import androidx.lifecycle.ViewModel
import com.cherrish.android.core.common.extension.updateSuccess
import com.cherrish.android.core.common.state.UiState
import com.cherrish.android.presentation.calendar.procedure.model.ProcedureCardDisplayMode
import com.cherrish.android.presentation.calendar.procedure.model.ProcedureFlow
import com.cherrish.android.presentation.calendar.procedure.model.ProcedureStep
import com.cherrish.android.presentation.calendar.procedure.model.ProcedureWithDowntime
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
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

    fun updateScreenHeight(heightDp: Float) {
        _uiState.updateSuccess { current ->
            current.copy(screenHeightDp = heightDp)
        }
    }

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

    fun onDowntimeClick(procedureId: Long) {
        _uiState.updateSuccess { current ->
            val procedure = current.procedureItems.firstOrNull { it.id == procedureId }
                ?: return@updateSuccess current

            current.copy(
                selectedProcedureForDowntime = procedure,
                showDowntimeBottomSheet = true,
                downtimePickerValue = current.procedureDowntimeMap[procedureId]
                    ?: procedure.minDowntimeDays
            )
        }
    }

    fun onDowntimeBottomSheetDismiss() {
        _uiState.updateSuccess { current ->
            current.copy(
                showDowntimeBottomSheet = false,
                selectedProcedureForDowntime = null
            )
        }
    }

    fun onDowntimePickerValueChange(value: Int) {
        _uiState.updateSuccess { current ->
            current.copy(downtimePickerValue = value)
        }
    }

    fun onDowntimeConfirm() {
        _uiState.updateSuccess { current ->
            val procedureId = current.selectedProcedureForDowntime?.id
                ?: return@updateSuccess current

            val updatedMap = current.procedureDowntimeMap.toMutableMap().apply {
                put(procedureId, current.downtimePickerValue)
            }

            current.copy(
                procedureDowntimeMap = updatedMap,
                showDowntimeBottomSheet = false,
                selectedProcedureForDowntime = null
            )
        }
    }

    fun onAddWithoutDowntime() {
        _uiState.updateSuccess { current ->
            val procedureId = current.selectedProcedureForDowntime?.id
                ?: return@updateSuccess current

            val updatedMap = current.procedureDowntimeMap.toMutableMap().apply {
                put(procedureId, 0)
            }

            current.copy(
                procedureDowntimeMap = updatedMap,
                showDowntimeBottomSheet = false,
                selectedProcedureForDowntime = null
            )
        }
    }

    fun onWorryClick(worryId: Long) {
        _uiState.updateSuccess { current ->
            val newName = current.worries.firstOrNull { it.id == worryId }?.content.orEmpty()

            current.copy(
                selectedWorryId = worryId,
                selectedWorryName = newName,
                procedureItems = current.procedureItems
            )
        }
    }

    fun onYearChange(value: String) {
        _uiState.updateSuccess {
            it.copy(year = value.filter { it.isDigit() }.take(4))
        }
    }

    fun onMonthChange(value: String) {
        _uiState.updateSuccess { current ->
            val filtered = value.filter { it.isDigit() }
            val isValid = filtered.isEmpty() ||
                (filtered.toIntOrNull()?.let { it in 1..12 } == true)

            current.copy(month = if (isValid) filtered.take(2) else current.month)
        }
    }

    fun onDayChange(value: String) {
        _uiState.updateSuccess { current ->
            val filtered = value.filter { it.isDigit() }
            val isValid = filtered.isEmpty() ||
                (filtered.toIntOrNull()?.let { it in 1..31 } == true)

            current.copy(day = if (isValid) filtered.take(2) else current.day)
        }
    }

    fun onProcedureCardClick(cardId: Long) {
        _uiState.updateSuccess { current ->
            val currentList = current.selectedProcedureCardIds

            val newList = if (cardId in currentList) {
                currentList.filter { it != cardId }
            } else {
                currentList + cardId
            }.toImmutableList()

            val updatedMap = if (cardId !in newList) {
                current.procedureDowntimeMap.filterKeys { it != cardId }
            } else {
                current.procedureDowntimeMap
            }

            current.copy(
                selectedProcedureCardIds = newList,
                procedureDowntimeMap = updatedMap
            )
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
                    selectedDowntime = null,
                    selectedProcedureCardIds = persistentListOf(),
                    procedureDowntimeMap = emptyMap()
                )
            }

            if (current.step == ProcedureStep.Filtering ||
                current.step == ProcedureStep.FilteringWithSearch
            ) {
                val nextStep = current.nextStep()

                val updatedProcedureItems = current.procedureItems.map { item ->
                    if (item.id in current.selectedProcedureCardIds) {
                        item.copy(displayMode = ProcedureCardDisplayMode.Selectable)
                    } else {
                        item
                    }
                }.toImmutableList()

                return@updateSuccess current.copy(
                    step = nextStep,
                    procedureItems = updatedProcedureItems
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
                is PrevResult.ToStep -> {
                    if (current.step == ProcedureStep.Downtime &&
                        (
                            prevStepOrEntry.step == ProcedureStep.Filtering ||
                                prevStepOrEntry.step == ProcedureStep.FilteringWithSearch
                            )
                    ) {
                        val updatedProcedureItems = current.procedureItems.map { item ->
                            item.copy(displayMode = ProcedureCardDisplayMode.Basic)
                        }.toImmutableList()

                        current.copy(
                            step = prevStepOrEntry.step,
                            procedureItems = updatedProcedureItems,
                            procedureDowntimeMap = emptyMap()
                        )
                    } else {
                        current.copy(step = prevStepOrEntry.step)
                    }
                }
            }
        }
    }

    fun onComplete() {
        _uiState.updateSuccess { current ->
            val proceduresWithDowntime = current.selectedProcedureCardIds.map { procedureId ->
                val downtime = current.procedureDowntimeMap[procedureId] ?: 0
                ProcedureWithDowntime(
                    procedureId = procedureId,
                    downtimeDays = downtime
                )
            }

            // TODO: 서버에 <procedureId, downtime>

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
        selectedDowntime = null,
        selectedProcedureCardIds = persistentListOf(),
        procedureDowntimeMap = emptyMap()
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
            ProcedureStep.Category
            -> PrevResult.ToEntry

            ProcedureStep.RecoverySchedule
            -> PrevResult.ToStep(ProcedureStep.Category)

            ProcedureStep.Filtering
            -> PrevResult.ToStep(ProcedureStep.RecoverySchedule)

            ProcedureStep.Downtime
            -> PrevResult.ToStep(ProcedureStep.Filtering)

            else
            -> PrevResult.ToEntry
        }

        ProcedureFlow.Treat -> when (step) {
            ProcedureStep.RecoverySchedule -> PrevResult.ToEntry
            ProcedureStep.FilteringWithSearch
            -> PrevResult.ToStep(ProcedureStep.RecoverySchedule)

            ProcedureStep.Downtime
            -> PrevResult.ToStep(ProcedureStep.FilteringWithSearch)

            else
            -> PrevResult.ToEntry
        }

        ProcedureFlow.Entry -> PrevResult.ToEntry
    }
}
