package com.cherrish.android.presentation.home.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.cherrish.android.R
import com.cherrish.android.core.common.extension.dropShadow
import com.cherrish.android.core.designsystem.component.button.CherrishButton
import com.cherrish.android.core.designsystem.component.gaugebar.CherrishGaugeBar
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.presentation.home.type.CherrishGaugeType
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun ChallengeSection(
    gauges: ImmutableList<CherrishGaugeType>,
    modifier: Modifier = Modifier,
    currentStep: Int = 0,
    @DrawableRes imageRes: Int? = R.drawable.img_challenge_lv2,
    onChallengeStartClick: () -> Unit = {},
    challengeName: String? = "웰니스 • 마음챙김",
    challengeRate: Int = 0
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        if (currentStep != 0 && imageRes != null) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .align(alignment = Alignment.TopEnd)
                    .size(144.dp)
                    .offset(y = (-42).dp)
                    .zIndex(1f),
                alignment = Alignment.TopEnd
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_app_logo_title),
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(10.dp))

            if (currentStep == 0) {
                NoChallenge(
                    currentStep = currentStep,
                    gauges = gauges,
                    onChallengeStartClick = onChallengeStartClick
                )
            } else {
                Challenge(
                    currentStep = currentStep,
                    gauges = gauges,
                    challengeName = challengeName,
                    challengeRate = challengeRate
                )
            }
        }
    }
}

@Composable
private fun Challenge(
    currentStep: Int,
    gauges: ImmutableList<CherrishGaugeType>,
    challengeName: String?,
    challengeRate: Int,
    modifier: Modifier = Modifier
) {
    val safeStep = currentStep.coerceIn(1, gauges.size)

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
            .padding(18.dp)
    ) {
        Text(
            text = "진행중인 챌린지",
            style = CherrishTheme.typography.body1M14,
            color = CherrishTheme.colors.gray700
        )

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = challengeName ?: "",
                style = CherrishTheme.typography.title2M16,
                color = CherrishTheme.colors.gray900
            )

            ChallengeChip(
                percent = challengeRate
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        CherrishGaugeBar(
            currentStep = safeStep,
            gauges = gauges
        )
    }
}

@Composable
private fun ChallengeChip(
    percent: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = "$percent%",
        style = CherrishTheme.typography.body3M12,
        color = CherrishTheme.colors.red700,
        modifier = modifier
            .clip(shape = RoundedCornerShape(30.dp))
            .border(
                width = 1.dp,
                color = CherrishTheme.colors.red700,
                shape = RoundedCornerShape(30.dp)
            )
            .padding(horizontal = 7.dp, vertical = 1.dp)
    )
}

@Composable
private fun NoChallenge(
    currentStep: Int,
    gauges: ImmutableList<CherrishGaugeType>,
    onChallengeStartClick: () -> Unit,
    modifier: Modifier = Modifier
) {
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
            .padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "챌린지를 시작해봐요!",
            style = CherrishTheme.typography.body1M14,
            color = CherrishTheme.colors.gray700
        )

        CherrishGaugeBar(
            currentStep = currentStep,
            gauges = gauges
        )

        CherrishButton(
            text = "챌린지 시작하기",
            onClick = onChallengeStartClick,
            modifier = Modifier.padding(horizontal = 6.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview_ChallengeSection_NoChallenge() {
    CherrishTheme {
        ChallengeSection(
            imageRes = R.drawable.img_challenge_lv2,
            currentStep = 0,
            gauges = CherrishGaugeType.entries.toImmutableList(),
            onChallengeStartClick = {},
            challengeName = "아침 루틴"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview_ChallengeSection_InProgress() {
    CherrishTheme {
        ChallengeSection(
            imageRes = R.drawable.img_challenge_lv2,
            currentStep = 2,
            gauges = CherrishGaugeType.entries.toImmutableList(),
            onChallengeStartClick = {},
            challengeName = "웰니스 • 마음챙김"
        )
    }
}
