package com.cherrish.android.presentation.calendar.procedure.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.core.designsystem.theme.CherrishTheme

@Composable
fun ProcedureTitleSection(
    procedureName: String,
    modifier: Modifier = Modifier
) {
    HorizontalDivider(
        modifier = Modifier
            .fillMaxWidth(),
        thickness = 1.dp,
        color = CherrishTheme.colors.gray500
    )
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = CherrishTheme.colors.gray100)
            .padding(horizontal = 25.dp, vertical = 20.dp)
    ) {
        Text(
            text = "$procedureName 관련 시술 리스트",
            style = CherrishTheme.typography.title1SB18,
            color = CherrishTheme.colors.gray1000
        )

        Spacer(modifier = Modifier.height(4.dp))

        CautionDescription()
    }
    HorizontalDivider(
        modifier = Modifier
            .fillMaxWidth(),
        thickness = 1.dp,
        color = CherrishTheme.colors.gray500
    )
}

@Preview
@Composable
private fun ProcedureTitleSectionPreview() {
    CherrishTheme {
        ProcedureTitleSection(
            procedureName = "색소 ∙ 잡티"
        )
    }
}
