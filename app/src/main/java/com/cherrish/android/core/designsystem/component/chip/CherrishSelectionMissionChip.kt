package com.cherrish.android.core.designsystem.component.chip

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.R
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.core.designsystem.theme.gray500
import com.cherrish.android.core.designsystem.theme.red700
import com.cherrish.android.core.designsystem.type.CherrishSelectionChipStyle

@Composable
fun CherrishMissionCard(
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
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp, horizontal = 8.dp)
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
                modifier = Modifier.align(Alignment.TopEnd)

            )

            Text(
                text = text,
                color = textColor,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(top = 44.dp, start = 8.dp, bottom = 6.dp)
            )
        }
    }
}

@Preview
@Composable
private fun CherrishMissionCardPreview() {
    CherrishTheme {
        var isSelected by remember { mutableStateOf(value = false) }

        CherrishMissionCard(
            text = "반신욕 20분",
            onClick = { isSelected = !isSelected },
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp, horizontal = 7.dp),
            horizontalArrangement = Arrangement.spacedBy(space = 12.dp)
        ) {
            CherrishMissionCard(
                text = "반신욕 20분",
                onClick = { isSelected = !isSelected },
                isSelected = isSelected
            )

            CherrishMissionCard(
                text = "반신욕 30분",
                onClick = { isSelected = !isSelected },
                isSelected = isSelected

            )
        }
    }
}
