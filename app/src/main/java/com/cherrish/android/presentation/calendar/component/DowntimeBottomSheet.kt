package com.cherrish.android.presentation.calendar.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.cherrish.android.R
import com.cherrish.android.core.common.extension.dropShadow
import com.cherrish.android.core.designsystem.component.button.CherrishButton
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.core.designsystem.type.CherrishButtonStyle
import com.cherrish.android.presentation.calendar.model.DowntimeValidationType
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DowntimeBottomSheet(
    onDismissRequest: () -> Unit,
    sheetState: SheetState,
    validationType: DowntimeValidationType,
    downtimeDay: Int,
    spareTimeDay: Int,
    downtimeStartDay: String,
    downtimeEndDay: String,
    state: LazyListState,
    onAddWithoutDowntimeClick: () -> Unit,
    onConfirmClick: () -> Unit,
    modifier: Modifier = Modifier,
    scrimColor: Color = CherrishTheme.colors.bottomSheetScrimColor,
    showBottomSheet: Boolean = false
) {
    val flingBehavior = rememberSnapFlingBehavior(
        lazyListState = state
    )

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = onDismissRequest,
            sheetState = sheetState,
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            containerColor = CherrishTheme.colors.gray0,
            scrimColor = scrimColor,
            dragHandle = { DowntimeDragHandle() },
            modifier = modifier.fillMaxWidth()
        ) {
            val downtimeGuideBubbleText = when (validationType) {
                DowntimeValidationType.VALID -> "회복 목표디데이로부터 약 ${spareTimeDay}일 전에 안정될 수 있어요."
                DowntimeValidationType.EXCEEDS_GOAL -> "설정한 다운타임은 목표일을 넘깁니다."
            }

            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp, bottom = 20.dp)
            ) {
                DowntimeHeader()

                Spacer(modifier = Modifier.height(44.dp))

                DowntimeProgressBarSection(
                    downtimeGuideBubbleText = downtimeGuideBubbleText,
                    downtimeDay = downtimeDay,
                    spareTimeDay = spareTimeDay,
                    downtimeStartDay = downtimeStartDay,
                    downtimeEndDay = downtimeEndDay
                )

                Spacer(modifier = Modifier.height(24.dp))

                DowntimeDayPickerSection(
                    state = state,
                    flingBehavior = flingBehavior
                )

                Spacer(modifier = Modifier.height(44.dp))

                DowntimeActionButtons(
                    onAddWithoutDowntimeClick = onAddWithoutDowntimeClick,
                    onConfirmClick = onConfirmClick
                )
            }
        }
    }
}

@Composable
private fun DowntimeDragHandle(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(width = 40.dp, height = 5.dp)
                .clip(shape = RoundedCornerShape(4.dp))
                .background(color = CherrishTheme.colors.gray400)
        )
    }
}

@Composable
private fun DowntimeHeader() {
    Text(
        text = "개인 다운타임으로 설정해주세요.",
        color = CherrishTheme.colors.gray1000,
        style = CherrishTheme.typography.title1SB18,
        modifier = Modifier.padding(start = 25.dp)
    )
}

@Composable
private fun DowntimeProgressBarSection(
    downtimeGuideBubbleText: String,
    downtimeDay: Int,
    spareTimeDay: Int,
    downtimeStartDay: String,
    downtimeEndDay: String,
    modifier: Modifier = Modifier
) {
    val downtimeWeight by remember(downtimeDay, spareTimeDay) {
        derivedStateOf {
            downtimeDay.toFloat() / (downtimeDay + spareTimeDay)
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        DowntimeGuideBubble(text = downtimeGuideBubbleText)

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "다운타임 ${downtimeDay}일",
                color = CherrishTheme.colors.red600,
                style = CherrishTheme.typography.title2M16,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "여유기간 ${spareTimeDay}일",
                color = CherrishTheme.colors.gray800,
                style = CherrishTheme.typography.title2M16,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        DowntimeProgressBar(downtimeWeight = downtimeWeight)

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = downtimeStartDay,
                color = CherrishTheme.colors.gray700,
                style = CherrishTheme.typography.body2R13
            )

            Text(
                text = downtimeEndDay,
                color = CherrishTheme.colors.gray700,
                style = CherrishTheme.typography.body2R13
            )
        }
    }
}

@Composable
private fun DowntimeGuideBubble(
    text: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .dropShadow(
                    shape = RoundedCornerShape(8.dp),
                    color = CherrishTheme.colors.shadow,
                    blur = 10.dp,
                    offsetX = 0.dp,
                    offsetY = 0.dp
                )
                .clip(shape = RoundedCornerShape(8.dp))
                .background(CherrishTheme.colors.gray0)
                .border(
                    width = 1.dp,
                    color = CherrishTheme.colors.gray200,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(vertical = 12.dp),
            text = text,
            style = CherrishTheme.typography.body1R14,
            color = CherrishTheme.colors.gray1000,
            textAlign = TextAlign.Center
        )

        Icon(
            painter = painterResource(R.drawable.ic_tooltip_arrow),
            contentDescription = null,
            tint = CherrishTheme.colors.gray0,
            modifier = Modifier
                .offset(y = -5.dp)
                .padding(end = 45.dp)
                .align(Alignment.End)
        )
    }
}

@Composable
private fun DowntimeProgressBar(
    downtimeWeight: Float,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .height(8.dp)
    ) {
        val totalWidth = maxWidth

        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(24.dp))
                .background(color = CherrishTheme.colors.gray400)
        )

        Box(
            modifier = Modifier
                .width(totalWidth * downtimeWeight)
                .fillMaxHeight()
                .clip(shape = RoundedCornerShape(24.dp))
                .background(color = CherrishTheme.colors.red600)
        )
    }
}

@Composable
private fun DowntimeDayPickerSection(
    state: LazyListState,
    flingBehavior: FlingBehavior,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 23.dp, end = 25.dp)
    ) {
        DowntimeDayPickerOutline()

        DowntimeDayPicker(
            state = state,
            flingBehavior = flingBehavior
        )

        DowntimeDayPickerOutline()
    }
}

@Composable
private fun DowntimeDayPicker(
    state: LazyListState,
    flingBehavior: FlingBehavior,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.weight(55f))

        Column(
            modifier = Modifier.padding(vertical = 39.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "다운타임",
                color = CherrishTheme.colors.gray1000,
                style = CherrishTheme.typography.headlineSB20
            )

            Text(
                text = "보통 3-5일",
                color = CherrishTheme.colors.gray600,
                style = CherrishTheme.typography.title2M16
            )
        }

        Spacer(modifier = Modifier.weight(69f))

        NumberPicker(
            list = (1..30).toPersistentList(),
            fontSize = CherrishTheme.typography.headlineSB20.fontSize,
            state = state,
            flingBehavior = flingBehavior
        )

        Spacer(modifier = Modifier.weight(40f))
    }
}

@Composable
private fun DowntimeDayPickerOutline(
    modifier: Modifier = Modifier
) {
    HorizontalDivider(
        modifier = modifier.fillMaxWidth(),
        thickness = 1.dp,
        color = CherrishTheme.colors.gray400
    )
}

@Composable
fun NumberPicker(
    list: ImmutableList<Int>,
    fontSize: TextUnit,
    state: LazyListState,
    flingBehavior: FlingBehavior,
    modifier: Modifier = Modifier
) {
    val itemWidth = 74.dp
    val itemHeight = 44.dp

    val paddedList: ImmutableList<Int?> =
        remember(list) { listOf(null) + list + listOf(null) }.toPersistentList()
    val dpBasedSp = with(LocalDensity.current) { fontSize.toDp().toSp() }

    val selectedIndex by remember(state) {
        derivedStateOf { state.firstVisibleItemIndex + 1 }
    }

    Box(
        modifier = modifier
            .size(width = itemWidth, height = itemHeight * 3),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(width = itemWidth, height = itemHeight)
                .clip(RoundedCornerShape(10.dp))
                .background(CherrishTheme.colors.gray300)
        )

        LazyColumn(
            modifier = Modifier.size(width = itemWidth, height = itemHeight * 3),
            state = state,
            flingBehavior = flingBehavior,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(paddedList.size, key = { index -> index }) { index ->

                val isSelected = index == selectedIndex

                Box(
                    modifier = Modifier.size(itemWidth, itemHeight),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = paddedList[index]?.toString().orEmpty(),
                        color = if (isSelected) {
                            CherrishTheme.colors.gray1000
                        } else {
                            CherrishTheme.colors.gray500
                        },
                        style = if (isSelected) {
                            CherrishTheme.typography.title1M18.copy(
                                fontSize = dpBasedSp
                            )
                        } else {
                            CherrishTheme.typography.title2M16.copy(
                                fontSize = dpBasedSp
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun DowntimeActionButtons(
    onAddWithoutDowntimeClick: () -> Unit,
    onConfirmClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CherrishButton(
            text = "다운타임 없이 일정 추가",
            onClick = onAddWithoutDowntimeClick,
            style = CherrishButtonStyle.SECONDARY,
            modifier = Modifier.weight(184f)
        )

        CherrishButton(
            text = "확인",
            onClick = onConfirmClick,
            modifier = Modifier.weight(122f)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun Preview() {
    CherrishTheme {
        val state = rememberLazyListState()

        DowntimeBottomSheet(
            onDismissRequest = {},
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
            validationType = DowntimeValidationType.VALID,
            downtimeDay = 10,
            spareTimeDay = 15,
            downtimeStartDay = "2023.08.01",
            downtimeEndDay = "2023.08.1",
            scrimColor = CherrishTheme.colors.gray400,
            state = state,
            onAddWithoutDowntimeClick = {},
            onConfirmClick = {},
            showBottomSheet = true
        )
    }
}
