package com.cherrish.android.presentation.challenge.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.core.common.extension.dropShadow
import com.cherrish.android.core.designsystem.component.gaugebar.CherrishGaugeBar
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.presentation.challenge.missionprogress.CherryType
import com.cherrish.android.presentation.home.type.CherrishGaugeType
import kotlinx.collections.immutable.toImmutableList

@Composable
fun ChallengeMissionProgressCherrygrowth(
    cherryType: CherryType,
    gaugeStep: Int,
    remainingGuideText: String,
    challengeProgress: Int,
    modifier: Modifier = Modifier
) {
    val colors = listOf(CherrishTheme.colors.red200, CherrishTheme.colors.gray0)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .dropShadow(
                shape = RoundedCornerShape(10.dp),
                blur = 10.dp,
                offsetX = 0.dp,
                offsetY = 0.dp,
                spread = 0.dp,
                color = CherrishTheme.colors.shadow
            )
            .clip(RoundedCornerShape(10.dp))
            .background(
                brush = Brush.linearGradient(
                    colors = colors,
                    start = Offset(x = 0f, y = 0f),
                    end = Offset(x = 0f, y = 230f)
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CherryGrowthSection(
            cherryType = cherryType,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, top = 16.dp)
        )

        Image(
            painter = painterResource(id = cherryType.imageRes),
            contentDescription = null
        )

        Text(
            text = remainingGuideText,
            color = CherrishTheme.colors.gray800,
            style = CherrishTheme.typography.body2R13
        )

        HorizontalDivider(
            color = CherrishTheme.colors.gray300,
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 14.dp)
        )

        CherryGrowthProgressSection(
            challengeProgress = challengeProgress,
            gaugeStep = gaugeStep,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp)
                .padding(bottom = 10.dp)
        )
    }
}

@Composable
private fun CherryGrowthSection(
    cherryType: CherryType,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = "Lv.${cherryType.step}",
            color = CherrishTheme.colors.gray900,
            style = CherrishTheme.typography.body1M14
        )

        Text(
            text = cherryType.stageName,
            color = CherrishTheme.colors.gray900,
            style = CherrishTheme.typography.body1M14
        )
    }
}

@Composable
private fun CherryGrowthProgressSection(
    challengeProgress: Int,
    gaugeStep: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(space = 6.dp)
        ) {
            Text(
                text = "챌린지 달성률",
                color = CherrishTheme.colors.gray900,
                style = CherrishTheme.typography.body1M14
            )

            Text(
                text = "$challengeProgress%",
                color = CherrishTheme.colors.gray900,
                style = CherrishTheme.typography.body1M14
            )
        }

        CherrishGaugeBar(
            currentStep = gaugeStep,
            gauges = CherrishGaugeType.entries.toImmutableList()

        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ChallengeMissionProgressCherrygrowthPreview() {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(space = 12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 18.dp)
    ) {
        items(
            items = listOf(
                Triple(first = CherryType.MONGRONG, second = 4, third = 25),
                Triple(first = CherryType.PPODUK, second = 3, third = 50),
                Triple(first = CherryType.BBANGBBANG, second = 2, third = 75),
                Triple(first = CherryType.KKUKKU, second = 0, third = 100)
            )
        ) { (type, remain, progress) ->
            val remainingGuide =
                if (type == CherryType.KKUKKU) {
                    "챌린지 완료까지 ${remain}개의 미션을 수행해야 해요!"
                } else {
                    "체리가 크려면 ${remain}개의 미션을 수행해야 해요!"
                }
            ChallengeMissionProgressCherrygrowth(
                cherryType = type,
                gaugeStep = 0,
                remainingGuideText = remainingGuide,
                challengeProgress = progress
            )

            HorizontalDivider(
                color = CherrishTheme.colors.gray800,
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 12.dp)
            )
        }
    }
}
