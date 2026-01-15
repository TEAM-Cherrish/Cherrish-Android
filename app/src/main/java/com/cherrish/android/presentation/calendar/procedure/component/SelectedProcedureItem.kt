package com.cherrish.android.presentation.calendar.procedure.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.R
import com.cherrish.android.core.common.extension.noRippleClickable
import com.cherrish.android.core.designsystem.theme.CherrishTheme

@Composable
fun SelectedProcedureItem(
    procedureId: Long,
    procedureName: String,
    minDowntimeDays: Int,
    maxDowntimeDays: Int,
    onDeletedClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(6.dp))
            .background(color = CherrishTheme.colors.gray200)
            .padding(horizontal = 11.dp, vertical = 5.dp),
        verticalAlignment = CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SelectedProcedureTitle(
            procedureName = procedureName,
            minDowntimeDays = minDowntimeDays,
            maxDowntimeDays = maxDowntimeDays,
        )

        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_deletebox),
            contentDescription = null,
            tint = CherrishTheme.colors.gray600,
            modifier = Modifier
                .noRippleClickable(onClick = { onDeletedClick(procedureId) })

        )
    }
}

@Composable
private fun SelectedProcedureTitle(
    procedureName: String,
    minDowntimeDays: Int,
    maxDowntimeDays: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = procedureName,
            color = CherrishTheme.colors.gray800,
            style = CherrishTheme.typography.body1R14
        )

        Text(
            text = "|",
            color = CherrishTheme.colors.gray600,
            style = CherrishTheme.typography.body2R13
        )

        Text(
            text = "다운타임* $minDowntimeDays-$maxDowntimeDays",
            color = CherrishTheme.colors.gray700,
            style = CherrishTheme.typography.body1R14
        )
    }
}

@Preview
@Composable
private fun SelectedProcedureItemPreview() {
    CherrishTheme {
        SelectedProcedureItem(
            procedureId = 1,
            procedureName = "레이저 토닝",
            minDowntimeDays = 3,
            maxDowntimeDays = 5,
            onDeletedClick = {}
        )
    }
}
