package com.cherrish.android.presentation.onboarding.information.extensioin

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class AgeSuffixTransformation(
    private val suffix: String = " 세"
) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        if (text.text.isBlank()) {
            return TransformedText(text, OffsetMapping.Companion.Identity)
        }

        val transformed = androidx.compose.ui.text.AnnotatedString(text.text + suffix)

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int = offset
            override fun transformedToOriginal(offset: Int): Int =
                offset.coerceAtMost(text.text.length)
        }

        return TransformedText(transformed, offsetMapping)
    }
}
