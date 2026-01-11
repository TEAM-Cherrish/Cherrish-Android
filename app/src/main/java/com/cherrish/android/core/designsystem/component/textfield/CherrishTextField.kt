package com.cherrish.android.core.designsystem.component.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.core.designsystem.theme.CherrishTheme

@Composable
fun CherrishTextField(
    value: String,
    onValueChanged: (String) -> Unit,
    placeholder: String,
    placeholderTextStyle: TextStyle,
    inputTextStyle: TextStyle,
    inputTextColor: Color,
    onDoneAction: () -> Unit = {},
    paddingValues: PaddingValues,
    keyboardImeAction: ImeAction = ImeAction.Done,
    keyboardType: KeyboardType = KeyboardType.Unspecified,
    placeholderTextColor: Color = CherrishTheme.colors.gray500,
    modifier: Modifier = Modifier
) {
    BasicTextField(
        value = value,
        onValueChange = {
            onValueChanged(it)
        },
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(color = CherrishTheme.colors.gray0)
            .border(
                width = 1.dp,
                color = CherrishTheme.colors.gray500,
                shape = RoundedCornerShape(10.dp)
            ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = keyboardImeAction
        ),
        keyboardActions = KeyboardActions(
            onDone = { onDoneAction() }
        ),
        textStyle = inputTextStyle.copy(
            color = inputTextColor
        ),
        decorationBox = { innerTextField ->
            Column(
                modifier = Modifier.padding(paddingValues)
            ) {
                Box(
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = placeholderTextColor,
                            style = placeholderTextStyle
                        )
                    }
                    innerTextField()
                }
            }
        }
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF212121)
@Composable
private fun CherrishBasicTextFieldPadding16Preview() {
    var text by remember { mutableStateOf("") }
    CherrishTheme {
        CherrishTextField(
            value = text,
            onValueChanged = { text = it },
            placeholder = "김체리",
            placeholderTextStyle = CherrishTheme.typography.body1R14,
            placeholderTextColor = CherrishTheme.colors.gray500,
            inputTextStyle = CherrishTheme.typography.body1R14,
            inputTextColor = CherrishTheme.colors.gray1000,
            paddingValues = PaddingValues(horizontal = 16.dp, vertical = 10.dp)
        )
    }
}
