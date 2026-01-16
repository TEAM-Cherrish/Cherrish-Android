package com.cherrish.android.presentation.home.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cherrish.android.core.common.extension.dropShadow
import com.cherrish.android.core.common.extension.noRippleClickable
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.presentation.home.model.PlanUiModel
import kotlinx.collections.immutable.ImmutableList

@Composable
fun PlanBoxSection(
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
                            durationMillis = 400,
                            easing = FastOutLinearInEasing
                        )
                    ) + fadeIn(),
                    exit = shrinkVertically(
                        shrinkTowards = Alignment.Bottom,
                        animationSpec = tween(
                            durationMillis = 400,
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
