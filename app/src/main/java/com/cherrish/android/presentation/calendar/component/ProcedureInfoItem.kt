package com.cherrish.android.presentation.calendar.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.core.common.extension.noRippleClickable
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.presentation.calendar.model.ProcedureType
import com.cherrish.android.presentation.calendar.util.ProcedureColors
import com.cherrish.android.presentation.calendar.util.getProcedureColors

@Composable
fun ProcedureInfoItem(
    procedureName: String,
    procedureDay: String,
    downTimeDuration: Int?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    procedureType: ProcedureType = ProcedureType.ACTIVE
) {
    val colors = getProcedureColors(procedureType, CherrishTheme.colors)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(6.dp))
            .background(color = colors.background)
            .border(
                width = 1.dp,
                color = colors.border,
                shape = RoundedCornerShape(6.dp)
            )
            .noRippleClickable(onClick = onClick)
            .padding(vertical = 11.dp, horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ProcedureLabel(
            colors = colors,
            procedureName = procedureName
        )

        ProcedureScheduleInfo(
            colors = colors,
            procedureDay = procedureDay,
            downTimeDuration = downTimeDuration
        )
    }
}

@Composable
private fun ProcedureLabel(
    colors: ProcedureColors,
    procedureName: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.height(IntrinsicSize.Max),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        VerticalDivider(
            color = colors.divider,
            thickness = 3.dp,
            modifier = Modifier
                .fillMaxHeight()
                .clip(shape = RoundedCornerShape(4.dp))
        )
        Text(
            text = procedureName,
            style = CherrishTheme.typography.body1SB14,
            color = colors.text,
            modifier = Modifier.padding(vertical = 7.dp)
        )
    }
}

@Composable
private fun ProcedureScheduleInfo(
    colors: ProcedureColors,
    procedureDay: String,
    downTimeDuration: Int?
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        Text(
            text = procedureDay,
            style = CherrishTheme.typography.body3M12,
            color = colors.text
        )
        Text(
            text = downTimeDuration?.let { "다운타임 ${it}일" } ?: "-",
            style = CherrishTheme.typography.body3R12,
            color = colors.text
        )
    }
}

@Preview
@Composable
private fun ProcedureInfoItemPreview() {
    CherrishTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ProcedureInfoItem(
                procedureType = ProcedureType.ACTIVE,
                procedureName = "레이저토닝",
                procedureDay = "1월 7일 수요일",
                downTimeDuration = 5,
                onClick = {}
            )
            ProcedureInfoItem(
                procedureType = ProcedureType.INACTIVE,
                procedureName = "레이저토닝",
                procedureDay = "1월 7일 수요일",
                downTimeDuration = null,
                onClick = {}
            )
        }
    }
}
