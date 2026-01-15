package com.cherrish.android.presentation.home

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
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
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
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
                onUpcomingPlanClick = viewModel::onUpcomingPlanClick,
                onAddPlanClick = viewModel::onAddPlanClick
            )
        }

        else -> {}
    }
}

@Composable
private fun HomeScreen(
    uiState: HomeUiState,
    paddingValues: PaddingValues,
    onUpcomingPlanClick: (LocalDate) -> Unit,
    onAddPlanClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = CherrishTheme.colors.graEnd)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(270.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = persistentListOf(
                            graStart,
                            graEnd
                        )
                    )
                )
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(
                start = 17.dp,
                end = 17.dp,
                top = 50.dp,
                bottom = 20.dp
            ),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                ChallengeSection(
                    imageRes = uiState.gauges[uiState.selectedIndex].image,
                    currentStep = uiState.currentStep,
                    gauges = uiState.gauges
                )
            }

            item {
                PlanBoxSection(
                    todayDate = uiState.todayDate,
                    plans = uiState.plans
                )
            }

            item {
                UpcomingPlanSection(
                    onAddPlanClick = onAddPlanClick,
                    plans = uiState.upcomingPlans,
                    onUpcomingPlanClick = onUpcomingPlanClick
                )
            }
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
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(10.dp)) // 10? 20?

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

    val verticalPadding = if (isStart) 22.dp else 18.dp

    Column(
        modifier = modifier
            .fillMaxWidth()
            .dropShadow(
                shape = RoundedCornerShape(14.dp),
                blur = 10.dp,
                offsetX = 0.dp,
                offsetY = 0.dp,
                spread = 0.dp
            )
            .clip(shape = RoundedCornerShape(14.dp))
            .background(color = CherrishTheme.colors.gray0)
            .padding(horizontal = 18.dp, vertical = verticalPadding),
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

    val bottomPadding = if (plans.isEmpty()) 18.dp else 10.dp

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
            .padding(top = 18.dp, bottom = bottomPadding),
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
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (plans.isEmpty()) {
                PlanBox(state = PlanBoxState.Empty)
            } else {
                plans.take(previewCount).forEachIndexed { index, plan ->
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

                AnimatedVisibility(
                    visible = expanded,
                    enter = expandVertically(
                        expandFrom = Alignment.Top,
                        animationSpec = tween(
                            durationMillis = 500,
                            easing = FastOutLinearInEasing
                        )
                    ),
                    exit = shrinkVertically(
                        shrinkTowards = Alignment.Bottom,
                        animationSpec = tween(
                            durationMillis = 500,
                            easing = FastOutSlowInEasing
                        )
                    )
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        plans.drop(previewCount).forEachIndexed { extraIndex, plan ->
                            key(
                                "${plan.procedureName}-${plan.daysSince}-${plan.downtimePhase}"
                            ) {
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
            }
        }

        if (hasMore) {
            Text(
                text = if (expanded) "접기" else "더보기",
                style = CherrishTheme.typography.body2R13,
                color = CherrishTheme.colors.gray500,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .noRippleClickable {
                        expanded = !expanded
                    }
            )
        }
    }
}

@Composable
private fun UpcomingPlanSection(
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
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = CherrishTheme.colors.gray0)
            .padding(horizontal = 15.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "다가오는 일정",
            style = CherrishTheme.typography.body1M14,
            color = CherrishTheme.colors.gray700
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
            .padding(horizontal = 9.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))

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

        Spacer(modifier = Modifier.height(40.dp))

        CherrishButton(
            text = "관리 일정을 추가해보세요 !",
            onClick = onAddPlanClick
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    CherrishTheme {
        HomeScreen(
            uiState = HomeUiState.fake,
            paddingValues = PaddingValues(0.dp),
            onUpcomingPlanClick = {},
            onAddPlanClick = {}
        )
    }
}
