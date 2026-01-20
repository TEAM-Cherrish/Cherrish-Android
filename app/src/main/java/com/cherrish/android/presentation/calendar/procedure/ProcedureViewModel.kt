package com.cherrish.android.presentation.calendar.procedure

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.cherrish.android.core.common.extension.updateSuccess
import com.cherrish.android.core.common.state.UiState
import com.cherrish.android.data.model.ProcedureModel
import com.cherrish.android.data.model.UserProcedureItemModel
import com.cherrish.android.data.model.UserProceduresRequestModel
import com.cherrish.android.data.repository.ProcedureRepository
import com.cherrish.android.data.repository.UserProcedureRepository
import com.cherrish.android.data.repository.WorryRepository
import com.cherrish.android.presentation.calendar.navigation.Procedure
import com.cherrish.android.presentation.calendar.procedure.model.ProcedureCardDisplayMode
import com.cherrish.android.presentation.calendar.procedure.model.ProcedureCardItemUiModel
import com.cherrish.android.presentation.calendar.procedure.model.ProcedureFlow
import com.cherrish.android.presentation.calendar.procedure.model.ProcedureStep
import com.cherrish.android.presentation.calendar.procedure.model.ProcedureWithDowntime
import com.cherrish.android.presentation.calendar.procedure.model.ProcedureWorryUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ProcedureViewModel @Inject constructor(
    private val worryRepository: WorryRepository,
    private val procedureRepository: ProcedureRepository,
    private val userProcedureRepository: UserProcedureRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val startDateArg = runCatching {
        LocalDate.parse(savedStateHandle.toRoute<Procedure>().startDate)
    }.getOrElse { LocalDate.now() }

    private val _uiState = MutableStateFlow<UiState<ProcedureUiState>>(
        UiState.Success(
            ProcedureUiState.FakeNormal.copy(
                procedureItems = persistentListOf()
            )
        )
    )
    val uiState: StateFlow<UiState<ProcedureUiState>> = _uiState.asStateFlow()

    private val _completeEvent = MutableSharedFlow<Unit>(extraBufferCapacity = 1)
    val completeEvent = _completeEvent.asSharedFlow()

    init {
        fetchWorries()
    }

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

    fun fetchWorries() {
        viewModelScope.launch {
            worryRepository.getWorries()
                .onSuccess { worries ->

                    _uiState.updateSuccess { current ->
                        val mapped = worries
                            .map { ProcedureWorryUiModel(id = it.id, content = it.content) }
                            .toPersistentList()

                        current.copy(
                            worries =
                            if (mapped.isEmpty()) {
                                ProcedureUiState.FakeNormal.worries
                            } else {
                                mapped
                            }
                        )
                    }
                }
                .onFailure { e ->
                    _uiState.updateSuccess { current ->
                        current.copy(worries = ProcedureUiState.FakeNormal.worries)
                    }
                }
        }
    }

    fun onRecoveryOptionClick(index: Int) {
        _uiState.updateSuccess { it.copy(recoverySelectedIndex = index) }
    }

    fun onSearchQueryChange(query: String) {
        _uiState.updateSuccess { it.copy(searchQuery = query) }
    }

    fun onSearchAction(query: String) {
        val current = currentStateOrNull() ?: return
        val keyword = query.trim().takeIf { it.isNotEmpty() }
        fetchProcedures(keyword = keyword, worryId = current.selectedWorryId)
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
        var queryToFetch: ProceduresQuery? = null

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

            if (current.flow == ProcedureFlow.NoTreat &&
                current.step == ProcedureStep.RecoverySchedule
            ) {
                queryToFetch = ProceduresQuery(
                    keyword = null,
                    worryId = current.selectedWorryId
                )
            }

            if (current.flow == ProcedureFlow.Treat &&
                current.step == ProcedureStep.RecoverySchedule
            ) {
                val keyword = current.searchQuery.trim().takeIf { it.isNotEmpty() }
                queryToFetch = ProceduresQuery(
                    keyword = keyword,
                    worryId = null
                )
            }

            current.copy(step = nextStep)
        }

        queryToFetch?.let { fetchProcedures(keyword = it.keyword, worryId = it.worryId) }
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
        val current = currentStateOrNull() ?: return

        val proceduresWithDowntime = current.selectedProcedureCardIds.map { procedureId ->
            val downtime = current.procedureDowntimeMap[procedureId] ?: 0
            ProcedureWithDowntime(
                procedureId = procedureId,
                downtimeDays = downtime
            )
        }

        val scheduledAt = startDateArg
            .atStartOfDay()
            .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        val recoveryTargetDate = current.recoveryTargetDateOrNull()

        val request = UserProceduresRequestModel(
            scheduledAt = scheduledAt,
            recoveryTargetDate = recoveryTargetDate,
            procedures = proceduresWithDowntime.map {
                UserProcedureItemModel(
                    procedureId = it.procedureId,
                    downtimeDays = it.downtimeDays
                )
            }
        )

        viewModelScope.launch {
            userProcedureRepository.addUserProcedures(body = request)
                .onSuccess { response ->
                    _uiState.updateSuccess { ProcedureUiState.FakeNormal }
                    _completeEvent.tryEmit(Unit)
                }
                .onFailure { e ->
                }
        }
    }

    private fun currentStateOrNull(): ProcedureUiState? =
        (_uiState.value as? UiState.Success)?.data

    private fun fetchProcedures(keyword: String?, worryId: Long?) {
        viewModelScope.launch {
            procedureRepository.getProcedures(keyword = keyword, worryId = worryId)
                .onSuccess { response ->
                    val items = response.procedures
                        .map { it.toUiModel() }
                        .toPersistentList()

                    _uiState.updateSuccess { current ->
                        current.copy(
                            procedureItems = items,
                            selectedProcedureCardIds = persistentListOf(),
                            procedureDowntimeMap = emptyMap(),
                            selectedProcedureForDowntime = null,
                            showDowntimeBottomSheet = false
                        )
                    }
                }
                .onFailure { e ->
                    _uiState.updateSuccess { current ->
                        current.copy(
                            procedureItems = persistentListOf(),
                            selectedProcedureCardIds = persistentListOf(),
                            procedureDowntimeMap = emptyMap(),
                            selectedProcedureForDowntime = null,
                            showDowntimeBottomSheet = false
                        )
                    }
                }
        }
    }
}

private data class ProceduresQuery(
    val keyword: String?,
    val worryId: Long?
)

private fun ProcedureModel.toUiModel(): ProcedureCardItemUiModel =
    ProcedureCardItemUiModel(
        id = id,
        name = name,
        category = category.orEmpty(),
        minDowntimeDays = minDowntimeDays,
        maxDowntimeDays = maxDowntimeDays,
        displayMode = ProcedureCardDisplayMode.Basic
    )

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

private fun ProcedureUiState.recoveryTargetDateOrNull(): String? {
    return try {
        if (year.isBlank() || month.isBlank() || day.isBlank()) return null
        val date = LocalDate.of(year.toInt(), month.toInt(), day.toInt())
        date.format(DateTimeFormatter.ISO_LOCAL_DATE)
    } catch (e: Exception) {
        null
    }
}
