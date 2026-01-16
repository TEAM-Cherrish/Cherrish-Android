package com.cherrish.android.presentation.challenge.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.R
import com.cherrish.android.core.common.extension.noRippleClickable
import com.cherrish.android.core.designsystem.theme.CherrishTheme

@Composable
fun ChallengeChecklist(
    isChecked: Boolean,
    onChecklistClick: () -> Unit,
    checklistContent: String,
    modifier: Modifier = Modifier
) {
    val checklistIcon =
        if (isChecked) R.drawable.ic_checkbox_active else R.drawable.ic_checkbox_inactive
    val contentColor =
        if (isChecked) CherrishTheme.colors.gray600 else CherrishTheme.colors.gray800
    val contentDecoration =
        if (isChecked) TextDecoration.LineThrough else TextDecoration.None

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(10.dp))
            .border(
                width = 1.dp,
                color = CherrishTheme.colors.gray500,
                shape = RoundedCornerShape(10.dp)
            )
            .noRippleClickable(onClick = onChecklistClick)
            .background(color = CherrishTheme.colors.gray0)
            .padding(vertical = 12.dp)
            .padding(start = 14.dp)
    ) {
        ChallengeChecklistItem(
            checklistIcon = checklistIcon,
            checklistContent = checklistContent,
            contentColor = contentColor,
            contentDecoration = contentDecoration
        )
    }
}

@Composable
private fun ChallengeChecklistItem(
    @DrawableRes checklistIcon: Int,
    checklistContent: String,
    contentColor: Color,
    contentDecoration: TextDecoration,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = ImageVector.vectorResource(id = checklistIcon),
            contentDescription = null,
            tint = Color.Unspecified
        )

        Text(
            text = checklistContent,
            color = contentColor,
            style = CherrishTheme.typography.body1R14,
            textDecoration = contentDecoration
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    CherrishTheme {
        var isChecked by remember { mutableStateOf(false) }

        ChallengeChecklist(
            isChecked = isChecked,
            onChecklistClick = { isChecked = !isChecked },
            checklistContent = "dddd"
        )
    }
}
