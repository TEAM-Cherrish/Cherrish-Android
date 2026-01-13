package com.cherrish.android.presentation.calendar.procedure.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.R
import com.cherrish.android.core.common.extension.noRippleClickable
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.presentation.calendar.procedure.model.BasicProcedureCardTokens
import com.cherrish.android.presentation.calendar.procedure.model.ProcedureCardDisplayMode
import com.cherrish.android.presentation.calendar.procedure.model.ProcedureCardTokens
import com.cherrish.android.presentation.calendar.procedure.model.SelectableProcedureCardTokens

@Composable
private fun procedureCardTokens(
    displayMode: ProcedureCardDisplayMode
): ProcedureCardTokens {
    return when (displayMode) {
        ProcedureCardDisplayMode.Basic -> {
            BasicProcedureCardTokens(
                selectedContainerColor = CherrishTheme.colors.gray300,
                unselectedContainerColor = CherrishTheme.colors.gray0,
                selectedBorderColor = CherrishTheme.colors.gray500,
                unselectedBorderColor = CherrishTheme.colors.gray500
            )
        }

        ProcedureCardDisplayMode.Selectable -> {
            SelectableProcedureCardTokens(
                selectedContainerColor = CherrishTheme.colors.green1,
                unselectedContainerColor = CherrishTheme.colors.gray0,
                selectedBorderColor = CherrishTheme.colors.green3,
                unselectedBorderColor = CherrishTheme.colors.gray500,
                selectedCheckIconResId = R.drawable.icon_check_circular_green,
                unselectedCheckIconResId = R.drawable.ic_check_circular
            )
        }
    }
}

@Composable
fun ProcedureCard(
    title: String,
    description: String,
    durationText: String,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    displayMode: ProcedureCardDisplayMode = ProcedureCardDisplayMode.Basic
) {
    val tokens = procedureCardTokens(displayMode)

    val containerColor =
        if (isSelected) tokens.selectedContainerColor else tokens.unselectedContainerColor
    val borderColor =
        if (isSelected) tokens.selectedBorderColor else tokens.unselectedBorderColor

    val shape = RoundedCornerShape(10.dp)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape)
            .background(color = containerColor, shape = shape)
            .border(width = 1.dp, color = borderColor, shape = shape)
            .noRippleClickable(onClick = onCardClick)
            .padding(horizontal = 14.dp, vertical = 12.dp)
    ) {
        ProcedureCardTitle(
            title = title,
            description = description,
            isSelected = isSelected,
            tokens = tokens
        )

        Spacer(modifier = Modifier.size(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProcedureCardDuration(durationText = durationText)
        }
    }
}

@Composable
private fun ProcedureCardTitle(
    title: String,
    description: String,
    isSelected: Boolean,
    tokens: ProcedureCardTokens
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = CherrishTheme.typography.title1SB18,
            color = CherrishTheme.colors.gray1000
        )

        if (tokens is SelectableProcedureCardTokens) {
            val checkIconResId = if (isSelected) {
                tokens.selectedCheckIconResId
            } else {
                tokens.unselectedCheckIconResId
            }

            Icon(
                imageVector = ImageVector.vectorResource(id = checkIconResId),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
    }

    Spacer(modifier = Modifier.size(2.dp))

    Text(
        text = description,
        style = CherrishTheme.typography.body3R12,
        color = CherrishTheme.colors.gray700
    )
}

@Composable
private fun ProcedureCardDuration(
    durationText: String
) {
    Icon(
        imageVector = ImageVector.vectorResource(id = R.drawable.ic_clock),
        contentDescription = null,
        tint = CherrishTheme.colors.gray700
    )
    Text(
        text = durationText,
        style = CherrishTheme.typography.body2R13,
        color = CherrishTheme.colors.gray700
    )
}

@Preview(showBackground = true)
@Composable
private fun BasicProcedureCardPreview() {
    CherrishTheme {
        var isSelected by remember { mutableStateOf(false) }

        ProcedureCard(
            title = "레이저 토닝",
            description = "색소 개선 | 토닝",
            durationText = "다운타임* 3-5일",
            onCardClick = { isSelected = !isSelected },
            isSelected = isSelected,
            displayMode = ProcedureCardDisplayMode.Basic
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SelectableProcedureCardPreview() {
    CherrishTheme {
        var isSelected by remember { mutableStateOf(false) }

        ProcedureCard(
            title = "레이저 토닝",
            description = "색소 개선 | 토닝",
            durationText = "다운타임* 3-5일",
            onCardClick = { isSelected = !isSelected },
            isSelected = isSelected,
            displayMode = ProcedureCardDisplayMode.Selectable
        )
    }
}
