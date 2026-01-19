package com.cherrish.android.presentation.calendar.procedure.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.core.designsystem.theme.CherrishTheme

private val FigmaGentleEasing = CubicBezierEasing(0.4f, 0.0f, 0.2f, 1.0f)

@Composable
fun StepProgressBar(
    totalStep: Int,
    currentStep: Int,
    modifier: Modifier = Modifier
) {
    if (totalStep <= 0) return

    val safeStep = currentStep.coerceIn(0, totalStep - 1)
    var previousStep by remember { mutableIntStateOf(safeStep) }

    val isGoingBack = safeStep < previousStep
    val animatedIndex = if (isGoingBack) previousStep else safeStep

    LaunchedEffect(safeStep) {
        kotlinx.coroutines.delay(800)
        previousStep = safeStep
    }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        repeat(totalStep) { index ->
            val target = when {
                index <= safeStep -> 1f
                else -> 0f
            }

            StepSegment(
                modifier = Modifier.weight(1f),
                targetProgress = target,
                animate = index == animatedIndex
            )
        }
    }
}

@Composable
private fun StepSegment(
    targetProgress: Float,
    animate: Boolean,
    modifier: Modifier = Modifier
) {
    val clampedTarget = targetProgress.coerceIn(0f, 1f)

    val animatable = remember { Animatable(0f) }

    LaunchedEffect(clampedTarget, animate) {
        if (animate) {
            animatable.animateTo(
                targetValue = clampedTarget,
                animationSpec = tween(durationMillis = 800, easing = FigmaGentleEasing)
            )
        } else {
            animatable.snapTo(clampedTarget)
        }
    }

    val progress = animatable.value

    Box(
        modifier = modifier
            .height(4.dp)
            .clip(RoundedCornerShape(76.dp))
            .background(CherrishTheme.colors.gray300)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(progress)
                .height(4.dp)
                .clip(RoundedCornerShape(76.dp))
                .background(CherrishTheme.colors.gray800)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun StepProgressBarPreview() {
    CherrishTheme {
        var step by remember { mutableIntStateOf(0) }

        Column(modifier = Modifier.padding(24.dp)) {
            StepProgressBar(
                totalStep = 4,
                currentStep = step
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = { step = (step - 1).coerceAtLeast(0) },
                    enabled = step > 0,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "이전")
                }

                Button(
                    onClick = { step = (step + 1).coerceAtMost(3) },
                    enabled = step < 3,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "다음")
                }
            }
        }
    }
}
