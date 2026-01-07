package com.cherrish.android.presentation.calendar.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.core.common.extension.noRippleClickable
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun CalendarHeader(
    onLeftArrowClick: () -> Unit,
    onRightArrowClick: () -> Unit,
    yearMonth: YearMonth,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        Icon(
            imageVector = Icons.Default.KeyboardArrowLeft,
            contentDescription = "Left Arrow",
            tint = Color.Black,
            modifier = Modifier.noRippleClickable(onClick = onLeftArrowClick)
        )

        Text(
            text = "${yearMonth.year}년 ${yearMonth.month.getDisplayName(
                TextStyle.SHORT,
                Locale.getDefault()
            )}",
            color = Color.Black
        )

        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "Right Arrow",
            tint = Color.Black,
            modifier = Modifier.noRippleClickable(onClick = onRightArrowClick)
        )
    }
}

@Preview
@Composable
private fun CalendarHeaderPreview() {
    CherrishTheme {
        CalendarHeader(
            onLeftArrowClick = {},
            onRightArrowClick = {},
            modifier = Modifier,
            yearMonth = YearMonth.now()
        )
    }
}
