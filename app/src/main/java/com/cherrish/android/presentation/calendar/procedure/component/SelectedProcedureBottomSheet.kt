package com.cherrish.android.presentation.calendar.procedure.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.core.common.extension.dropShadow
import com.cherrish.android.core.designsystem.component.button.CherrishButton
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.presentation.calendar.procedure.model.SelectedProcedureModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectedProcedureBottomSheet(
    isVisible: Boolean,
    selectedProcedure: ImmutableList<SelectedProcedureModel>,
    onDismiss: () -> Unit,
    onDeletedClick: (Long) -> Unit,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
) {
    LaunchedEffect(isVisible) {
        if (isVisible) {
            sheetState.show()
        } else {
            sheetState.hide()
        }
    }

    val configuration = LocalConfiguration.current
    val screenHeightDp = configuration.screenHeightDp.dp
    val maxSheetHeight = screenHeightDp * 0.36f
    val listState = rememberLazyListState()

    val isFirstItemVisible = remember(selectedProcedure.size) {
        derivedStateOf {
            val firstVisibleItem = listState.layoutInfo.visibleItemsInfo.firstOrNull()
            firstVisibleItem?.index == 0
        }
    }

    val isLastItemVisible = remember(selectedProcedure.size) {
        derivedStateOf {
            val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
            val lastItemIndex = selectedProcedure.size - 1
            lastVisibleItem?.index == lastItemIndex
        }
    }

    if (sheetState.isVisible) {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = sheetState,
            modifier = modifier
                .fillMaxWidth()
                .dropShadow(
                    shape = RoundedCornerShape(10.dp),
                    color = CherrishTheme.colors.shadow,
                    blur = 10.dp,
                    offsetX = 0.dp,
                    offsetY = 0.dp,
                    spread = 0.dp
                ),
            containerColor = CherrishTheme.colors.gray0,
            shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
            dragHandle = null
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = maxSheetHeight),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "선택한 시술",
                        color = CherrishTheme.colors.gray600,
                        style = CherrishTheme.typography.body1SB14,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 9.dp, horizontal = 24.dp),
                        textAlign = TextAlign.Start
                    )

                    HorizontalDivider(
                        thickness = 1.dp,
                        color = CherrishTheme.colors.gray400
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    LazyColumn(
                        state = listState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f, fill = false)
                            .padding(horizontal = 24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(
                            items = selectedProcedure,
                            key = { it.procedureId }
                        ) { procedure ->
                            SelectedProcedureItem(
                                procedureId = procedure.procedureId,
                                procedureName = procedure.procedureName,
                                minDowntimeDays = procedure.minDowntimeDays,
                                maxDowntimeDays = procedure.maxDowntimeDays,
                                onDeletedClick = { onDeletedClick(procedure.procedureId) }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    CherrishButton(
                        text = "다음",
                        onClick = onButtonClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp)
                            .padding(bottom = 20.dp)
                    )
                }

                if (!isFirstItemVisible.value) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .fillMaxWidth()
                            .padding(top = 49.dp)
                            .height(70.dp)
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        CherrishTheme.colors.gray0,
                                        CherrishTheme.colors.gray0.copy(alpha = 0.8f),
                                        CherrishTheme.colors.gray0.copy(alpha = 0.5f),
                                        Color.Transparent
                                    )
                                )
                            )
                    )
                }

                if (!isLastItemVisible.value) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .padding(bottom = 68.dp)
                            .height(70.dp)
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        CherrishTheme.colors.gray0.copy(alpha = 0.5f),
                                        CherrishTheme.colors.gray0.copy(alpha = 0.8f),
                                        CherrishTheme.colors.gray0
                                    )
                                )
                            )
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun SelectedProcedureBottomSheetPreview() {
    CherrishTheme {
        var isSheetVisible by remember { mutableStateOf(true) }
        var selectedProcedures by remember {
            mutableStateOf(
                persistentListOf(
                    SelectedProcedureModel(
                        procedureId = 1L,
                        procedureName = "레이저 토닝",
                        minDowntimeDays = 3,
                        maxDowntimeDays = 5
                    ),
                    SelectedProcedureModel(
                        procedureId = 2L,
                        procedureName = "보톡스",
                        minDowntimeDays = 2,
                        maxDowntimeDays = 4
                    ),
                    SelectedProcedureModel(
                        procedureId = 3L,
                        procedureName = "울쎄라",
                        minDowntimeDays = 5,
                        maxDowntimeDays = 7
                    ),
                    SelectedProcedureModel(
                        procedureId = 4L,
                        procedureName = "필러",
                        minDowntimeDays = 3,
                        maxDowntimeDays = 6
                    ),
                    SelectedProcedureModel(
                        procedureId = 5L,
                        procedureName = "리프팅 레이저",
                        minDowntimeDays = 7,
                        maxDowntimeDays = 10
                    ),
                    SelectedProcedureModel(
                        procedureId = 6L,
                        procedureName = "피코토닝",
                        minDowntimeDays = 2,
                        maxDowntimeDays = 4
                    ),
                    SelectedProcedureModel(
                        procedureId = 7L,
                        procedureName = "쥬베룩",
                        minDowntimeDays = 4,
                        maxDowntimeDays = 6
                    ),
                    SelectedProcedureModel(
                        procedureId = 8L,
                        procedureName = "스킨보톡스",
                        minDowntimeDays = 2,
                        maxDowntimeDays = 3
                    )
                )
            )
        }

        SelectedProcedureBottomSheet(
            isVisible = isSheetVisible,
            selectedProcedure = selectedProcedures,
            onDismiss = { isSheetVisible = false },
            onDeletedClick = { procedureId ->
                val newList = selectedProcedures
                    .filter { it.procedureId != procedureId }
                    .toPersistentList()

                selectedProcedures = newList

                if (newList.isEmpty()) {
                    isSheetVisible = false
                }
            },
            onButtonClick = { isSheetVisible = false }
        )
    }
}
