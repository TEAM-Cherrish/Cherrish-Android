package com.cherrish.android.core.designsystem.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

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

/**
 * Preview that displays the app's red color tokens (Red100–Red800) as labeled text samples.
 *
 * Each label is rendered using the theme's body1_m_14 typography and the corresponding color token.
 */
@Preview
@Composable
fun CherrishRedColorsPreview() {
    CherrishTheme {
        Column {
            Text("Red100", style = CherrishTheme.typography.body1_m_14, color = CherrishTheme.colors.Red100)
            Text("Red200", style = CherrishTheme.typography.body1_m_14, color = CherrishTheme.colors.Red200)
            Text("Red300", style = CherrishTheme.typography.body1_m_14, color = CherrishTheme.colors.Red300)
            Text("Red400", style = CherrishTheme.typography.body1_m_14, color = CherrishTheme.colors.Red400)
            Text("Red500", style = CherrishTheme.typography.body1_m_14, color = CherrishTheme.colors.Red500)
            Text("Red600", style = CherrishTheme.typography.body1_m_14, color = CherrishTheme.colors.Red600)
            Text("Red700", style = CherrishTheme.typography.body1_m_14, color = CherrishTheme.colors.Red700)
            Text("Red800", style = CherrishTheme.typography.body1_m_14, color = CherrishTheme.colors.Red800)
        }
    }
}

/**
 * Shows a vertical preview of the Cherrish gray color tokens.
 *
 * Renders a Column of Text items labeled Gray0 through Gray1000 using CherrishTheme typography and each corresponding color token; Gray0 and Gray100 are displayed on a Gray1000 background to illustrate contrast.
 */
@Preview
@Composable
fun CherrishGrayColorsPreview() {
    CherrishTheme {
        Column {
            Text("Gray0", style = CherrishTheme.typography.body1_m_14, color = CherrishTheme.colors.Gray0,
                modifier = Modifier.background(CherrishTheme.colors.Gray1000))
            Text("Gray100", style = CherrishTheme.typography.body1_m_14, color = CherrishTheme.colors.Gray100,
                modifier = Modifier.background(CherrishTheme.colors.Gray1000))
            Text("Gray200", style = CherrishTheme.typography.body1_m_14, color = CherrishTheme.colors.Gray200)
            Text("Gray300", style = CherrishTheme.typography.body1_m_14, color = CherrishTheme.colors.Gray300)
            Text("Gray400", style = CherrishTheme.typography.body1_m_14, color = CherrishTheme.colors.Gray400)
            Text("Gray500", style = CherrishTheme.typography.body1_m_14, color = CherrishTheme.colors.Gray500)
            Text("Gray600", style = CherrishTheme.typography.body1_m_14, color = CherrishTheme.colors.Gray600)
            Text("Gray700", style = CherrishTheme.typography.body1_m_14, color = CherrishTheme.colors.Gray700)
            Text("Gray800", style = CherrishTheme.typography.body1_m_14, color = CherrishTheme.colors.Gray800)
            Text("Gray900", style = CherrishTheme.typography.body1_m_14, color = CherrishTheme.colors.Gray900)
            Text("Gray1000", style = CherrishTheme.typography.body1_m_14, color = CherrishTheme.colors.Gray1000)
        }
    }
}

/**
 * Displays a preview of the theme's Green color tokens.
 *
 * Renders three text samples labeled "Green1" through "Green3" using the theme's body1_m_14 typography
 * and the corresponding CherrishTheme color tokens.
 */
@Preview
@Composable
fun CherrishGreenColorsPreview() {
    CherrishTheme {
        Column {
            Text("Green1", style = CherrishTheme.typography.body1_m_14, color = CherrishTheme.colors.Green1)
            Text("Green2", style = CherrishTheme.typography.body1_m_14, color = CherrishTheme.colors.Green2)
            Text("Green3", style = CherrishTheme.typography.body1_m_14, color = CherrishTheme.colors.Green3)
        }
    }
}