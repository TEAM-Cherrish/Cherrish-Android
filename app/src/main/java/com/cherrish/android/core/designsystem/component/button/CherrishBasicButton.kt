package com.cherrish.android.core.designsystem.component.button

import android.graphics.Paint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.core.designsystem.type.CherrishButtonStyle

@Composable
fun CherrishBasicButton(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    style: CherrishButtonStyle = CherrishButtonStyle.PRIMARY,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val cherrishColor = CherrishTheme.colors
    val isPressed by interactionSource.collectIsPressedAsState()

    val pressedScale = 0.95f

    val scale by animateFloatAsState(
        targetValue = if (isPressed) pressedScale else 1f,
        animationSpec = tween(durationMillis = 80),
        label = "button-scale"
    )

    val backgroundColor = remember(enabled, style) {
        when (style) {
            CherrishButtonStyle.PRIMARY -> {
                when {
                    !enabled -> cherrishColor.gray200
                    else -> cherrishColor.red700
                }
            }
            CherrishButtonStyle.SECONDARY -> {
                cherrishColor.gray400
            }
        }
    }

    val textColor = remember(enabled, style) {
        when (style) {
            CherrishButtonStyle.PRIMARY -> {
                when {
                    !enabled -> cherrishColor.gray600
                    else -> cherrishColor.gray0
                }
            }
            CherrishButtonStyle.SECONDARY -> {
                cherrishColor.gray700
            }
        }
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .scale(scale)
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor)
            .clickable(
                enabled = enabled,
                indication = null,
                interactionSource = interactionSource,
                onClick = onClick
            )
            .padding(10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = CherrishTheme.typography.title2SB16,
            color = textColor
        )
    }
}

@Preview
@Composable
private fun CherrishBasicButtonsPreview() {
    CherrishTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            CherrishBasicButton(
                title = "다음",
                onClick = {},
            )
            CherrishBasicButton(
                title = "다음",
                onClick = {},
                enabled = false
            )
        }
    }
}

@Preview
@Composable
private fun CherrishBasicButtonsInteractionPreview() {
    CherrishTheme {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = CherrishTheme.colors.gray0)
                .padding(50.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CherrishBasicButton(
                title = "다운타임 없이 일정 추가",
                onClick = {},
                style = CherrishButtonStyle.SECONDARY,
                modifier = Modifier.weight(184f)
            )

            CherrishBasicButton(
                title = "확인",
                onClick = {},
                modifier = Modifier.weight(122f)
            )
        }
    }
}