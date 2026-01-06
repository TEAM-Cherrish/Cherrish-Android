package com.cherrish.android.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val CherrishPink = Color(0xFFEB445B)

val Red100 = Color(0xFFFEF7F8)
val Red200 = Color(0xFFFDF2F5)
val Red300 = Color(0xFFFAE0E6)
val Red400 = Color(0xFFF7CCD5)
val Red500 = Color(0xFFF2A4B3)
val Red600 = Color(0xFFEE7E92)
val Red700 = Color(0xFFEC5A72)
val Red800 = Color(0xFFEB445B)

val Gray0 = Color(0xFFFFFFFF)
val Gray100 = Color(0xFFFCFCFC)
val Gray200 = Color(0xFFF7F8F9)
val Gray300 = Color(0xFFEFF1F3)
val Gray400 = Color(0xFFE9EBED)
val Gray500 = Color(0xFFCACDD1)
val Gray600 = Color(0xFF9FA4A9)
val Gray700 = Color(0xFF73787E)
val Gray800 = Color(0xFF464C52)
val Gray900 = Color(0xFF26282B)
val Gray1000 = Color(0xFF1B1D1F)

val Green1 = Color(0xFFFBFFF3)
val Green2 = Color(0xFFBEDCB8)
val Green3 = Color(0xFF9AD342)

@Immutable
data class CherrishColors(
    val CherrishPink: Color,

    val Red100: Color,
    val Red200: Color,
    val Red300: Color,
    val Red400: Color,
    val Red500: Color,
    val Red600: Color,
    val Red700: Color,
    val Red800: Color,

    val Gray0: Color,
    val Gray100: Color,
    val Gray200: Color,
    val Gray300: Color,
    val Gray400: Color,
    val Gray500: Color,
    val Gray600: Color,
    val Gray700: Color,
    val Gray800: Color,
    val Gray900: Color,
    val Gray1000: Color,

    val Green1: Color,
    val Green2: Color,
    val Green3: Color,
)

val defaultCherrishColors = CherrishColors(
    CherrishPink = CherrishPink,

    Red100 = Red100,
    Red200 = Red200,
    Red300 = Red300,
    Red400 = Red400,
    Red500 = Red500,
    Red600 = Red600,
    Red700 = Red700,
    Red800 = Red800,

    Gray0 = Gray0,
    Gray100 = Gray100,
    Gray200 = Gray200,
    Gray300 = Gray300,
    Gray400 = Gray400,
    Gray500 = Gray500,
    Gray600 = Gray600,
    Gray700 = Gray700,
    Gray800 = Gray800,
    Gray900 = Gray900,
    Gray1000 = Gray1000,

    Green1 = Green1,
    Green2 = Green2,
    Green3 = Green3
)

val LocalCherrishColorsProvider = staticCompositionLocalOf { defaultCherrishColors }
