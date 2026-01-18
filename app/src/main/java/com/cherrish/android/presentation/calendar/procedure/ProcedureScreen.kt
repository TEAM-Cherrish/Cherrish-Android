package com.cherrish.android.presentation.calendar.procedure

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cherrish.android.core.common.state.UiState
import com.cherrish.android.core.designsystem.component.button.CherrishButton
import com.cherrish.android.core.designsystem.component.topappbar.BackAndCloseTopAppBar
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.presentation.calendar.procedure.component.SelectedProcedureBottomSheet
import com.cherrish.android.presentation.calendar.procedure.component.StepProgressBar
import com.cherrish.android.presentation.calendar.procedure.content.CategoryContent
import com.cherrish.android.presentation.calendar.procedure.content.DowntimeContent
import com.cherrish.android.presentation.calendar.procedure.content.ExistenceContent
import com.cherrish.android.presentation.calendar.procedure.content.FilteringContent
import com.cherrish.android.presentation.calendar.procedure.content.FilteringWithSearchContent
import com.cherrish.android.presentation.calendar.procedure.content.RecoveryScheduleContent
import com.cherrish.android.presentation.calendar.procedure.model.ProcedureFlow
import com.cherrish.android.presentation.calendar.procedure.model.ProcedureStep
import kotlinx.collections.immutable.toImmutableList

@Composable
fun ProcedureRoute(
    onNavigateBack: () -> Unit,
    onComplete: () -> Unit,
    viewModel: ProcedureViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (val state = uiState) {
        is UiState.Loading -> Unit
        is UiState.Failure -> Unit
        is UiState.Success -> {
            ProcedureScreen(
                uiState = state.data,
                onExistenceClick = viewModel::onExistenceClick,
                onWorryClick = viewModel::onWorryClick,
                onRecoveryOptionClick = viewModel::onRecoveryOptionClick,
                onYearChange = viewModel::onYearChange,
                onMonthChange = viewModel::onMonthChange,
                onDayChange = viewModel::onDayChange,
                onProcedureCardClick = viewModel::onProcedureCardClick,
                onDowntimeClick = viewModel::onDowntimeClick,
                onNextClick = {
                    if (state.data.step == ProcedureStep.Downtime) {
                        viewModel.onComplete()
                        onComplete()
                    } else {
                        viewModel.onNextClick()
                    }
                },
                onBackClick = {
                    if (state.data.flow == ProcedureFlow.Entry) {
                        onNavigateBack()
                    } else {
                        viewModel.onBackClick()
                    }
                },
                onCloseClick = onNavigateBack
            )
        }
        else -> Unit
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProcedureScreen(
    uiState: ProcedureUiState,
    onExistenceClick: (Int) -> Unit,
    onWorryClick: (Long) -> Unit,
    onRecoveryOptionClick: (Int) -> Unit,
    onYearChange: (String) -> Unit,
    onMonthChange: (String) -> Unit,
    onDayChange: (String) -> Unit,
    onProcedureCardClick: (Long) -> Unit,
    onDowntimeClick: (Int) -> Unit,
    onNextClick: () -> Unit,
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var query by remember { mutableStateOf("") }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    val showBottomSheet = (
        uiState.step == ProcedureStep.Filtering ||
            uiState.step == ProcedureStep.FilteringWithSearch
        ) &&
        uiState.selectedProcedureCardIds.isNotEmpty()

    BackHandler(enabled = true) { onBackClick() }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(CherrishTheme.colors.gray0)
                .padding(top = 40.dp, bottom = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BackAndCloseTopAppBar(
                title = uiState.title,
                onBackClick = onBackClick,
                onCloseClick = onCloseClick
            )

            if (uiState.showStepProgressBar) {
                StepProgressBar(
                    totalStep = uiState.totalSteps,
                    currentStep = uiState.currentStepIndex,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 26.dp)
                        .padding(top = 20.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = uiState.contentTopPadding)
                    .weight(1f)
            ) {
                when (uiState.flow) {
                    ProcedureFlow.Entry -> {
                        ExistenceContent(
                            selectedIndex = uiState.existenceSelectedIndex,
                            onItemClick = onExistenceClick,
                            modifier = Modifier.padding(horizontal = 26.dp)
                        )
                    }

                    ProcedureFlow.NoTreat,
                    ProcedureFlow.Treat -> {
                        when (uiState.step) {
                            ProcedureStep.Category -> {
                                CategoryContent(
                                    worries = uiState.worries,
                                    selectedWorryId = uiState.selectedWorryId,
                                    onWorryClick = onWorryClick,
                                    modifier = modifier.padding(horizontal = 26.dp)
                                )
                            }

                            ProcedureStep.RecoverySchedule -> {
                                RecoveryScheduleContent(
                                    selectedIndex = uiState.recoverySelectedIndex,
                                    onItemClick = onRecoveryOptionClick,
                                    year = uiState.year,
                                    month = uiState.month,
                                    day = uiState.day,
                                    onYearChange = onYearChange,
                                    onMonthChange = onMonthChange,
                                    onDayChange = onDayChange,
                                    modifier = Modifier.padding(horizontal = 26.dp)
                                )
                            }

                            ProcedureStep.Filtering -> {
                                FilteringContent(
                                    name = uiState.selectedWorryName,
                                    cardItems = uiState.procedureItems,
                                    selectedCardIds = uiState.selectedProcedureCardIds,
                                    onCardClick = onProcedureCardClick,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }

                            ProcedureStep.FilteringWithSearch -> {
                                FilteringWithSearchContent(
                                    cardItems = uiState.procedureItems,
                                    selectedCardIds = uiState.selectedProcedureCardIds,
                                    onCardClick = onProcedureCardClick,
                                    onSearchAction = { /* TODO */ },
                                    query = query,
                                    onQueryChange = { query = it },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 24.dp)
                                )
                            }

                            ProcedureStep.Downtime -> {
                                DowntimeContent(
                                    cardItems = uiState.procedureItems
                                        .filter { it.id in uiState.selectedProcedureCardIds }
                                        .toImmutableList(),
                                    selectedCardId = uiState.selectedDowntime?.toLong(),
                                    onCardClick = { clickedId ->
                                        onDowntimeClick(clickedId.toInt())
                                    },
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                CherrishButton(
                    text = "다음",
                    onClick = onNextClick,
                    enabled = uiState.isNextEnabled,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        ) {
            SelectedProcedureBottomSheet(
                isVisible = showBottomSheet,
                selectedProcedure = uiState.selectedProcedures,
                onDismiss = { },
                onDeletedClick = onProcedureCardClick,
                onButtonClick = onNextClick,
                sheetState = sheetState
            )
        }
    }
}
