package com.cherrish.android.core.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.TextUnit

@Composable
fun rememberFixedDpFontSize(fontSize: TextUnit): TextUnit {
    val density = LocalDensity.current
    return with(density) {
        fontSize.toDp().toSp()
    }
}
