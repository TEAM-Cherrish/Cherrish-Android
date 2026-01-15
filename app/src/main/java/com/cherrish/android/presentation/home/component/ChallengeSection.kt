package com.cherrish.android.presentation.home.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.cherrish.android.R
import com.cherrish.android.core.common.extension.dropShadow
import com.cherrish.android.core.designsystem.component.gaugebar.CherrishGaugeBar
import com.cherrish.android.core.designsystem.component.type.CherrishGaugeType
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import kotlinx.collections.immutable.ImmutableList

@Composable
fun ChallengeSection(
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
