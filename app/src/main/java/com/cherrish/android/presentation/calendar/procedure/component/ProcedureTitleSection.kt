package com.cherrish.android.presentation.calendar.procedure.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
    id: Long,
    content: String
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        HorizontalDivider(
            thickness = 1.dp,
            color = CherrishTheme.colors.gray500
        )

        ProcedureTitleWithCaution(
            id = id,
            content = content
        )

        HorizontalDivider(
            thickness = 1.dp,
            color = CherrishTheme.colors.gray500
        )
    }
}

@Composable
private fun ProcedureTitleWithCaution(
    id: Long,
    content: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = CherrishTheme.colors.gray100)
            .padding(horizontal = 25.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = "$content 관련 시술 리스트",
            style = CherrishTheme.typography.title1SB18,
            color = CherrishTheme.colors.gray1000
        )

        CautionDescription()
    }
}

@Preview(showBackground = true)
@Composable
private fun ProcedureTitleSectionPreview() {
    CherrishTheme {
        ProcedureTitleSection(
            id = 1L,
            content = "모공"
        )
    }
}
