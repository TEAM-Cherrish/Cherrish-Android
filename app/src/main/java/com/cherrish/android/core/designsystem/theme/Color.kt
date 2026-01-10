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

val red100 = Color(0xFFFEF7F8)
val red200 = Color(0xFFFFF4F7)
val red300 = Color(0xFFFFE4EB)
val red400 = Color(0xFFFFD3DC)
val red500 = Color(0xFFFFADBD)
val red600 = Color(0xFFFF879C)
val red700 = Color(0xFFFF617B)
val red800 = Color(0xFFFF4A63)

val gray0 = Color(0xFFFFFFFF)
val gray100 = Color(0xFFFCFCFC)
val gray200 = Color(0xFFF7F8F9)
val gray300 = Color(0xFFEFF1F3)
val gray400 = Color(0xFFE9EBED)
val gray500 = Color(0xFFCACDD1)
val gray600 = Color(0xFF9FA4A9)
val gray700 = Color(0xFF73787E)
val gray800 = Color(0xFF464C52)
val gray900 = Color(0xFF26282B)
val gray1000 = Color(0xFF1B1D1F)

val green1 = Color(0xFFFBFFF3)
val green2 = Color(0xFFBEDCB8)
val green3 = Color(0xFF9AD342)

val shadow = Color(0xFF9098A7).copy(alpha = 0.12f)

@Immutable
data class CherrishColors(

    val red100: Color,
    val red200: Color,
    val red300: Color,
    val red400: Color,
    val red500: Color,
    val red600: Color,
    val red700: Color,
    val red800: Color,

    val gray0: Color,
    val gray100: Color,
    val gray200: Color,
    val gray300: Color,
    val gray400: Color,
    val gray500: Color,
    val gray600: Color,
    val gray700: Color,
    val gray800: Color,
    val gray900: Color,
    val gray1000: Color,

    val green1: Color,
    val green2: Color,
    val green3: Color,

    val shadow: Color
)

val defaultCherrishColors = CherrishColors(

    red100 = red100,
    red200 = red200,
    red300 = red300,
    red400 = red400,
    red500 = red500,
    red600 = red600,
    red700 = red700,
    red800 = red800,

    gray0 = gray0,
    gray100 = gray100,
    gray200 = gray200,
    gray300 = gray300,
    gray400 = gray400,
    gray500 = gray500,
    gray600 = gray600,
    gray700 = gray700,
    gray800 = gray800,
    gray900 = gray900,
    gray1000 = gray1000,

    green1 = green1,
    green2 = green2,
    green3 = green3,

    shadow = shadow
)

val LocalCherrishColorsProvider = staticCompositionLocalOf { defaultCherrishColors }

@Preview
@Composable
private fun CherrishRedColorsPreview() {
    CherrishTheme {
        Column {
            Text(
                text = "Red100",
                style = CherrishTheme.typography.body1M14,
                color = CherrishTheme.colors.red100
            )
            Text(
                text = "Red200",
                style = CherrishTheme.typography.body1M14,
                color = CherrishTheme.colors.red200
            )
            Text(
                text = "Red300",
                style = CherrishTheme.typography.body1M14,
                color = CherrishTheme.colors.red300
            )
            Text(
                text = "Red400",
                style = CherrishTheme.typography.body1M14,
                color = CherrishTheme.colors.red400
            )
            Text(
                text = "Red500",
                style = CherrishTheme.typography.body1M14,
                color = CherrishTheme.colors.red500
            )
            Text(
                text = "Red600",
                style = CherrishTheme.typography.body1M14,
                color = CherrishTheme.colors.red600
            )
            Text(
                text = "Red700",
                style = CherrishTheme.typography.body1M14,
                color = CherrishTheme.colors.red700
            )
            Text(
                text = "Red800",
                style = CherrishTheme.typography.body1M14,
                color = CherrishTheme.colors.red800
            )
        }
    }
}

@Preview
@Composable
private fun CherrishGrayColorsPreview() {
    CherrishTheme {
        Column {
            Text(
                text = "Gray0",
                style = CherrishTheme.typography.body1M14,
                color = CherrishTheme.colors.gray0,
                modifier = Modifier.background(CherrishTheme.colors.gray1000)
            )
            Text(
                text = "Gray100",
                style = CherrishTheme.typography.body1M14,
                color = CherrishTheme.colors.gray100,
                modifier = Modifier.background(CherrishTheme.colors.gray1000)
            )
            Text(
                text = "Gray200",
                style = CherrishTheme.typography.body1M14,
                color = CherrishTheme.colors.gray200
            )
            Text(
                text = "Gray300",
                style = CherrishTheme.typography.body1M14,
                color = CherrishTheme.colors.gray300
            )
            Text(
                text = "Gray400",
                style = CherrishTheme.typography.body1M14,
                color = CherrishTheme.colors.gray400
            )
            Text(
                text = "Gray500",
                style = CherrishTheme.typography.body1M14,
                color = CherrishTheme.colors.gray500
            )
            Text(
                text = "Gray600",
                style = CherrishTheme.typography.body1M14,
                color = CherrishTheme.colors.gray600
            )
            Text(
                text = "Gray700",
                style = CherrishTheme.typography.body1M14,
                color = CherrishTheme.colors.gray700
            )
            Text(
                text = "Gray800",
                style = CherrishTheme.typography.body1M14,
                color = CherrishTheme.colors.gray800
            )
            Text(
                text = "Gray900",
                style = CherrishTheme.typography.body1M14,
                color = CherrishTheme.colors.gray900
            )
            Text(
                text = "Gray1000",
                style = CherrishTheme.typography.body1M14,
                color = CherrishTheme.colors.gray1000
            )
            Text(
                text = "Shadow",
                style = CherrishTheme.typography.body1M14,
                color = CherrishTheme.colors.shadow
            )
        }
    }
}

@Preview
@Composable
private fun CherrishGreenColorsPreview() {
    CherrishTheme {
        Column {
            Text(
                text = "Green1",
                style = CherrishTheme.typography.body1M14,
                color = CherrishTheme.colors.green1
            )
            Text(
                text = "Green2",
                style = CherrishTheme.typography.body1M14,
                color = CherrishTheme.colors.green2
            )
            Text(
                text = "Green3",
                style = CherrishTheme.typography.body1M14,
                color = CherrishTheme.colors.green3
            )
        }
    }
}
