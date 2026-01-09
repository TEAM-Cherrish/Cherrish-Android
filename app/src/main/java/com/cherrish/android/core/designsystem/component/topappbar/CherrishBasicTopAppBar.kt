package com.cherrish.android.core.designsystem.component.topappbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.core.designsystem.theme.CherrishTheme

@Composable
fun CherrishBasicTopAppBar(
    modifier: Modifier = Modifier,
    title: String? = null,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable () -> Unit = {},
    backgroundColor: Color = CherrishTheme.colors.gray0
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(horizontal = 19.dp, vertical = 9.dp)
    ) {
        // 좌측 아이콘
        Row(
            modifier = Modifier.align(Alignment.CenterStart),
            verticalAlignment = Alignment.CenterVertically
        ) {
            navigationIcon()
        }

        // 타이틀
        if (title != null) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = title,
                color = CherrishTheme.colors.gray1000,
                style = CherrishTheme.typography.title1SB18
            )
        }

        // 우측 아이콘
        Row(
            modifier = Modifier.align(Alignment.CenterEnd),
            verticalAlignment = Alignment.CenterVertically
        ) {
            actions()
        }
    }
}

@Preview
@Composable
private fun CherrishTopAppBarPreview() {
    CherrishTheme {
        CherrishBasicTopAppBar(
            title = "시술 여부 선택"
        )
    }
}
