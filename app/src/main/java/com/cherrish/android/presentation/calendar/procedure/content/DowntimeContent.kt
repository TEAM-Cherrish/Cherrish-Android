package com.cherrish.android.presentation.calendar.procedure.content

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.presentation.calendar.procedure.component.CautionDescription
import com.cherrish.android.presentation.calendar.procedure.component.ProcedureCard
import com.cherrish.android.presentation.calendar.procedure.model.ProcedureCardDisplayMode
import com.cherrish.android.presentation.calendar.procedure.model.ProcedureCardItemUiModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

/* TODO: 삭제 예정 */
private val mockProcedureCardItems = persistentListOf(
    ProcedureCardItemUiModel(
        id = 1L,
        name = "레이저 토닝",
        category = "색소 개선 | 토닝",
        minDowntimeDays = 3,
        maxDowntimeDays = 5,
        displayMode = ProcedureCardDisplayMode.Selectable
    ),
    ProcedureCardItemUiModel(
        id = 2L,
        name = "레이저 토닝",
        category = "색소 개선 | 토닝",
        minDowntimeDays = 2,
        maxDowntimeDays = 3,
        displayMode = ProcedureCardDisplayMode.Selectable
    ),
    ProcedureCardItemUiModel(
        id = 3L,
        name = "레이저 토닝",
        category = "색소 개선 | 토닝",
        minDowntimeDays = 5,
        maxDowntimeDays = 8,
        displayMode = ProcedureCardDisplayMode.Selectable
    ),
    ProcedureCardItemUiModel(
        id = 4L,
        name = "레이저 토닝",
        category = "색소 개선 | 토닝",
        minDowntimeDays = 3,
        maxDowntimeDays = 5,
        displayMode = ProcedureCardDisplayMode.Selectable
    ),
    ProcedureCardItemUiModel(
        id = 5L,
        name = "레이저 토닝",
        category = "색소 개선 | 토닝",
        minDowntimeDays = 3,
        maxDowntimeDays = 5,
        displayMode = ProcedureCardDisplayMode.Selectable
    ),
    ProcedureCardItemUiModel(
        id = 6L,
        name = "레이저 토닝",
        category = "색소 개선 | 토닝",
        minDowntimeDays = 3,
        maxDowntimeDays = 5,
        displayMode = ProcedureCardDisplayMode.Selectable
    ),
    ProcedureCardItemUiModel(
        id = 7L,
        name = "레이저 토닝",
        category = "색소 개선 | 토닝",
        minDowntimeDays = 3,
        maxDowntimeDays = 5,
        displayMode = ProcedureCardDisplayMode.Selectable
    )
)

@Composable
fun DowntimeContent(
    cardItems: ImmutableList<ProcedureCardItemUiModel>,
    selectedCardId: Long?,
    onCardClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 20.dp)
    ) {
        item {
            Text(
                text = "필요에 맞게 다운타임을 조정할 수 있어요.",
                style = CherrishTheme.typography.title1SB18
            )
        }

        item {
            Spacer(modifier = Modifier.height(14.dp))
        }

        itemsIndexed(
            items = cardItems,
            key = { _, item -> item.id }
        ) { _, item ->
            ProcedureCard(
                procedureName = item.name,
                category = item.category,
                minDowntimeDays = item.minDowntimeDays,
                maxDowntimeDays = item.maxDowntimeDays,
                onCardClick = { onCardClick(item.id) },
                isSelected = selectedCardId == item.id,
                displayMode = item.displayMode,
                modifier = Modifier.padding(top = 10.dp)
            )
        }

        item {
            CautionDescription(
                description = "본 정보는 의료 상담이나 진단을 대체하지 않으며,\n" +
                    "실제 다운타임 및 회복 과정은 개인에 따라 다를 수 있습니다.\n" +
                    "정확한 내용은 의료진 상담을 통해 확인하세요.",
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DowntimeContentPreview() {
    CherrishTheme {
        var selectedCardId by remember { mutableStateOf<Long?>(null) }

        DowntimeContent(
            cardItems = mockProcedureCardItems,
            selectedCardId = selectedCardId,
            onCardClick = { clickedId ->
                selectedCardId = if (selectedCardId == clickedId) null else clickedId
            }
        )
    }
}
