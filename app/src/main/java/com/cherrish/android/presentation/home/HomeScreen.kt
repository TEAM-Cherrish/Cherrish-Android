package com.cherrish.android.presentation.home

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cherrish.android.R
import com.cherrish.android.core.common.extension.dropShadow
import com.cherrish.android.core.common.extension.noRippleClickable
import com.cherrish.android.core.common.state.UiState
import com.cherrish.android.core.designsystem.component.button.CherrishButton
import com.cherrish.android.core.designsystem.component.gaugebar.CherrishGaugeBar
import com.cherrish.android.core.designsystem.component.type.CherrishGaugeType
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.core.designsystem.theme.graEnd
import com.cherrish.android.core.designsystem.theme.graStart
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
    paddingValues: PaddingValues,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (val state = uiState) {
        is UiState.Loading -> {
        }

        is UiState.Failure -> {
        }

        is UiState.Success -> {
            HomeScreen(
                uiState = state.data,
                paddingValues = paddingValues,
                onUpcomingPlanClick = viewModel::onUpcomingPlanClicked,
                onAddPlanClick = viewModel::onAddPlanClicked
            )
        }

        else -> {}
    }
}

@Composable
private fun HomeScreen(
    uiState: HomeUiState,
    paddingValues: PaddingValues,
    onUpcomingPlanClick: (Int) -> Unit,
    onAddPlanClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = CherrishTheme.colors.graEnd)
            .verticalScroll(scrollState)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(270.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            graStart,
                            graEnd
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(paddingValues)
                .padding(top = 40.dp)
                .padding(horizontal = 24.dp)
                .padding(top = 10.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            ChallengeSection(
                imageRes = uiState.gauges[uiState.selectedIndex].image,
                currentStep = uiState.currentStep,
                gauges = uiState.gauges
            )

            PlanBoxSection(
                todayDate = uiState.todayDate,
                plans = uiState.plans
            )

            UpcomingPlanSection(
                onAddPlanClick = onAddPlanClick,
                plans = uiState.upcomingPlans,
                onUpcomingPlanClick = onUpcomingPlanClick
            )
        }
    }
}

@Composable
private fun ChallengeSection(
    @DrawableRes imageRes: Int,
    currentStep: Int,
    gauges: ImmutableList<CherrishGaugeType>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .size(122.dp)
                .padding(end = 13.dp)
                .offset(y = (-42).dp)
                .zIndex(1f),
            alignment = Alignment.TopEnd
        )

        Column(
            modifier = modifier.fillMaxWidth()
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_app_logo),
                contentDescription = null,
                modifier = Modifier.padding(start = 7.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Challenge(
                currentStep = currentStep,
                gauges = gauges
            )
        }
    }
}

@Composable
private fun Challenge(
    currentStep: Int,
    gauges: ImmutableList<CherrishGaugeType>,
    modifier: Modifier = Modifier
) {
    val isStart = currentStep <= 0
    val safeStep = currentStep.coerceIn(1, gauges.size)
    val gauge = gauges[safeStep - 1]

    val verticalPadding = if (isStart) 18.dp else 22.dp

    Column(
        modifier = modifier
            .fillMaxWidth()
            .dropShadow(shape = RoundedCornerShape(14.dp))
            .clip(shape = RoundedCornerShape(14.dp))
            .background(color = CherrishTheme.colors.gray0)
            .padding(horizontal = 16.dp, vertical = verticalPadding),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        if (isStart) {
            Text(
                text = "챌린지를 시작해봐요!",
                style = CherrishTheme.typography.body1M14,
                color = CherrishTheme.colors.gray700
            )
        } else {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "피부 컨디션 챌린지",
                    style = CherrishTheme.typography.title2M16,
                    color = CherrishTheme.colors.gray700
                )

                Text(
                    text = "${gauge.percent}%",
                    style = CherrishTheme.typography.title2M16,
                    color = CherrishTheme.colors.gray1000
                )

                Text(
                    text = "달성",
                    style = CherrishTheme.typography.title2M16,
                    color = CherrishTheme.colors.gray700
                )
            }
        }

        CherrishGaugeBar(
            currentStep = safeStep,
            gauges = gauges
        )
    }
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
                color = CherrishTheme.colors.shadow,
                blur = 10.dp,
                offsetX = 0.dp,
                offsetY = 0.dp,
                spread = 0.dp
            )
            .clip(shape = RoundedCornerShape(14.dp))
            .background(color = CherrishTheme.colors.gray0)
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
                color = CherrishTheme.colors.shadow,
                blur = 10.dp,
                offsetX = 0.dp,
                offsetY = 0.dp,
                spread = 0.dp
            )
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = CherrishTheme.colors.gray0)
            .padding(top = 11.dp, bottom = 16.dp),
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
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(70f))

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_home_no_plan),
                contentDescription = null,
                modifier = Modifier.size(width = 98.dp, height = 80.dp)
            )

            Text(
                text = "아직 진행 중인 관리가 없어요.",
                style = CherrishTheme.typography.body1R14,
                color = CherrishTheme.colors.gray600
            )
        }

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

@Preview(showBackground = true)
@Composable
private fun Preview4() {
    CherrishTheme {
        HomeScreen(
            uiState = HomeUiState.fake,
            paddingValues = PaddingValues(0.dp),
            onUpcomingPlanClick = {},
            onAddPlanClick = {}
        )
    }
}
