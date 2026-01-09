package com.cherrish.android.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.core.designsystem.theme.CherrishTheme

@Composable
fun MonthPlanBox(
    medicalProcedureNameDate: String,
    medicalProcedureName: String,
    dDay: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(10.dp))
            .border(
                width = 1.dp,
                color = CherrishTheme.colors.gray500,
                shape = RoundedCornerShape(10.dp)
            )
            .background(color = CherrishTheme.colors.gray0)
            .padding(vertical = 10.dp)
            .padding(start = 10.dp)
    ) {
        Row(
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
}

@Composable
private fun DdayChip(
    dDay: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(shape = RoundedCornerShape(20.dp))
            .background(color = CherrishTheme.colors.gray300)
            .padding(horizontal = 10.dp)
    ) {
        Text(
            text = dDay,
            style = CherrishTheme.typography.body2R13,
            color = CherrishTheme.colors.gray900
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    CherrishTheme {
        MonthPlanBox(
            medicalProcedureNameDate = "1월 2일",
            medicalProcedureName = "IPL(광선치료)",
            dDay = "D-1"
        )
    }
}
