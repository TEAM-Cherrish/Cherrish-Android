package com.cherrish.android.presentation.home

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.R
import com.cherrish.android.core.common.extension.dropShadow
import com.cherrish.android.core.common.extension.noRippleClickable
import com.cherrish.android.core.designsystem.component.button.CherrishButton
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.presentation.home.component.PlanBox
import com.cherrish.android.presentation.home.component.PlanBoxState
import com.cherrish.android.presentation.home.model.PlanUiModel
import com.cherrish.android.presentation.home.model.UpcomingPlanUiModel
import com.cherrish.android.presentation.home.type.DowntimePhase
import com.cherrish.android.presentation.home.type.UpcomingPlanTimelineType
import com.cherrish.android.presentation.home.type.style
import com.cherrish.android.presentation.home.type.toUpcomingPlanTimelineType
import java.time.LocalDate
import kotlin.math.abs
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun HomeRoute(
    paddingValues: PaddingValues
) {
    HomeScreen(paddingValues = paddingValues)
}

@Composable
private fun HomeScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Home",
        modifier = modifier.padding(paddingValues)
    )
}

@Composable
private fun PlanBoxSection(
    todayDate: String,
    plans: ImmutableList<PlanUiModel>,
    modifier: Modifier = Modifier
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    val previewCount = 3
    val hasMore = plans.size > previewCount
    val visiblePlans = if (expanded) plans else plans.take(previewCount)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .dropShadow(
                shape = RoundedCornerShape(14.dp),
                color = CherrishTheme.colors.gray0,
                blur = 10.dp,
                offsetX = 0.dp,
                offsetY = 0.dp,
                spread = 0.dp
            )
            .clip(shape = RoundedCornerShape(14.dp))
            .padding(horizontal = 15.dp)
            .padding(top = 18.dp, bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = todayDate,
            style = CherrishTheme.typography.body1M14,
            color = CherrishTheme.colors.gray700,
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.Start)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 70,
                        easing = FastOutLinearInEasing
                    )
                ),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            if (plans.isEmpty()) {
                PlanBox(state = PlanBoxState.Empty)
            } else {
                visiblePlans.forEachIndexed { index, plan ->
                    key("${plan.procedureName}-${plan.daysSince}-${plan.downtimePhase}-$index") {
                        PlanBox(
                            state = PlanBoxState.Filled(
                                medicalProcedureName = plan.procedureName,
                                medicalProcedureNameDate = plan.daysSince,
                                downtimePhase = plan.downtimePhase
                            )
                        )
                    }
                }
            }
        }

        if (hasMore && !expanded) {
            Text(
                text = "더보기",
                style = CherrishTheme.typography.body2R13,
                color = CherrishTheme.colors.gray500,
                modifier = Modifier.noRippleClickable {
                    expanded = true
                }
            )
        }

        if (expanded) {
            Text(
                text = "접기",
                style = CherrishTheme.typography.body2R13,
                color = CherrishTheme.colors.gray500,
                modifier = Modifier.noRippleClickable {
                    expanded = false
                }
            )
        }
    }
}

@Composable
private fun UpcomingPlanSection(
    onAddPlanClick: () -> Unit,
    plans: ImmutableList<UpcomingPlanUiModel>,
    onUpcomingPlanClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .dropShadow(
                shape = RoundedCornerShape(10.dp),
                color = CherrishTheme.colors.shadow1,
                blur = 10.dp,
                offsetX = 0.dp,
                offsetY = 0.dp,
                spread = 0.dp
            )
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = CherrishTheme.colors.gray0)
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "다가오는 일정",
            style = CherrishTheme.typography.body1M14,
            color = CherrishTheme.colors.gray700,
            modifier = Modifier.padding(start = 20.dp)
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
    onUpcomingPlanClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 26.dp, end = 16.dp)
    ) {
        plans.forEachIndexed { index, plan ->
            key("${plan.upcomingPlanDate}-$index") {
                UpcomingPlanContent(
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
    planModel: UpcomingPlanUiModel,
    index: Int,
    onUpcomingPlanClick: (Int) -> Unit,
    type: UpcomingPlanTimelineType,
    modifier: Modifier = Modifier
) {
    var heightPx by remember { mutableIntStateOf(0) }
    var dateTextTopPx by remember { mutableFloatStateOf(0f) }

    Row(
        modifier = modifier
            .wrapContentHeight()
            .onSizeChanged { heightPx = it.height }
            .noRippleClickable(onClick = { onUpcomingPlanClick(index) }),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalAlignment = Alignment.Top

    ) {
        UpcomingPlanTimeline(
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
    type: UpcomingPlanTimelineType,
    heightPx: Int,
    isFirst: Boolean,
    dotTopPx: Float,
    modifier: Modifier = Modifier
) {
    val timelineStyle = type.style(colors = CherrishTheme.colors)
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
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(70f))

        // TODO: 추후 이미지 들어갈 예정

        Text(
            text = "아직 진행 중인 관리가 없어요.",
            style = CherrishTheme.typography.body1R14,
            color = CherrishTheme.colors.gray600
        )

        Spacer(modifier = Modifier.weight(60f))

        CherrishButton(
            text = "관리 일정을 추가해보세요 !",
            onClick = onAddPlanClick,
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    CherrishTheme {
        val samplePlans = persistentListOf(
            PlanUiModel(
                procedureName = "슈링크",
                daysSince = 2,
                downtimePhase = DowntimePhase.SENSITIVE
            ),
            PlanUiModel(
                procedureName = "인모드",
                daysSince = 5,
                downtimePhase = DowntimePhase.RECOVERY
            ),
            PlanUiModel(
                procedureName = "리쥬란",
                daysSince = 10,
                downtimePhase = DowntimePhase.CAUTION
            ),
            PlanUiModel(
                procedureName = "피코토닝",
                daysSince = 14,
                downtimePhase = DowntimePhase.CAUTION
            ),
            PlanUiModel(
                procedureName = "피코토닝",
                daysSince = 14,
                downtimePhase = DowntimePhase.CAUTION
            ),
            PlanUiModel(
                procedureName = "피코토닝",
                daysSince = 14,
                downtimePhase = DowntimePhase.CAUTION
            ),
            PlanUiModel(
                procedureName = "피코토닝",
                daysSince = 14,
                downtimePhase = DowntimePhase.CAUTION
            ),
            PlanUiModel(
                procedureName = "피코토닝",
                daysSince = 14,
                downtimePhase = DowntimePhase.CAUTION
            ),
            PlanUiModel(
                procedureName = "피코토닝",
                daysSince = 14,
                downtimePhase = DowntimePhase.CAUTION
            ),
            PlanUiModel(
                procedureName = "피코토닝",
                daysSince = 14,
                downtimePhase = DowntimePhase.CAUTION
            )

        )

        PlanBoxSection(
            todayDate = "2026.01.15",
            plans = samplePlans
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview2() {
    CherrishTheme {
        val sampleUpcomingPlans = persistentListOf(
            UpcomingPlanUiModel(
                upcomingPlanDate = LocalDate.now().plusDays(3),
                procedureName = "슈링크",
                procedureCount = 2,
                dDay = 3
            ),
            UpcomingPlanUiModel(
                upcomingPlanDate = LocalDate.now().plusDays(10),
                procedureName = "인모드",
                procedureCount = 2,
                dDay = 10
            ),
            UpcomingPlanUiModel(
                upcomingPlanDate = LocalDate.now().plusDays(21),
                procedureName = "리쥬란",
                procedureCount = 1,
                dDay = 21
            )
        )

        UpcomingPlanSection(
            onAddPlanClick = {},
            plans = sampleUpcomingPlans,
            onUpcomingPlanClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview3() {
    CherrishTheme {
        val sampleNoPlans = persistentListOf<UpcomingPlanUiModel>()

        UpcomingPlanSection(
            onAddPlanClick = {},
            plans = sampleNoPlans,
            onUpcomingPlanClick = {}
        )
    }
}
