package com.cherrish.android.core.designsystem.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.cherrish.android.R

val cherrishFontBold = FontFamily(Font(R.font.pretendard_bold))
val cherrishFontSemiBold = FontFamily(Font(R.font.pretendard_semibold))
val cherrishFontMedium = FontFamily(Font(R.font.pretendard_medium))
val cherrishFontRegular = FontFamily(Font(R.font.pretendard_regular))

@Immutable
data class CherrishTypography(

    val headlineB20: TextStyle,
    val headlineSb20: TextStyle,

    val title1Sb18: TextStyle,
    val title1M18: TextStyle,
    val title1R18: TextStyle,

    val title2Sb16: TextStyle,
    val title2M16: TextStyle,
    val title2R16: TextStyle,

    val body1Sb14: TextStyle,
    val body1M14: TextStyle,
    val body1R14: TextStyle,

    val body2R13: TextStyle,

    val body3M12: TextStyle,
    val body3R12: TextStyle,

    val captionR11: TextStyle,

    )

val defaultCherrishTypography = CherrishTypography(

    headlineB20 = TextStyle(
        fontFamily = cherrishFontBold,
        fontSize = 20.sp,
        letterSpacing = 0.01.em,
    ),
    headlineSb20 = TextStyle(
        fontFamily = cherrishFontSemiBold,
        fontSize = 20.sp,
        letterSpacing = 0.01.em,
    ),

    title1Sb18 = TextStyle(
        fontFamily = cherrishFontSemiBold,
        fontSize = 18.sp,
        letterSpacing = 0.01.em,
    ),
    title1M18 = TextStyle(
        fontFamily = cherrishFontMedium,
        fontSize = 18.sp,
        letterSpacing = 0.01.em,
    ),
    title1R18 = TextStyle(
        fontFamily = cherrishFontRegular,
        fontSize = 18.sp,
        letterSpacing = 0.01.em,
    ),

    title2Sb16 = TextStyle(
        fontFamily = cherrishFontSemiBold,
        fontSize = 16.sp,
    ),

    title2M16 = TextStyle(
        fontFamily = cherrishFontMedium,
        fontSize = 16.sp,
    ),

    title2R16 = TextStyle(
        fontFamily = cherrishFontRegular,
        fontSize = 16.sp,
    ),

    body1Sb14 = TextStyle(
        fontFamily = cherrishFontSemiBold,
        fontSize = 14.sp,
    ),

    body1M14 = TextStyle(
        fontFamily = cherrishFontMedium,
        fontSize = 14.sp,
    ),

    body1R14 = TextStyle(
        fontFamily = cherrishFontRegular,
        fontSize = 14.sp,
    ),

    body2R13 = TextStyle(
        fontFamily = cherrishFontRegular,
        fontSize = 13.sp,
    ),

    body3M12 = TextStyle(
        fontFamily = cherrishFontMedium,
        fontSize = 12.sp,
    ),

    body3R12 = TextStyle(
        fontFamily = cherrishFontRegular,
        fontSize = 12.sp,
    ),

    captionR11 = TextStyle(
        fontFamily = cherrishFontRegular,
        fontSize = 11.sp,
    ),

    )

val LocalCherrishTypographyProvider = staticCompositionLocalOf { defaultCherrishTypography }

@Preview
@Composable
private fun CherrishTypographyPreview() {
    CherrishTheme {
        Column {
            Text(
                text = "headlineB20",
                style = CherrishTheme.typography.headlineB20,
                color = CherrishTheme.colors.gray900
            )
            Text(
                text = "headlineSb20",
                style = CherrishTheme.typography.headlineSb20,
                color = CherrishTheme.colors.gray900
            )

            Text(
                text = "title1Sb18",
                style = CherrishTheme.typography.title1Sb18,
                color = CherrishTheme.colors.gray900
            )
            Text(
                text = "title1M18",
                style = CherrishTheme.typography.title1M18,
                color = CherrishTheme.colors.gray900
            )
            Text(
                text = "title1R18",
                style = CherrishTheme.typography.title1R18,
                color = CherrishTheme.colors.gray900
            )

            Text(
                text = "title2Sb16",
                style = CherrishTheme.typography.title2Sb16,
                color = CherrishTheme.colors.gray900
            )
            Text(
                text = "title2M16",
                style = CherrishTheme.typography.title2M16,
                color = CherrishTheme.colors.gray900
            )
            Text(
                text = "title2R16",
                style = CherrishTheme.typography.title2R16,
                color = CherrishTheme.colors.gray900
            )

            Text(
                text = "body1Sb14",
                style = CherrishTheme.typography.body1Sb14,
                color = CherrishTheme.colors.gray900
            )
            Text(
                text = "body1M14",
                style = CherrishTheme.typography.body1M14,
                color = CherrishTheme.colors.gray900
            )
            Text(
                text = "body1R14",
                style = CherrishTheme.typography.body1R14,
                color = CherrishTheme.colors.gray900
            )

            Text(
                text = "body2R13",
                style = CherrishTheme.typography.body2R13,
                color = CherrishTheme.colors.gray900
            )

            Text(
                text = "body3M12",
                style = CherrishTheme.typography.body3M12,
                color = CherrishTheme.colors.gray900
            )
            Text(
                text = "body3R12",
                style = CherrishTheme.typography.body3R12,
                color = CherrishTheme.colors.gray900
            )

            Text(
                text = "captionR11",
                style = CherrishTheme.typography.captionR11,
                color = CherrishTheme.colors.gray900
            )
        }
    }
}
