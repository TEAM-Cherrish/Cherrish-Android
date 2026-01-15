package com.cherrish.android.core.designsystem.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.core.common.extension.noRippleClickable
import com.cherrish.android.core.designsystem.theme.CherrishTheme

@Composable
fun CherrishSelectionChip(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false
) {
    val backgroundColor =
        if (isSelected) CherrishTheme.colors.red200 else CherrishTheme.colors.gray0

    val lineColor = if (isSelected) CherrishTheme.colors.red500 else CherrishTheme.colors.gray500

    val textColor = if (isSelected) CherrishTheme.colors.gray800 else CherrishTheme.colors.gray700

    val textStyle = if (isSelected) {
        CherrishTheme.typography.body1SB14
    } else {
        CherrishTheme.typography.body1M14
    }

    Column(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(size = 10.dp))
            .background(backgroundColor)
            .border(
                width = 1.dp,
                color = lineColor,
                shape = RoundedCornerShape(size = 10.dp)
            )
            .noRippleClickable(onClick = onClick)
            .then(other = modifier)
    ) {
        Text(
            text = text,
            style = textStyle,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 30.dp, horizontal = 10.dp),
            color = textColor,
            textAlign = TextAlign.Center
        )
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
                .fillMaxWidth(),
            isSelected = isSelected
        )
    }
}
