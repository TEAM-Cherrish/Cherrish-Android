package com.cherrish.android.presentation.calendar.procedure.content

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.presentation.calendar.procedure.component.ProcedureCard
import com.cherrish.android.presentation.calendar.procedure.component.ProcedureTitleSection
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
        displayMode = ProcedureCardDisplayMode.Basic
    ),
    ProcedureCardItemUiModel(
        id = 2L,
        name = "레이저 토닝",
        category = "색소 개선 | 토닝",
        minDowntimeDays = 2,
        maxDowntimeDays = 3,
        displayMode = ProcedureCardDisplayMode.Basic
    ),
    ProcedureCardItemUiModel(
        id = 3L,
        name = "레이저 토닝",
        category = "색소 개선 | 토닝",
        minDowntimeDays = 5,
        maxDowntimeDays = 8,
        displayMode = ProcedureCardDisplayMode.Basic
    ),
    ProcedureCardItemUiModel(
        id = 4L,
        name = "레이저 토닝",
        category = "색소 개선 | 토닝",
        minDowntimeDays = 3,
        maxDowntimeDays = 5,
        displayMode = ProcedureCardDisplayMode.Basic
    ),
    ProcedureCardItemUiModel(
        id = 5L,
        name = "레이저 토닝",
        category = "색소 개선 | 토닝",
        minDowntimeDays = 3,
        maxDowntimeDays = 5,
        displayMode = ProcedureCardDisplayMode.Basic
    ),
    ProcedureCardItemUiModel(
        id = 6L,
        name = "레이저 토닝",
        category = "색소 개선 | 토닝",
        minDowntimeDays = 3,
        maxDowntimeDays = 5,
        displayMode = ProcedureCardDisplayMode.Basic
    ),
    ProcedureCardItemUiModel(
        id = 7L,
        name = "레이저 토닝",
        category = "색소 개선 | 토닝",
        minDowntimeDays = 3,
        maxDowntimeDays = 5,
        displayMode = ProcedureCardDisplayMode.Basic
    )
)

@Composable
fun FilteringContent(
    name: String,
    cardItems: ImmutableList<ProcedureCardItemUiModel>,
    selectedCardId: Long?,
    onCardClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        item {
            ProcedureTitleSection(
                worryName = name
            )
        }

        item {
            Spacer(modifier = Modifier.height(18.dp))
        }

        items(
            items = cardItems,
            key = { it.id }
        ) { item ->
            ProcedureCard(
                procedureName = item.name,
                category = item.category,
                minDowntimeDays = item.minDowntimeDays,
                maxDowntimeDays = item.maxDowntimeDays,
                onCardClick = { onCardClick(item.id) },
                isSelected = selectedCardId == item.id,
                displayMode = item.displayMode,
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .padding(horizontal = 24.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FilteringContentPreview() {
    CherrishTheme {
        var selectedCardId by remember { mutableStateOf<Long?>(null) }

        FilteringContent(
            name = "색소침착",
            cardItems = mockProcedureCardItems,
            selectedCardId = selectedCardId,
            onCardClick = { clickedId ->
                selectedCardId = if (selectedCardId == clickedId) null else clickedId
            }
        )
    }
}
