package com.cherrish.android.presentation.calendar.procedure.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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

@Composable
internal fun TreatmentCard(
    title: String,
    description: String,
    durationText: String,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    selectedContainerColor: Color = CherrishTheme.colors.gray500,
    unselectedContainerColor: Color = CherrishTheme.colors.gray0,
    selectedBorderColor: Color = CherrishTheme.colors.gray700,
    unselectedBorderColor: Color = CherrishTheme.colors.gray700,
    showCheckIcon: Boolean = false,
    selectedCheckIconResId: Int = R.drawable.icon_check_circular_green,
    unselectedCheckIconResId: Int = R.drawable.ic_check_circular,
    topRightContent: (@Composable () -> Unit)? = null
) {
    val containerColor = if (isSelected) selectedContainerColor else unselectedContainerColor
    val borderColor = if (isSelected) selectedBorderColor else unselectedBorderColor

    val shape = RoundedCornerShape(10.dp)

    val resolvedTopRightContent: (@Composable () -> Unit)? = when {
        topRightContent != null -> topRightContent
        showCheckIcon -> {
            {
                Icon(
                    imageVector = ImageVector.vectorResource(
                        id = if (isSelected) selectedCheckIconResId else unselectedCheckIconResId
                    ),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
        }
        else -> null
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .noRippleClickable(onClick = onCardClick)
            .clip(shape)
            .background(color = containerColor, shape = shape)
            .border(width = 1.dp, color = borderColor, shape = shape)
            .padding(horizontal = 14.dp, vertical = 10.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
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

                if (resolvedTopRightContent != null) {
                    resolvedTopRightContent()
                }
            }

            Spacer(modifier = Modifier.size(2.dp))

            Text(
                text = description,
                style = CherrishTheme.typography.body3R12,
                color = CherrishTheme.colors.gray700
            )

            Spacer(modifier = Modifier.size(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_clock),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
                Text(
                    text = durationText,
                    style = CherrishTheme.typography.body2R13,
                    color = CherrishTheme.colors.gray700
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TreatmentCardPreview_Basic() {
    CherrishTheme {
        var isSelected by remember { mutableStateOf(false) }

        TreatmentCard(
            title = "레이저 토닝",
            description = "색소 개선 | 토닝",
            durationText = "다운타임* 3-5일",
            onCardClick = { isSelected = !isSelected },
            isSelected = isSelected,
            showCheckIcon = false,
            topRightContent = null,
            selectedContainerColor = CherrishTheme.colors.gray300,
            unselectedContainerColor = CherrishTheme.colors.gray0,
            selectedBorderColor = CherrishTheme.colors.gray500,
            unselectedBorderColor = CherrishTheme.colors.gray500
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TreatmentCardPreview_WithCheck() {
    CherrishTheme {
        var isSelected by remember { mutableStateOf(false) }

        TreatmentCard(
            title = "레이저 토닝",
            description = "색소 개선 | 토닝",
            durationText = "다운타임* 3-5일",
            onCardClick = { isSelected = !isSelected },
            isSelected = isSelected,
            showCheckIcon = true,
            selectedContainerColor = CherrishTheme.colors.green1,
            unselectedContainerColor = CherrishTheme.colors.gray0,
            selectedBorderColor = CherrishTheme.colors.green3,
            unselectedBorderColor = CherrishTheme.colors.gray500,
            selectedCheckIconResId = R.drawable.icon_check_circular_green,
            unselectedCheckIconResId = R.drawable.ic_check_circular
        )
    }
}
