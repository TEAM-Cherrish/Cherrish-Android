package com.cherrish.android.presentation.calendar.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.core.common.extension.dropShadow
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.presentation.calendar.model.CalendarDisplayMode
import com.cherrish.android.presentation.calendar.model.ProcedureInfoModel
import com.cherrish.android.presentation.calendar.model.ProcedureType
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun ProcedureScheduleCard(
    displayMode: CalendarDisplayMode,
    procedureInfo: ImmutableList<ProcedureInfoModel>,
    onClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .dropShadow(
                shape = RoundedCornerShape(10.dp),
                color = Color(0xFF9098A7).copy(alpha = 0.12f),
                blur = 10.dp,
                offsetX = 0.dp,
                offsetY = 0.dp,
                spread = 0.dp
            )
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = CherrishTheme.colors.gray0, shape = RoundedCornerShape(10.dp))
            .padding(vertical = 18.dp, horizontal = 19.dp)
    ) {
        ScheduleHeader(displayMode = displayMode)

        Spacer(modifier = Modifier.height(15.dp))

        procedureInfo.forEachIndexed { index, procedure ->
            val procedureType = when (displayMode) {
                is CalendarDisplayMode.Normal -> ProcedureType.ACTIVE
                is CalendarDisplayMode.Downtime -> {
                    if (displayMode.selectedProcedureId == procedure.procedureId) {
                        ProcedureType.ACTIVE
                    } else {
                        ProcedureType.INACTIVE
                    }
                }
            }

            ProcedureInfoItem(
                procedureName = procedure.procedureName,
                procedureDay = procedure.procedureDay,
                downTimeDuration = procedure.downTimeDuration,
                procedureType = procedureType,
                onClick = { onClick(procedure.procedureId) }
            )

            if (index < procedureInfo.lastIndex) {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
private fun ScheduleHeader(
    displayMode: CalendarDisplayMode,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "일정",
            style = CherrishTheme.typography.body1M14,
            color = CherrishTheme.colors.gray1000
        )

        when (displayMode) {
            is CalendarDisplayMode.Downtime -> DownTimeStatusIndicator()
            is CalendarDisplayMode.Normal -> { }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProcedureScheduleCardNormalPreview() {
    CherrishTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            ProcedureScheduleCard(
                displayMode = CalendarDisplayMode.Normal(
                    procedureCountByDate = mapOf()
                ),
                procedureInfo = persistentListOf(
                    ProcedureInfoModel(
                        procedureId = 1L,
                        procedureName = "레이저토닝",
                        procedureDay = "1월 7일 수요일",
                        downTimeDuration = 5
                    ),
                    ProcedureInfoModel(
                        procedureId = 2L,
                        procedureName = "레이저토닝",
                        procedureDay = "1월 7일 수요일",
                        downTimeDuration = 5
                    ),
                    ProcedureInfoModel(
                        procedureId = 3L,
                        procedureName = "울쎄라",
                        procedureDay = "1월 7일 수요일",
                        downTimeDuration = 3
                    )
                ),
                onClick = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProcedureScheduleCardDowntimePreview() {
    CherrishTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            ProcedureScheduleCard(
                displayMode = CalendarDisplayMode.Downtime(
                    downtimeByDate = mapOf(),
                    selectedProcedureId = 1L
                ),
                procedureInfo = persistentListOf(
                    ProcedureInfoModel(
                        procedureId = 1L,
                        procedureName = "레이저토닝",
                        procedureDay = "1월 7일 수요일",
                        downTimeDuration = 5
                    ),
                    ProcedureInfoModel(
                        procedureId = 2L,
                        procedureName = "레이저토닝",
                        procedureDay = "1월 7일 수요일",
                        downTimeDuration = 10
                    ),
                    ProcedureInfoModel(
                        procedureId = 3L,
                        procedureName = "울쎄라",
                        procedureDay = "1월 7일 수요일",
                        downTimeDuration = 3
                    )
                ),
                onClick = {}
            )
        }
    }
}
