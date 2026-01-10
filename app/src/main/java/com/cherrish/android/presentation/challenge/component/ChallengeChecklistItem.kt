package com.cherrish.android.presentation.challenge.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun ChallengeChecklistItem(
    @DrawableRes checklistIcon: Int,
    onChecklistClick: () -> Unit,
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
            modifier = Modifier.noRippleClickable(
                onClick = onChecklistClick
            ),
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
        ChallengeChecklistItem(
            checklistIcon = R.drawable.ic_checkbox_inactive,
            onChecklistClick = {},
            checklistContent = "dddd",
            contentColor = CherrishTheme.colors.gray800,
            contentDecoration = TextDecoration.None
        )
    }
}
