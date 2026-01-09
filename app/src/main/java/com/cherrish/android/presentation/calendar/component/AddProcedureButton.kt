package com.cherrish.android.presentation.calendar.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.R
import com.cherrish.android.core.designsystem.component.button.CherrishBasicButton
import com.cherrish.android.core.designsystem.theme.CherrishTheme

@Composable
fun AddProcedureButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
){
    val isPressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = tween(durationMillis = 80),
        label = "button-scale"
    )

    CherrishBasicButton(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .scale(scale)
            .clip(RoundedCornerShape(10.dp))
            .background(color = CherrishTheme.colors.red700)
            .padding(10.dp),
        interactionSource = interactionSource
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_plus),
                contentDescription = null,
                tint = CherrishTheme.colors.gray0
            )
            Text(
                text = "시술 일정 추가하기",
                color = CherrishTheme.colors.gray0,
                style = CherrishTheme.typography.title2SB16
            )
        }
    }
}

@Preview
@Composable
private fun AddProcedureButtonPreview() {
    CherrishTheme {
        AddProcedureButton(
            onClick = {}
        )
    }
}