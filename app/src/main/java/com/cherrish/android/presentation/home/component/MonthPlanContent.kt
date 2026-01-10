package com.cherrish.android.presentation.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.core.designsystem.theme.CherrishTheme

@Composable
fun MonthPlanContent(
    medicalProcedureNameDate: String,
    medicalProcedureName: String,
    dDay: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = medicalProcedureNameDate,
            style = CherrishTheme.typography.title2M16,
            color = CherrishTheme.colors.gray900
        )

        Text(
            text = "•",
            style = CherrishTheme.typography.title2M16,
            color = CherrishTheme.colors.gray900
        )

        Text(
            text = medicalProcedureName,
            style = CherrishTheme.typography.title2SB16,
            color = CherrishTheme.colors.gray900
        )

        DdayChip(
            dDay = dDay
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    CherrishTheme {
        MonthPlanContent(
            medicalProcedureNameDate = "1월 2일",
            medicalProcedureName = "IPL(광선치료)",
            dDay = "D-1"
        )
    }
}
