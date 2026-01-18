package com.cherrish.android.presentation.challenge

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.R
import com.cherrish.android.core.common.extension.noRippleClickable
import com.cherrish.android.core.designsystem.theme.CherrishTheme

@Composable
fun ChallengeLoadingScreen(
    modifier: Modifier = Modifier,
    onCloseClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(44f))

        Icon(
            modifier = Modifier
                .noRippleClickable(onClick = onCloseClick)
                .align(Alignment.Start),
            imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_left),
            contentDescription = null,
            tint = CherrishTheme.colors.gray1000
        )

        Spacer(modifier = Modifier.weight(84f))

        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(color = CherrishTheme.colors.red700)
                ) {
                    append("피부 컨디션 ")
                }
                withStyle(
                    style = SpanStyle(color = CherrishTheme.colors.gray800)
                ) {
                    append("관리 방향을 바탕으로")
                }
            },
            style = CherrishTheme.typography.title1SB18,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.weight(2f))

        Text(
            text = "TO-DO 미션을 만들고 있어요.",
            color = CherrishTheme.colors.gray800,
            style = CherrishTheme.typography.title1SB18
        )

        Spacer(modifier = Modifier.weight(60f))

        Image(
            painter = painterResource(id = R.drawable.img_challenge_loading),
            contentDescription = null
        )

        Spacer(modifier = Modifier.weight(80f))

        Text(
            text = "잠시만 기다려주세요!",
            color = CherrishTheme.colors.gray800,
            style = CherrishTheme.typography.title2SB16,
        )

        Spacer(modifier = Modifier.weight(147f))

        Text(
            text = "AI가 맞춤형 루틴을 제작하고 있어요.",
            color = CherrishTheme.colors.gray600,
            style = CherrishTheme.typography.body3M12
        )

        Spacer(modifier = Modifier.weight(30f))
    }
}

@Preview(showBackground = true)
@Composable
private fun ChallengeLoadingScreenPreview() {
    CherrishTheme {
        ChallengeLoadingScreen(onCloseClick = {})
    }
}
