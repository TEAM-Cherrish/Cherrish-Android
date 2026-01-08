package com.cherrish.android.core.designsystem.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.sp
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
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(9.dp))
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
private fun CherrishSelectionChipPreview() {
    CherrishTheme {
        val cherrishColor = CherrishTheme.colors
        var selected by remember { mutableStateOf(false) }
        val textColor = if (selected) cherrishColor.gray800 else cherrishColor.gray700

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = CherrishTheme.colors.gray0)
                .padding(vertical = 300.dp, horizontal = 80.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            CherrishBasicChip(
                selected = selected,
                onClick = { selected = !selected },
            ) { Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "여드름 ∙ 트러블",
                    fontSize = 20.sp,
                    style = CherrishTheme.typography.body1M14,
                    color = textColor,

                    textAlign = TextAlign.Center
                )}
            }
        }
    }
}

@Preview
@Composable
private fun CherrishMissionCardPreview() {
    CherrishTheme {
        val cherrishColor = CherrishTheme.colors
        var selected by remember { mutableStateOf(false) }
        val textColor = if (selected) cherrishColor.gray800 else cherrishColor.gray700

        val lineColor = if (selected) cherrishColor.red500 else cherrishColor.gray500
        val indicatorColor = if (selected) cherrishColor.red700 else cherrishColor.gray500

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 300.dp, horizontal = 80.dp),
            contentAlignment = Alignment.Center
        ) {
            CherrishBasicChip(
                selected = selected,
                onClick = { selected = !selected },
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    Text(
                        text = "반신욕 20분",
                        style = CherrishTheme.typography.body1M14,
                        color = textColor,
                        modifier = Modifier.align(Alignment.BottomStart), fontSize = 15.sp,
                    )

                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .size(18.dp)
                            .clip(RoundedCornerShape(50))
                            .border(
                                width = 1.dp,
                                color = lineColor,
                                shape = RoundedCornerShape(50)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        if (selected) {
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .clip(RoundedCornerShape(50))
                                    .background(indicatorColor)
                            )
                        }
                    }
                }
            }
        }
    }
}
