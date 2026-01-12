package com.cherrish.android.presentation.calendar.procedure.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.core.designsystem.theme.CherrishTheme

@Composable
fun CautionDescription(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "◎",
            modifier = modifier,
            style = CherrishTheme.typography.body3R12,
            color = CherrishTheme.colors.gray600
        )

        Spacer(modifier = Modifier.size(4.dp))

        Text(
            text = "본 정보는 인터넷 빅데이터 검색 및 분석을 통해 " +
                "수집된 정보\n이며, 공식적인 의료 정보가 아닙니다.",
            modifier = modifier,
            style = CherrishTheme.typography.body3R12,
            color = CherrishTheme.colors.gray600
        )
    }
}

@Preview
@Composable
private fun CautionDescriptionPreview() {
    CherrishTheme {
        CautionDescription()
    }
}
