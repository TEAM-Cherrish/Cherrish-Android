package com.cherrish.android.presentation.home.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.R
import com.cherrish.android.core.common.extension.dropShadow
import com.cherrish.android.core.common.extension.noRippleClickable
import com.cherrish.android.core.designsystem.component.button.CherrishButton
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.presentation.home.model.UpcomingPlanUiModel
import com.cherrish.android.presentation.home.type.UpcomingPlanTimelineType
import com.cherrish.android.presentation.home.type.style
import com.cherrish.android.presentation.home.type.toUpcomingPlanTimelineType
import java.time.LocalDate
import kotlin.math.abs
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun UpcomingPlanSection(
    onAddPlanClick: () -> Unit,
    plans: ImmutableList<UpcomingPlanUiModel>,
    onUpcomingPlanClick: (LocalDate) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .dropShadow(
                shape = RoundedCornerShape(10.dp),
                color = CherrishTheme.colors.shadow,
                blur = 10.dp,
                offsetX = 0.dp,
                offsetY = 0.dp,
                spread = 0.dp
            )
            .clip(shape = RoundedCornerShape(14.dp))
            .background(color = CherrishTheme.colors.gray0)
            .padding(horizontal = 15.dp, vertical = 16.dp)
    ) {
        Text(
            text = "다가오는 일정",
            style = CherrishTheme.typography.body1M14,
            color = CherrishTheme.colors.gray700,
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 5.dp)
        )

        HorizontalDivider(
            thickness = 1.dp,
            color = CherrishTheme.colors.gray300
        )

        when {
            plans.isEmpty() -> {
                UpcomingNoPlan(
                    onAddPlanClick = onAddPlanClick
                )
            }

            else -> {
                UpcomingPlan(
                    plans = plans,
                    onUpcomingPlanClick = onUpcomingPlanClick
                )
            }
        }
    }
}

@Composable
private fun UpcomingPlan(
    plans: ImmutableList<UpcomingPlanUiModel>,
    onUpcomingPlanClick: (LocalDate) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 26.dp, end = 16.dp, top = 11.dp)
    ) {
        plans.forEachIndexed { index, plan ->
            key("${plan.upcomingPlanDate}-$index") {
                UpcomingPlanContent(
                    planCount = plans.size,
                    planModel = plan,
                    index = index,
                    onUpcomingPlanClick = onUpcomingPlanClick,
                    type = index.toUpcomingPlanTimelineType()
                )
            }
        }
    }
}

@Composable
private fun UpcomingPlanContent(
    planCount: Int,
    planModel: UpcomingPlanUiModel,
    index: Int,
    onUpcomingPlanClick: (LocalDate) -> Unit,
    type: UpcomingPlanTimelineType,
    modifier: Modifier = Modifier
) {
    var heightPx by remember { mutableIntStateOf(0) }
    var dateTextTopPx by remember { mutableFloatStateOf(0f) }

    Row(
        modifier = modifier
            .wrapContentHeight()
            .onSizeChanged { heightPx = it.height }
            .noRippleClickable(onClick = { onUpcomingPlanClick(planModel.upcomingPlanDate) }),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalAlignment = Alignment.Top
    ) {
        UpcomingPlanTimeline(
            planCount = planCount,
            type = type,
            heightPx = heightPx,
            isFirst = index == 0,
            dotTopPx = dateTextTopPx
        )

        UpcomingPlanBox(
            upcomingDate = planModel.upcomingPlanDate,
            procedureName = planModel.procedureName,
            procedureCount = planModel.procedureCount,
            dDay = planModel.dDay,
            onDateTextTopPositioned = { topPx -> dateTextTopPx = topPx }
        )
    }
}

@Composable
private fun UpcomingPlanTimeline(
    planCount: Int,
    type: UpcomingPlanTimelineType,
    heightPx: Int,
    isFirst: Boolean,
    dotTopPx: Float,
    modifier: Modifier = Modifier
) {
    val timelineStyle = type.style(size = planCount, colors = CherrishTheme.colors)
    val density = LocalDensity.current

    val totalHeightDp = with(density) { heightPx.toDp() }

    val circleSize = 10.dp
    val lineWidth = 2.dp

    Box(
        modifier = modifier
            .height(totalHeightDp)
            .width(circleSize),
        contentAlignment = Alignment.TopCenter
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val stroke = with(density) { lineWidth.toPx() }
            val radius = with(density) { (circleSize / 2).toPx() }
            val centerX = size.width / 2f

            val dotTopY = dotTopPx
            val circleCenterY = dotTopY + radius
            val lineTopY = if (isFirst) dotTopY else 0f
            drawRect(
                brush = timelineStyle.barBrush,
                topLeft = Offset(centerX - stroke / 2f, lineTopY),
                size = Size(stroke, size.height - lineTopY)
            )

            drawCircle(
                color = timelineStyle.circleColor,
                radius = radius,
                center = Offset(centerX, circleCenterY)
            )
        }
    }
}

@Composable
private fun UpcomingPlanBox(
    upcomingDate: LocalDate,
    procedureName: String,
    procedureCount: Int,
    dDay: Int,
    onDateTextTopPositioned: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    var previousDateTextTopY by remember { mutableFloatStateOf(Float.NaN) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.padding(top = 5.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = upcomingDate.toString(),
                style = CherrishTheme.typography.body1M14,
                color = CherrishTheme.colors.gray700,
                modifier = Modifier.onGloballyPositioned { coordinates ->
                    val y = coordinates.positionInParent().y
                    if (previousDateTextTopY.isNaN() || abs(y - previousDateTextTopY) > 0.5f) {
                        previousDateTextTopY = y
                        onDateTextTopPositioned(y)
                    }
                }
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = procedureName,
                    style = CherrishTheme.typography.title2M16,
                    color = CherrishTheme.colors.gray900
                )

                if (procedureCount != 0) {
                    Text(
                        text = "외 ${procedureCount}개",
                        style = CherrishTheme.typography.body1R14,
                        color = CherrishTheme.colors.gray600
                    )
                }
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            DDayChip(
                dDay = dDay
            )

            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_right),
                contentDescription = null,
                tint = CherrishTheme.colors.gray600
            )
        }
    }
}

@Composable
private fun DDayChip(
    dDay: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(shape = RoundedCornerShape(20.dp))
            .background(color = CherrishTheme.colors.gray200)
            .padding(horizontal = 10.dp)
    ) {
        Text(
            text = "D-$dDay",
            style = CherrishTheme.typography.body3R12,
            color = CherrishTheme.colors.gray600
        )
    }
}

@Composable
private fun UpcomingNoPlan(
    onAddPlanClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 9.dp)
            .padding(bottom = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Image(
            painter = painterResource(id = R.drawable.img_home_no_plan),
            contentDescription = null,
            modifier = Modifier.size(width = 98.dp, height = 80.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "아직 진행 중인 관리가 없어요.",
            style = CherrishTheme.typography.body1R14,
            color = CherrishTheme.colors.gray600,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        CherrishButton(
            text = "관리 일정을 추가하기",
            onClick = onAddPlanClick
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview_UpcomingPlanSection_Empty() {
    CherrishTheme {
        UpcomingPlanSection(
            onAddPlanClick = {},
            plans = persistentListOf(),
            onUpcomingPlanClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview_UpcomingPlanSection_Filled() {
    CherrishTheme {
        UpcomingPlanSection(
            onAddPlanClick = {},
            plans = persistentListOf(
                UpcomingPlanUiModel(
                    upcomingPlanDate = LocalDate.of(2026, 1, 20),
                    procedureName = "슈링크",
                    procedureCount = 1,
                    dDay = 3
                ),
                UpcomingPlanUiModel(
                    upcomingPlanDate = LocalDate.of(2026, 1, 25),
                    procedureName = "보톡스",
                    procedureCount = 0,
                    dDay = 8
                ),
                UpcomingPlanUiModel(
                    upcomingPlanDate = LocalDate.of(2026, 2, 1),
                    procedureName = "필러",
                    procedureCount = 2,
                    dDay = 15
                )
            ),
            onUpcomingPlanClick = {}
        )
    }
}
