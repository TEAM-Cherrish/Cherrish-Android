package com.cherrish.android.presentation.calendar.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.R
import com.cherrish.android.core.common.extension.dropShadow
import com.cherrish.android.core.common.extension.noRippleClickable
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.core.util.rememberFixedDpFontSize
import com.cherrish.android.presentation.calendar.model.CalendarDisplayMode
import com.cherrish.android.presentation.calendar.model.ProcedureInfoModel
import com.cherrish.android.presentation.calendar.util.getProcedureType
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun ProcedureScheduleCard(
    displayMode: CalendarDisplayMode,
    procedureInfo: ImmutableList<ProcedureInfoModel>,
    onProcedureClick: (Long) -> Unit,
    onAddProcedureClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .dropShadow(
                shape = RoundedCornerShape(10.dp),
                color = CherrishTheme.colors.shadow,
                blur = 10.dp,
                offsetX = 0.dp,
                offsetY = 0.dp,
                spread = 0.dp
            )
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = CherrishTheme.colors.gray0, shape = RoundedCornerShape(10.dp))
    ) {
        if (procedureInfo.isEmpty()) {
            EmptyCardView(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(top = 50.dp, bottom = 24.dp),
                onClick = onAddProcedureClick
            )
        } else {
            key(procedureInfo) {
                val listState = rememberLazyListState()
                val showTopGradient = remember {
                    derivedStateOf { listState.canScrollBackward }
                }
                val showBottomGradient = remember {
                    derivedStateOf { listState.canScrollForward }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 19.dp)
                        .padding(top = 8.dp, bottom = 18.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    ScheduleHeader(
                        eventCount = procedureInfo.size,
                        displayMode = displayMode,
                        onClick = onAddProcedureClick
                    )

                    LazyColumn(
                        state = listState,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(
                            items = procedureInfo,
                            key = { it.procedureId }
                        ) { procedure ->
                            ProcedureInfoItem(
                                procedureName = procedure.procedureName,
                                procedureDay = procedure.procedureDay,
                                downTimeDuration = procedure.downTimeDuration,
                                procedureType = getProcedureType(
                                    displayMode = displayMode,
                                    procedureId = procedure.procedureId,
                                    downTimeDuration = procedure.downTimeDuration
                                ),
                                onClick = { onProcedureClick(procedure.procedureId) }
                            )
                        }
                    }
                }

                AnimatedVisibility(
                    visible = showTopGradient.value,
                    enter = fadeIn(),
                    exit = fadeOut(),
                    modifier = Modifier.align(Alignment.TopCenter)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 40.dp)
                            .height(70.dp)
                            .background(
                                Brush.verticalGradient(
                                    colors = persistentListOf(
                                        CherrishTheme.colors.gray0,
                                        CherrishTheme.colors.gray0.copy(alpha = 0.8f),
                                        CherrishTheme.colors.gray0.copy(alpha = 0.5f),
                                        Color.Transparent
                                    )
                                )
                            )
                    )
                }

                AnimatedVisibility(
                    visible = showBottomGradient.value,
                    enter = fadeIn(),
                    exit = fadeOut(),
                    modifier = Modifier.align(Alignment.BottomCenter)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 18.dp)
                            .height(70.dp)
                            .background(
                                Brush.verticalGradient(
                                    colors = persistentListOf(
                                        Color.Transparent,
                                        CherrishTheme.colors.gray0.copy(alpha = 0.5f),
                                        CherrishTheme.colors.gray0.copy(alpha = 0.8f),
                                        CherrishTheme.colors.gray0
                                    )
                                )
                            )
                    )
                }
            }
        }
    }
}

@Composable
private fun ScheduleHeader(
    displayMode: CalendarDisplayMode,
    eventCount: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ScheduleTitle(
            eventCount = eventCount
        )

        when (displayMode) {
            is CalendarDisplayMode.Downtime -> DownTimeStatusIndicator()
            is CalendarDisplayMode.Normal -> {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_plus),
                    contentDescription = null,
                    tint = CherrishTheme.colors.gray600,
                    modifier = Modifier.noRippleClickable(onClick = onClick)
                )
            }
        }
    }
}

@Composable
private fun ScheduleTitle(
    eventCount: Int,
    modifier: Modifier = Modifier
) {
    val fixedTitleFontSize = rememberFixedDpFontSize(CherrishTheme.typography.body1M14.fontSize)
    val fixedCountFontSize = rememberFixedDpFontSize(CherrishTheme.typography.body1R14.fontSize)

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "일정",
            style = CherrishTheme.typography.body1M14.copy(
                fontSize = fixedTitleFontSize
            ),
            color = CherrishTheme.colors.gray1000
        )

        Text(
            text = "・",
            style = CherrishTheme.typography.body1R14.copy(
                fontSize = fixedCountFontSize
            ),
            color = CherrishTheme.colors.gray1000
        )

        Text(
            text = "${eventCount}개",
            style = CherrishTheme.typography.body1R14.copy(
                fontSize = fixedCountFontSize
            ),
            color = CherrishTheme.colors.gray1000
        )
    }
}

@Composable
private fun EmptyCardView(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_calendar_empty_view),
            contentDescription = null,
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = "오늘 예정된 일정이 없어요.",
            color = CherrishTheme.colors.gray600,
            style = CherrishTheme.typography.body1R14
        )

        Spacer(modifier = Modifier.height(40.dp))

        AddProcedureButton(
            onClick = onClick
        )
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
                        procedureName = "보톡스",
                        procedureDay = "1월 10일 토요일",
                        downTimeDuration = 3
                    ),
                    ProcedureInfoModel(
                        procedureId = 3L,
                        procedureName = "울쎄라",
                        procedureDay = "1월 14일 수요일",
                        downTimeDuration = 7
                    ),
                    ProcedureInfoModel(
                        procedureId = 4L,
                        procedureName = "필러",
                        procedureDay = "1월 17일 토요일",
                        downTimeDuration = 5
                    ),
                    ProcedureInfoModel(
                        procedureId = 5L,
                        procedureName = "리프팅 레이저",
                        procedureDay = "1월 21일 수요일",
                        downTimeDuration = 10
                    ),
                    ProcedureInfoModel(
                        procedureId = 6L,
                        procedureName = "피코토닝",
                        procedureDay = "1월 24일 토요일",
                        downTimeDuration = 4
                    ),
                    ProcedureInfoModel(
                        procedureId = 7L,
                        procedureName = "쥬베룩",
                        procedureDay = "1월 28일 수요일",
                        downTimeDuration = 6
                    ),
                    ProcedureInfoModel(
                        procedureId = 8L,
                        procedureName = "스킨보톡스",
                        procedureDay = "1월 31일 토요일",
                        downTimeDuration = 3
                    )
                ),
                onProcedureClick = {},
                onAddProcedureClick = {}
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
                        procedureName = "보톡스",
                        procedureDay = "1월 10일 토요일",
                        downTimeDuration = 3
                    ),
                    ProcedureInfoModel(
                        procedureId = 3L,
                        procedureName = "울쎄라",
                        procedureDay = "1월 14일 수요일",
                        downTimeDuration = 7
                    ),
                    ProcedureInfoModel(
                        procedureId = 4L,
                        procedureName = "필러",
                        procedureDay = "1월 17일 토요일",
                        downTimeDuration = 5
                    ),
                    ProcedureInfoModel(
                        procedureId = 5L,
                        procedureName = "리프팅 레이저",
                        procedureDay = "1월 21일 수요일",
                        downTimeDuration = 10
                    ),
                    ProcedureInfoModel(
                        procedureId = 6L,
                        procedureName = "피코토닝",
                        procedureDay = "1월 24일 토요일",
                        downTimeDuration = 4
                    ),
                    ProcedureInfoModel(
                        procedureId = 7L,
                        procedureName = "쥬베룩",
                        procedureDay = "1월 28일 수요일",
                        downTimeDuration = 6
                    ),
                    ProcedureInfoModel(
                        procedureId = 8L,
                        procedureName = "스킨보톡스",
                        procedureDay = "1월 31일 토요일",
                        downTimeDuration = 3
                    )
                ),
                onProcedureClick = {},
                onAddProcedureClick = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProcedureScheduleCardEmptyPreview() {
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
                procedureInfo = persistentListOf(),
                onProcedureClick = {},
                onAddProcedureClick = {}
            )
        }
    }
}
