package com.cherrish.android.core.designsystem.component.chip

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.core.designsystem.type.CherrishSelectionChipStyle

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
            modifier = Modifier.fillMaxWidth(),
            isSelected = isSelected
        )
    }
}
