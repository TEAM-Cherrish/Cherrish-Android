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
import com.cherrish.android.presentation.home.type.DowntimePhase

@Composable
fun PlanBox(
    state: PlanBoxState,
    modifier: Modifier = Modifier
) {
    val verticalPadding = when (state) {
        PlanBoxState.Empty -> 12.dp
        is PlanBoxState.Filled -> 13.dp
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(10.dp))
            .border(
                width = 1.dp,
                color = CherrishTheme.colors.gray400,
                shape = RoundedCornerShape(10.dp)
            )
            .background(color = CherrishTheme.colors.gray0)
            .padding(start = 14.dp)
            .padding(vertical = verticalPadding)
    ) {
        when (state) {
            PlanBoxState.Empty -> {
                Text(
                    text = "진행 중인 일정이 없어요",
                    style = CherrishTheme.typography.body1M14,
                    color = CherrishTheme.colors.gray600
                )
            }

            is PlanBoxState.Filled -> {
                PlanContent(
                    medicalProcedureName = state.medicalProcedureName,
                    medicalProcedureNameDate = state.medicalProcedureNameDate,
                    downtimePhase = state.downtimePhase
                )
            }
        }
    }
}

@Composable
private fun PlanContent(
    medicalProcedureName: String,
    medicalProcedureNameDate: Int,
    downtimePhase: DowntimePhase,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = medicalProcedureName,
            style = CherrishTheme.typography.body1M14,
            color = CherrishTheme.colors.gray900
        )

        Text(
            text = "•",
            style = CherrishTheme.typography.body1M14,
            color = CherrishTheme.colors.gray900
        )

        Text(
            text = "회복 ${medicalProcedureNameDate}일차",
            style = CherrishTheme.typography.body1R14,
            color = CherrishTheme.colors.gray900
        )

        DowntimePhaseChip(
            downtimePhase = downtimePhase
        )
    }
}

@Composable
private fun DowntimePhaseChip(
    downtimePhase: DowntimePhase,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(shape = RoundedCornerShape(20.dp))
            .background(color = CherrishTheme.colors.gray0)
            .border(
                width = 1.dp,
                color = CherrishTheme.colors.gray400,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 8.dp, vertical = 2.dp)
    ) {
        Text(
            text = downtimePhase.phaseName,
            style = CherrishTheme.typography.body3R12,
            color = CherrishTheme.colors.gray700
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    CherrishTheme {
        PlanBox(
            state = PlanBoxState.Filled(
                medicalProcedureName = "슈링크",
                medicalProcedureNameDate = 2,
                downtimePhase = DowntimePhase.SENSITIVE
            )
        )
    }
}
