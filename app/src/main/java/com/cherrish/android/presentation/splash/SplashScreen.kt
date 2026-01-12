package com.cherrish.android.presentation.splash

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.core.designsystem.theme.CherrishTheme

@Composable
fun SplashScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    val gradationColors = listOf(CherrishTheme.colors.gradation, CherrishTheme.colors.gradation2)

    Column(
        modifier = modifier
            .fillMaxSize()
            .drawBehind {
                val brush = Brush.linearGradient(
                    colors = gradationColors,
                    start = Offset(size.width / 2f, size.height / 2f),
                )
                drawRect(brush)
            }
            .padding(top = 109.dp + paddingValues.calculateTopPadding())
    ) {
        // TODO: 임시 텍스트, 로고 들어갈 예정
        Text(
            text = "Cherrish",
            style = CherrishTheme.typography.title1M18,
            color = CherrishTheme.colors.red600,
            modifier = Modifier
                .padding(start = 33.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "디데이를 기준으로,\n다운타임 회복을 관리하는 뷰티 캘린더",
            style = CherrishTheme.typography.title1M18,
            color = CherrishTheme.colors.gray600,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )

        // TODO: 추후 이미지 들어갈 예정
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    CherrishTheme {
        SplashScreen(
            paddingValues = PaddingValues(top = 109.dp)
        )
    }
}
