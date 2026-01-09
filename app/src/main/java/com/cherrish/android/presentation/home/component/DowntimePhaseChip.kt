package com.cherrish.android.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.core.designsystem.theme.CherrishTheme

@Composable
fun DowntimePhaseChip(
    downtimePhase: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(shape = RoundedCornerShape(20.dp))
            .background(color = CherrishTheme.colors.gray0)
            .padding(horizontal = 8.dp, vertical = 3.dp)
    ) {
        Text(
            text = downtimePhase,
            style = CherrishTheme.typography.body2R13,
            color = CherrishTheme.colors.gray700
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    CherrishTheme {
        Column(
            modifier = Modifier.background(color = CherrishTheme.colors.gray900)
        ) {
            DowntimePhaseChip(
                downtimePhase = "주의기"
            )
        }
    }
}
