package com.cherrish.android.presentation.calendar.procedure

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cherrish.android.core.common.state.UiState
import com.cherrish.android.core.designsystem.component.LoadingScreen
import com.cherrish.android.core.designsystem.component.button.CherrishButton
import com.cherrish.android.core.designsystem.component.topappbar.BackAndCloseTopAppBar
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.presentation.calendar.procedure.component.DowntimeBottomSheet
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
    viewModel: ProcedureViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val configuration = LocalConfiguration.current

    LaunchedEffect(configuration.screenHeightDp) {
        viewModel.updateScreenHeight(configuration.screenHeightDp.toFloat())
    }

    LaunchedEffect(Unit) {
        viewModel.completeEvent.collect {
            onNavigateBack()
        }
    }

    when (val state = uiState) {
        is UiState.Loading -> {
            LoadingScreen()
        }

        is UiState.Failure -> {}
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
                onDowntimeBottomSheetDismiss = viewModel::onDowntimeBottomSheetDismiss,
                onDowntimePickerValueChange = viewModel::onDowntimePickerValueChange,
                onDowntimeConfirm = viewModel::onDowntimeConfirm,
                onAddWithoutDowntime = viewModel::onAddWithoutDowntime,
                onSearchableQueryChange = viewModel::onSearchQueryChange,
                onSearchAction = viewModel::onSearchAction,
                onNextClick = {
                    if (state.data.step == ProcedureStep.Downtime) {
                        viewModel.onComplete()
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
    onDowntimeClick: (Long) -> Unit,
    onDowntimeBottomSheetDismiss: () -> Unit,
    onDowntimePickerValueChange: (Int) -> Unit,
    onDowntimeConfirm: () -> Unit,
    onAddWithoutDowntime: () -> Unit,
    onSearchableQueryChange: (String) -> Unit,
    onSearchAction: (String) -> Unit,
    onNextClick: () -> Unit,
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val downtimeBottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val downtimePickerState = rememberLazyListState()

    LaunchedEffect(uiState.showDowntimeBottomSheet, uiState.downtimePickerValue) {
        if (uiState.showDowntimeBottomSheet) {
            val initialIndex = uiState.downtimePickerValue - 1
            downtimePickerState.scrollToItem(initialIndex.coerceIn(0, 29))
        }
    }

    LaunchedEffect(downtimePickerState.firstVisibleItemIndex) {
        if (uiState.showDowntimeBottomSheet) {
            val newValue = downtimePickerState.firstVisibleItemIndex + 1
            onDowntimePickerValueChange(newValue)
        }
    }

    BackHandler { onBackClick() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(CherrishTheme.colors.gray0)
            .navigationBarsPadding()
            .padding(top = 44.dp, bottom = 20.dp),
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
                        modifier = Modifier
                            .padding(horizontal = 26.dp)
                            .padding(bottom = 10.dp)
                    )
                }

                ProcedureFlow.NoTreat, ProcedureFlow.Treat -> {
                    when (uiState.step) {
                        ProcedureStep.Category -> {
                            CategoryContent(
                                worries = uiState.worries,
                                selectedWorryId = uiState.selectedWorryId,
                                onWorryClick = onWorryClick,
                                modifier = Modifier
                                    .padding(horizontal = 26.dp)
                                    .padding(bottom = 10.dp)
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
                                errorMessage = uiState.dateErrorMessage,
                                modifier = Modifier.padding(horizontal = 26.dp)
                            )
                        }

                        ProcedureStep.Filtering -> {
                            FilteringContent(
                                name = uiState.selectedWorryName,
                                cardItems = uiState.procedureItems,
                                selectedCardIds = uiState.selectedProcedureCardIds,
                                onCardClick = onProcedureCardClick,
                                bottomPadding = uiState.lazyColumnBottomPadding,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                        ProcedureStep.FilteringWithSearch -> {
                            FilteringWithSearchContent(
                                cardItems = uiState.procedureItems,
                                selectedCardIds = uiState.selectedProcedureCardIds,
                                onCardClick = onProcedureCardClick,
                                onSearchAction = onSearchAction,
                                query = uiState.searchQuery,
                                onQueryChange = onSearchableQueryChange,
                                bottomContentPadding = uiState.lazyColumnBottomPadding,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 24.dp)
                            )
                        }

                        ProcedureStep.Downtime -> {
                            DowntimeContent(
                                cardItems = uiState.selectedProcedureCardItems,
                                selectedCardIds =
                                    uiState.procedureDowntimeMap.keys.toImmutableList(),
                                onCardClick = onDowntimeClick,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }
        }
        CherrishButton(
            text = "다음",
            onClick = onNextClick,
            enabled = uiState.isNextEnabled,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(top = 24.dp)
        )
    }

    SelectedProcedureBottomSheet(
        isVisible = uiState.showBottomSheet,
        selectedProcedure = uiState.selectedProcedures,
        onDeletedClick = onProcedureCardClick,
        onButtonClick = onNextClick,
        sheetState = sheetState
    )

    if (uiState.selectedProcedureForDowntime != null) {
        DowntimeBottomSheet(
            onDismissRequest = onDowntimeBottomSheetDismiss,
            sheetState = downtimeBottomSheetState,
            validationType = uiState.downtimeValidationType,
            downtimeDay = uiState.downtimeDay,
            spareTimeDay = uiState.spareTimeDay,
            downtimeStartMonth = uiState.downtimeStartMonth,
            downtimeStartDay = uiState.downtimeStartDay,
            downtimeEndMonth = uiState.downtimeEndMonth,
            downtimeEndDay = uiState.downtimeEndDay,
            state = downtimePickerState,
            onAddWithoutDowntimeClick = onAddWithoutDowntime,
            onConfirmClick = onDowntimeConfirm,
            minDowntimeDays = uiState.selectedProcedureForDowntime.minDowntimeDays,
            maxDowntimeDays = uiState.selectedProcedureForDowntime.maxDowntimeDays,
            showBottomSheet = uiState.showDowntimeBottomSheet
        )
    }
}
