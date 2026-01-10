package com.cherrish.android.core.designsystem.component.chip

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.R
import com.cherrish.android.core.common.extension.noRippleClickable
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.core.designsystem.type.CherrishSelectionChipStyle

@Composable
fun CherrishSelectionChip(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    style: CherrishSelectionChipStyle = CherrishSelectionChipStyle.SELECTIONCHIP,
    content: @Composable () -> Unit
) {
    val backgroundColor = when (style) {
        CherrishSelectionChipStyle.SELECTIONCHIP -> {
            if (isSelected) CherrishTheme.colors.red200 else CherrishTheme.colors.gray0
        }

        CherrishSelectionChipStyle.MISSIONCARD -> {
            if (isSelected) CherrishTheme.colors.red100 else CherrishTheme.colors.gray0
        }
    }

    val lineColor = if (isSelected) CherrishTheme.colors.red500 else CherrishTheme.colors.gray500

    val cornerRadius = when (style) {
        CherrishSelectionChipStyle.SELECTIONCHIP -> 10.dp
        CherrishSelectionChipStyle.MISSIONCARD -> 9.dp
    }

    val paddingValues = when (style) {
        CherrishSelectionChipStyle.SELECTIONCHIP -> PaddingValues(10.dp)
        CherrishSelectionChipStyle.MISSIONCARD -> PaddingValues(horizontal = 7.dp, vertical = 6.dp)
    }

    // 최소일떄 패딩

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(size = cornerRadius))
            .background(backgroundColor)
            .border(
                width = 1.dp,
                color = lineColor,
                shape = RoundedCornerShape(size = cornerRadius)
            )
            .noRippleClickable(onClick = onClick)
            .padding(paddingValues)

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

        CherrishSelectionChip(

            onClick = { selected = !selected },
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 60.dp),
            isSelected = selected,
            style = CherrishSelectionChipStyle.SELECTIONCHIP

        ) {
            Text(
                text = "여드름 ∙ 트러블",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 40.dp),
                color = textColor,
                textAlign = TextAlign.Center

            )
        }
    }
}

@Preview
@Composable
private fun CherrishMissionCardPreview() {
    CherrishTheme {
        var selected by remember { mutableStateOf(value = false) }

        val textColor = if (selected) CherrishTheme.colors.gray800 else CherrishTheme.colors.gray700

        CherrishSelectionChip(
            onClick = { selected = !selected },
            modifier = Modifier.fillMaxWidth().padding(all = 30.dp),
            isSelected = selected,
            style = CherrishSelectionChipStyle.MISSIONCARD
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                Text(
                    text = "반신욕 20분",
                    modifier = Modifier.align(Alignment.BottomStart).padding(
                        start = 7.dp,
                        bottom = 6.dp,
                        top = 38.dp
                    ),
                    color = textColor

                )

                Image(
                    painter = painterResource(
                        id = if (selected) {
                            R.drawable.ic_radiobtn_selected
                        } else {
                            R.drawable.ic_radiobtn_default
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.TopEnd)
                )
            }
        }
    }
}
