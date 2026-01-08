package com.cherrish.android.core.designsystem.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.core.designsystem.theme.CherrishTheme

@Composable
fun CherrishBasicChip(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    selected: Boolean,
    content: @Composable () -> Unit
) {
    val cherrishColor = CherrishTheme.colors

    val backgroundColor = if (selected) cherrishColor.red200 else cherrishColor.gray0

    val lineColor = if (selected) cherrishColor.red500 else cherrishColor.gray500

    Row(
        modifier = modifier.fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(backgroundColor)
            .border(width = 1.dp, color = lineColor, shape = RoundedCornerShape(10.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        content()
    }
}

@Preview
@Composable
private fun CherrishBasicChipPreview() {
    CherrishTheme {
        val cherrishColor = CherrishTheme.colors
        var selected by remember { mutableStateOf(false) }
        val textColor = if (selected) cherrishColor.gray800 else cherrishColor.gray700

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = CherrishTheme.colors.gray0)
                .padding(50.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(Modifier.padding(20.dp))

            CherrishBasicChip(
                selected = selected,
                onClick = { selected = !selected }
            ) {
                Text(
                    text = "여드름 . 트러블",
                    style = CherrishTheme.typography.body1M14,
                    color = textColor,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
