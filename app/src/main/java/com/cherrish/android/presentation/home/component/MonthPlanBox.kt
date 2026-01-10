package com.cherrish.android.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.core.common.extension.dropShadow
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
            .dropShadow(
                shape = RoundedCornerShape(10.dp),
                color = Color(0xFF9098A7).copy(alpha = 0.12f), // TODO: 디자인시스템에 컬러 추가 후 변경 예정
                blur = 10.dp,
                offsetX = 0.dp,
                offsetY = 0.dp,
                spread = 0.dp
            )
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
        MonthPlanContent(
            medicalProcedureNameDate = medicalProcedureNameDate,
            medicalProcedureName = medicalProcedureName,
            dDay = dDay
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    CherrishTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp)
        ) {
            MonthPlanBox(
                medicalProcedureNameDate = "1월 2일",
                medicalProcedureName = "IPL(광선치료)",
                dDay = "D-1"
            )
        }
    }
}
