package com.cherrish.android.presentation.calendar.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.presentation.calendar.model.DownTimeStatus
import com.cherrish.android.presentation.calendar.util.getDowntimeColors

@Composable
fun DownTimeStatusItem(
    status: DownTimeStatus,
    modifier: Modifier = Modifier
) {
    val colors = getDowntimeColors(status, CherrishTheme.colors)

    Row(
        modifier = modifier.padding(vertical = 3.dp, horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .clip(shape = CircleShape)
                .background(color = colors.background)
                .border(
                    width = 1.dp,
                    color = colors.border,
                    shape = CircleShape
                )
                .padding(vertical = 3.dp)
        )
        Text(
            text = status.label,
            style = CherrishTheme.typography.body3M12,
            color = CherrishTheme.colors.gray800
        )
    }
}

@Preview
@Composable
private fun DownTimeStatusItemPreview() {
    CherrishTheme {
        Row(
            modifier = Modifier.background(color = CherrishTheme.colors.gray0),
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            DownTimeStatusItem(status = DownTimeStatus.SENSITIVE)
            DownTimeStatusItem(status = DownTimeStatus.CAUTION)
            DownTimeStatusItem(status = DownTimeStatus.RECOVERY)
        }
    }
}
