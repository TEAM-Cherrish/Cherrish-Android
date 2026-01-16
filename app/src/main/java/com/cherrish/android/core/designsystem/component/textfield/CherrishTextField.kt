package com.cherrish.android.core.designsystem.component.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.core.designsystem.theme.CherrishTheme

@Composable
fun CherrishTextField(
    value: String,
    onValueChange: (String) -> Unit,
    roundedCornerShape: RoundedCornerShape,
    placeholder: String,
    placeholderTextStyle: TextStyle,
    inputTextStyle: TextStyle,
    inputTextColor: Color,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    keyboardImeAction: ImeAction = ImeAction.Done,
    onNextAction: () -> Unit = {},
    onDoneAction: () -> Unit = {},
    keyboardType: KeyboardType = KeyboardType.Unspecified,
    placeholderTextColor: Color = CherrishTheme.colors.gray500,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    val textStyle = remember(inputTextStyle, inputTextColor) {
        inputTextStyle.copy(color = inputTextColor)
    }

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        visualTransformation = visualTransformation,
        modifier = modifier
            .clip(roundedCornerShape)
            .background(color = CherrishTheme.colors.gray0)
            .border(
                width = 1.dp,
                color = CherrishTheme.colors.gray500,
                shape = roundedCornerShape
            ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = keyboardImeAction
        ),
        keyboardActions = KeyboardActions(
            onNext = { onNextAction() },
            onDone = { onDoneAction() }
        ),
        textStyle = textStyle,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier.padding(paddingValues),
                contentAlignment = Alignment.CenterStart
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        color = placeholderTextColor,
                        style = placeholderTextStyle,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                innerTextField()
            }
        }
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF212121)
@Composable
private fun CherrishTextFieldPreview() {
    var text by remember { mutableStateOf("") }
    CherrishTheme {
        CherrishTextField(
            value = text,
            onValueChange = { text = it },
            roundedCornerShape = RoundedCornerShape(10.dp),
            placeholder = "김체리",
            placeholderTextStyle = CherrishTheme.typography.body1R14,
            placeholderTextColor = CherrishTheme.colors.gray500,
            inputTextStyle = CherrishTheme.typography.body1R14,
            inputTextColor = CherrishTheme.colors.gray1000,
            paddingValues = PaddingValues(horizontal = 16.dp, vertical = 10.dp)
        )
    }
}
