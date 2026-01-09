package com.cherrish.android.presentation.calendar.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.R
import com.cherrish.android.core.common.extension.noRippleClickable
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun CalendarMonthHeader(
    onLeftArrowClick: () -> Unit,
    onRightArrowClick: () -> Unit,
    yearMonth: YearMonth,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
            .padding(12.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_calendar_left),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.noRippleClickable(onClick = onLeftArrowClick)
        )

        Text(
            text = "${yearMonth.year}년 ${yearMonth.month.getDisplayName(
                TextStyle.SHORT,
                Locale.getDefault()
            )}",
            color = CherrishTheme.colors.gray1000,
            style = CherrishTheme.typography.title2M16
        )

        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_calendar_right),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.noRippleClickable(onClick = onRightArrowClick)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CalendarMonthHeaderPreview() {
    CherrishTheme {
        CalendarMonthHeader(
            onLeftArrowClick = {},
            onRightArrowClick = {},
            modifier = Modifier,
            yearMonth = YearMonth.now()
        )
    }
}
