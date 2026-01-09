package com.cherrish.android.core.designsystem.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import com.cherrish.android.core.common.extension.noRippleClickable
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.core.designsystem.type.CherrishSelectionChipStyle

@Composable
fun CherrishSelectionChip(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    selected: Boolean,

    style: CherrishSelectionChipStyle = CherrishSelectionChipStyle.SELECTIONCHIP,
    content: @Composable () -> Unit
) {
    val backgroundColor = when (style) {
        CherrishSelectionChipStyle.SELECTIONCHIP -> {
            if (selected) CherrishTheme.colors.red200 else CherrishTheme.colors.gray0
        }

        CherrishSelectionChipStyle.MISSIONCARD -> {
            if (selected) CherrishTheme.colors.red100 else CherrishTheme.colors.gray0
        }
    }

    val lineColor = when (style) {
        CherrishSelectionChipStyle.SELECTIONCHIP -> {
            if (selected) CherrishTheme.colors.red500 else CherrishTheme.colors.gray500
        }

        CherrishSelectionChipStyle.MISSIONCARD -> {
            if (selected) CherrishTheme.colors.red500 else CherrishTheme.colors.gray500
        }
    }

    val cornerRadius = when (style) {
        CherrishSelectionChipStyle.SELECTIONCHIP -> 10.dp
        CherrishSelectionChipStyle.MISSIONCARD -> 9.dp
    }

    val paddingValues = when (style) {
        CherrishSelectionChipStyle.SELECTIONCHIP -> PaddingValues(10.dp)
        CherrishSelectionChipStyle.MISSIONCARD -> PaddingValues(horizontal = 7.dp, vertical = 6.dp)
    }

    Row(
        modifier = modifier
            .fillMaxSize()
            .clip(shape = RoundedCornerShape(size = cornerRadius))
            .background(backgroundColor)
            .border(
                width = 1.dp,
                color = lineColor,
                shape = RoundedCornerShape(size = cornerRadius)
            )
            .noRippleClickable(onClick = onClick)
            .padding(paddingValues),
        verticalAlignment = Alignment.CenterVertically
    ) {
        content()
    }
}

@Preview
@Composable
private fun CherrishSelectionChipPreview() {
    CherrishTheme {
        var selected by remember { mutableStateOf(value = false) }
        val textColor = if (selected) CherrishTheme.colors.gray800 else CherrishTheme.colors.gray700

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = CherrishTheme.colors.gray0)
                .padding(vertical = 300.dp, horizontal = 80.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CherrishSelectionChip(
                selected = selected,
                onClick = { selected = !selected }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "여드름 ∙ 트러블",
                        fontSize = 20.sp,
                        style = CherrishTheme.typography.body1M14,
                        color = textColor,

                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CherrishMissionCardPreview() {
    CherrishTheme {
        var selected by remember { mutableStateOf(value = false) }
        val textColor = if (selected) CherrishTheme.colors.gray800 else CherrishTheme.colors.gray700

        val lineColor = if (selected) CherrishTheme.colors.red500 else CherrishTheme.colors.gray500
        val indicatorColor =
            if (selected) CherrishTheme.colors.red700 else CherrishTheme.colors.gray500

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 300.dp, horizontal = 80.dp),
            contentAlignment = Alignment.Center
        ) {
            CherrishSelectionChip(
                selected = selected,
                onClick = { selected = !selected },
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(all = 10.dp)
                ) {
                    Text(
                        text = "반신욕 20분",
                        style = CherrishTheme.typography.body1M14,
                        color = textColor,
                        modifier = Modifier.align(Alignment.BottomStart),
                        fontSize = 15.sp
                    )

                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .size(size = 18.dp)
                            .clip(shape = RoundedCornerShape(percent = 50))
                            .border(
                                width = 1.dp,
                                color = lineColor,
                                shape = RoundedCornerShape(percent = 50)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        if (selected) {
                            Box(
                                modifier = Modifier
                                    .size(size = 8.dp)
                                    .clip(shape = RoundedCornerShape(percent = 50))
                                    .background(indicatorColor)
                            )
                        }
                    }
                }
            }
        }
    }
}
