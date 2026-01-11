package com.cherrish.android.core.designsystem.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.R
import com.cherrish.android.core.common.extension.noRippleClickable
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.core.designsystem.theme.gray500
import com.cherrish.android.core.designsystem.theme.red700
import com.cherrish.android.core.designsystem.type.CherrishSelectionChipStyle

@Composable
fun CherrishSelectionBaseChip(
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
        CherrishSelectionChipStyle.SELECTIONCHIP -> PaddingValues(
            horizontal = 10.dp,
            vertical = 30.dp
        )

        CherrishSelectionChipStyle.MISSIONCARD -> PaddingValues(horizontal = 7.dp, vertical = 6.dp)
    }

    Column(
        modifier = modifier
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

@Composable
fun CherrishSelectionChip(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false
) {
    val textColor = if (isSelected) CherrishTheme.colors.gray800 else CherrishTheme.colors.gray700

    CherrishSelectionBaseChip(
        onClick = onClick,
        modifier = modifier,
        isSelected = isSelected,
        style = CherrishSelectionChipStyle.SELECTIONCHIP
    ) {
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 40.dp),
            color = textColor,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun CherrishMissionCardChip(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false
) {
    val textColor = if (isSelected) CherrishTheme.colors.gray800 else CherrishTheme.colors.gray700

    CherrishSelectionBaseChip(
        onClick = onClick,
        modifier = modifier,
        isSelected = isSelected,
        style = CherrishSelectionChipStyle.MISSIONCARD
    ) {
        Column(
            modifier = Modifier
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(
                    id = if (isSelected) {
                        R.drawable.ic_radiobtn_selected
                    } else {
                        R.drawable.ic_radiobtn_default
                    }
                ),
                tint = if (isSelected) red700 else gray500,
                contentDescription = null,
                modifier = Modifier.weight(1f).padding(start = 110.dp)
            )

            Text(
                text = text,
                color = textColor,
                modifier = Modifier
                    .weight(weight = 1f)
            )
        }
    }
}

@Preview
@Composable
private fun CherrishSelectionChipPreview() {
    CherrishTheme {
        var isSelected by remember { mutableStateOf(value = false) }

        CherrishSelectionChip(
            text = "여드름 ∙ 트러블",

            onClick = { isSelected = !isSelected },
            modifier = Modifier
                .fillMaxWidth()
                .padding(60.dp),
            isSelected = isSelected
        )
    }
}

@Preview
@Composable
private fun CherrishMissionCardPreview() {
    CherrishTheme {
        var isSelected by remember { mutableStateOf(value = false) }

        CherrishMissionCardChip(
            text = "반신욕 20분",

            onClick = { isSelected = !isSelected },
            modifier = Modifier
                .width(width = 148.dp)
                .height(height = 80.dp),
            isSelected = isSelected
        )
    }
}

@Preview
@Composable
private fun CherrishMissionCardRowPreview() {
    CherrishTheme {
        var isSelected by remember { mutableStateOf(value = false) }

        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(space = 12.dp)
        ) {
            CherrishMissionCardChip(
                text = "반신욕 20분",

                onClick = { isSelected = !isSelected },
                modifier = Modifier
                    .width(width = 148.dp)
                    .height(height = 80.dp),
                isSelected = isSelected
            )
            CherrishMissionCardChip(
                text = "반신욕 30분",
                onClick = { isSelected = !isSelected },
                modifier = Modifier
                    .width(width = 148.dp)
                    .height(height = 80.dp),
                isSelected = isSelected

            )
        }
    }
}
