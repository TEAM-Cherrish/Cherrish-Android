package com.cherrish.android.core.designsystem.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.cherrish.android.core.common.extension.noRippleClickable
import com.cherrish.android.core.designsystem.theme.CherrishTheme
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

    Column(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(size = cornerRadius))
            .background(backgroundColor)
            .border(
                width = 1.dp,
                color = lineColor,
                shape = RoundedCornerShape(size = cornerRadius)
            )
            .noRippleClickable(onClick = onClick)
            .then(other = modifier)

    ) {
        content()
    }
}
