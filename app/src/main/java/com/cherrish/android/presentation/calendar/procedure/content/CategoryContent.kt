package com.cherrish.android.presentation.calendar.procedure.content

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.presentation.calendar.procedure.component.SelectionSection
import com.cherrish.android.presentation.calendar.procedure.model.ProcedureWorryUiModel
import kotlin.collections.indexOfFirst
import kotlin.collections.map
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

/* TODO: 삭제 예정 */
private val mockWorries: ImmutableList<ProcedureWorryUiModel> = persistentListOf(
    ProcedureWorryUiModel(id = 1, content = "피부결 ∙ 각질"),
    ProcedureWorryUiModel(id = 2, content = "색소 ∙ 잡티"),
    ProcedureWorryUiModel(id = 3, content = "홍조"),
    ProcedureWorryUiModel(id = 4, content = "탄력 ∙ 주름"),
    ProcedureWorryUiModel(id = 5, content = "모공"),
    ProcedureWorryUiModel(id = 6, content = "트러블")
)

@Composable
fun CategoryContent(
    worries: ImmutableList<ProcedureWorryUiModel>,
    selectedWorryId: Long?,
    onWorryClick: (Long) -> Unit
) {
    val items = worries.map { it.content }.toImmutableList()

    val selectedIndex = worries.indexOfFirst { it.id == selectedWorryId }
        .takeIf { it >= 0 }

    SelectionSection(
        title = "요즘 가장 신경 쓰이는\n피부 고민은 무엇인가요?",
        description = "선택한 고민을 기준으로 시술 정보를 정리해줘요.",
        descriptionTextStyle = CherrishTheme.typography.body1M14,
        items = items,
        selectedIndex = selectedIndex,
        onItemClick = { index ->
            onWorryClick(worries[index].id)
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun CategoryContentPreview() {
    CherrishTheme {
        var selectedWorryId by remember { mutableStateOf<Long?>(null) }

        CategoryContent(
            worries = mockWorries,
            selectedWorryId = selectedWorryId,
            onWorryClick = { clickedId ->
                selectedWorryId = if (selectedWorryId == clickedId) null else clickedId
            }
        )
    }
}
