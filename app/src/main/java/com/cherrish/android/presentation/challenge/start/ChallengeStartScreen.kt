package com.cherrish.android.presentation.challenge.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cherrish.android.R
import com.cherrish.android.core.designsystem.component.button.CherrishButton
import com.cherrish.android.core.designsystem.theme.CherrishTheme

@Composable
fun ChallengeStartRoute(
    paddingValues: PaddingValues,
    onNavigateToRoutine: () -> Unit,
    viewModel: ChallengeStartViewModel = hiltViewModel()
) {
    ChallengeStartScreen(
        paddingValues = paddingValues,
        onNextClick = onNavigateToRoutine
    )
}

@Composable
private fun ChallengeStartScreen(
    paddingValues: PaddingValues,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(CherrishTheme.colors.gray0)
            .padding(horizontal = 17.dp)
            .padding(paddingValues)


    ) {
        Spacer(modifier = Modifier.weight(98f))

        ChallengeStartTitle(modifier = Modifier.fillMaxWidth())

        Spacer(Modifier.weight(20f))

        Image(
            painter = painterResource(id = R.drawable.img_challenge_start),
            contentDescription = null,
            modifier = Modifier.weight(364f)
        )

        Spacer(Modifier.weight(18f))

        ChallengeStartInfo()

        Spacer(Modifier.weight(12f))

        CherrishButton(
            text = "다음",
            onClick = onNextClick
        )

        Spacer(modifier = Modifier.weight(34f))
    }
}

@Composable
private fun ChallengeStartInfo(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_challenge_info),
            contentDescription = null,
            tint = CherrishTheme.colors.gray600
        )
        Text(
            text = "이 챌린지는 설정 시점부터 7일간 진행됩니다.",
            color = CherrishTheme.colors.gray600,
            style = CherrishTheme.typography.body3M12
        )
    }
}

@Composable
private fun ChallengeStartTitle(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(horizontal = 7.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = "이번엔 어떤 루틴으로 관리할까요?",
            color = CherrishTheme.colors.gray1000,
            style = CherrishTheme.typography.headlineSB20
        )

        Text(
            text = "루틴을 지킬수록 체리가 성장해요.",
            color = CherrishTheme.colors.gray800,
            style = CherrishTheme.typography.title2M16
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ChallengeStartScreenPreview() {
    CherrishTheme {
        ChallengeStartScreen(
            paddingValues = PaddingValues(),
            onNextClick = { }
        )
    }
}
