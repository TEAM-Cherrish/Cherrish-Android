package com.cherrish.android.presentation.challenge.loading

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cherrish.android.R
import com.cherrish.android.core.common.extension.collectLatestSideEffect
import com.cherrish.android.core.designsystem.theme.CherrishTheme

@Composable
fun ChallengeLoadingRoute(
    navigateToChallengeMission: (Int, List<String>) -> Unit,
    paddingValues: PaddingValues,
    viewModel: ChallengeLoadingViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.postAiRecommendations()
    }

    viewModel.sideEffect.collectLatestSideEffect { sideEffect ->
        when (sideEffect) {
            is ChallengeLoadingSideEffect.NavigateToChallengeMission
            -> navigateToChallengeMission(sideEffect.routineId, sideEffect.routines)
        }
    }

    ChallengeLoadingScreen(
        paddingValues = paddingValues
    )
}

@Composable
private fun ChallengeLoadingScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = CherrishTheme.colors.gray0)
            .padding(horizontal = 10.dp)
            .padding(paddingValues)
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(44.dp))

        Icon(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(10.dp),
            imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_left),
            contentDescription = null,
            tint = CherrishTheme.colors.gray1000
        )

        Spacer(modifier = Modifier.weight(84f))

        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = "피부 컨디션",
                color = CherrishTheme.colors.red700,
                style = CherrishTheme.typography.title1SB18
            )

            Text(
                text = "관리 방향을 바탕으로",
                color = CherrishTheme.colors.gray800,
                style = CherrishTheme.typography.title1SB18
            )
        }

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = "TO-DO 미션을 만들고 있어요.",
            color = CherrishTheme.colors.gray800,
            style = CherrishTheme.typography.title1SB18
        )

        Spacer(modifier = Modifier.height(60.dp))

        Image(
            painter = painterResource(id = R.drawable.img_challenge_loading),
            contentDescription = null,
            modifier = Modifier.size(150.dp)
        )

        Spacer(modifier = Modifier.height(80.dp))

        Text(
            text = "잠시만 기다려주세요!",
            color = CherrishTheme.colors.gray800,
            style = CherrishTheme.typography.title2SB16
        )

        Spacer(modifier = Modifier.weight(147f))

        Text(
            text = "AI가 맞춤형 루틴을 제작하고 있어요.",
            color = CherrishTheme.colors.gray600,
            style = CherrishTheme.typography.body3M12
        )

        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun ChallengeLoadingScreenPreview() {
    CherrishTheme {
        ChallengeLoadingScreen(
            paddingValues = PaddingValues()
        )
    }
}
