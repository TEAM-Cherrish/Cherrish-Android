package com.cherrish.android.presentation.challenge.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.core.designsystem.component.gaugebar.CherrishGaugeBar
import com.cherrish.android.core.designsystem.component.type.CherrishGaugeType
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.presentation.challenge.missionprogress.CherryType
import kotlinx.collections.immutable.toImmutableList

@Composable
private fun CherryGrowthSection(
    modifier: Modifier = Modifier,
    cherryType: CherryType
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = "Lv.${cherryType.level}",
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
    modifier: Modifier = Modifier,
    challengeProgress: Int,
    cherryType: CherryType
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
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
        currentStep = cherryType.step,
        gauges = CherrishGaugeType.entries.toImmutableList()
    )
}


@Composable
fun ChallengeMissionProgressCherrygrowth(
    cherryType: CherryType,
    remainingRoutines: Int,
    modifier: Modifier = Modifier,
    challengeProgress: Int,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp)),
       horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CherryGrowthSection(
            cherryType = cherryType
        )
        Image(
            painter = painterResource(id = cherryType.imageRes),
            contentDescription = null,
        )




        Text(
            text = "체리가 크려면 ${remainingRoutines}개의 미션을 수행해야 해요!",
            color = CherrishTheme.colors.gray800,
            style = CherrishTheme.typography.body2R13,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center

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
            cherryType = cherryType
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun ChallengeMissionProgressCherrygrowthLv0Preview() {
    ChallengeMissionProgressCherrygrowth(
        cherryType = CherryType.MONGRONG,
        remainingRoutines = 0,
        challengeProgress = 0,
        modifier = Modifier.padding(18.dp)
    )
}
@Preview(showBackground = true)
@Composable
private fun ChallengeMissionProgressCherrygrowthLv1Preview() {
    ChallengeMissionProgressCherrygrowth(
        cherryType = CherryType.PPODUK,
        remainingRoutines = 0,
        challengeProgress = 25,
        modifier = Modifier.padding(18.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun ChallengeMissionProgressCherrygrowthLv2Preview() {
    ChallengeMissionProgressCherrygrowth(
        cherryType = CherryType.CHOKCHOK,
        remainingRoutines = 2,
        challengeProgress = 50,
        modifier = Modifier.padding(18.dp)
    )
}
@Preview(showBackground = true)
@Composable
private fun ChallengeMissionProgressCherrygrowthLv3Preview() {
    ChallengeMissionProgressCherrygrowth(
        cherryType = CherryType.BBANGBBANG,
        remainingRoutines = 2,
        challengeProgress = 75,
        modifier = Modifier.padding(18.dp)
    )
}
@Preview(showBackground = true)
@Composable
private fun ChallengeMissionProgressCherrygrowthLv4Preview() {
    ChallengeMissionProgressCherrygrowth(
        cherryType = CherryType.KKUKKU,
        remainingRoutines = 2,
        challengeProgress = 100,
        modifier = Modifier.padding(18.dp)
    )
}
