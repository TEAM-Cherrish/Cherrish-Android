package com.cherrish.android.presentation.calendar.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.R
import com.cherrish.android.core.designsystem.theme.CherrishTheme

@Composable
fun ProcedureTextField(
    value: String,
    onValueChange: (String) -> Unit,
    onSearchAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(30.dp)),
        textStyle = CherrishTheme.typography.body1M14,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchAction()
                keyboardController?.hide()
            }
        ),
        singleLine = true,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = CherrishTheme.colors.gray200,
                        shape = RoundedCornerShape(30.dp)
                    )
                    .padding(horizontal = 13.dp, vertical = 8.dp)
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = "원하는 시술을 검색하세요.",
                        style = CherrishTheme.typography.body1R14,
                        color = CherrishTheme.colors.gray600,
                        modifier = Modifier.align(Alignment.CenterStart)
                    )
                }
                innerTextField()

                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_search),
                    contentDescription = null,
                    tint = CherrishTheme.colors.gray500,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }
        }
    )
}

@Preview()
@Composable
private fun ProcedureTextFieldPreview() {
    CherrishTheme {
        var text by remember { mutableStateOf("") }

        ProcedureTextField(
            value = text,
            onValueChange = { text = it },
            onSearchAction = {}
        )
    }
}
