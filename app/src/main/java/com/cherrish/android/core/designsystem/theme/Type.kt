package com.cherrish.android.core.designsystem.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.cherrish.android.R

val cherrishFontBold = FontFamily(Font(R.font.pretendard_bold))
val cherrishFontSemiBold = FontFamily(Font(R.font.pretendard_semibold))
val cherrishFontMedium = FontFamily(Font(R.font.pretendard_medium))
val cherrishFontRegular = FontFamily(Font(R.font.pretendard_regular))

private fun CherrishTextStyle(
    fontFamily: FontFamily,
    fontSize: TextUnit,
    letterSpacing: TextUnit = 0.em,
    lineHeight: TextUnit
): TextStyle = TextStyle(
    fontFamily = fontFamily,
    fontSize = fontSize,
    letterSpacing = letterSpacing,
    lineHeight = lineHeight,
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )
)

@Immutable
data class CherrishTypography(

    val headlineB20: TextStyle,
    val headlineSB20: TextStyle,

    val title1SB18: TextStyle,
    val title1M18: TextStyle,
    val title1R18: TextStyle,

    val title2SB16: TextStyle,
    val title2M16: TextStyle,
    val title2R16: TextStyle,

    val body1SB14: TextStyle,
    val body1M14: TextStyle,
    val body1R14: TextStyle,

    val body2R13: TextStyle,

    val body3M12: TextStyle,
    val body3R12: TextStyle,

    val captionR11: TextStyle

)

val defaultCherrishTypography = CherrishTypography(

    headlineB20 = CherrishTextStyle(
        fontFamily = cherrishFontBold,
        fontSize = 20.sp,
        letterSpacing = 0.01.em,
        lineHeight = 1.5.em
    ),

    headlineSB20 = CherrishTextStyle(
        fontFamily = cherrishFontSemiBold,
        fontSize = 20.sp,
        letterSpacing = 0.01.em,
        lineHeight = 1.5.em

    ),

    title1SB18 = CherrishTextStyle(
        fontFamily = cherrishFontSemiBold,
        fontSize = 18.sp,
        letterSpacing = 0.01.em,
        lineHeight = 1.5.em
    ),
    title1M18 = CherrishTextStyle(
        fontFamily = cherrishFontMedium,
        fontSize = 18.sp,
        letterSpacing = 0.01.em,
        lineHeight = 1.5.em
    ),
    title1R18 = CherrishTextStyle(
        fontFamily = cherrishFontRegular,
        fontSize = 18.sp,
        letterSpacing = 0.01.em,
        lineHeight = 1.5.em
    ),

    title2SB16 = CherrishTextStyle(
        fontFamily = cherrishFontSemiBold,
        fontSize = 16.sp,
        lineHeight = 1.5.em
    ),

    title2M16 = CherrishTextStyle(
        fontFamily = cherrishFontMedium,
        fontSize = 16.sp,
        lineHeight = 1.5.em
    ),

    title2R16 = CherrishTextStyle(
        fontFamily = cherrishFontRegular,
        fontSize = 16.sp,
        lineHeight = 1.5.em
    ),

    body1SB14 = CherrishTextStyle(
        fontFamily = cherrishFontSemiBold,
        fontSize = 14.sp,
        lineHeight = 1.4.em
    ),

    body1M14 = CherrishTextStyle(
        fontFamily = cherrishFontMedium,
        fontSize = 14.sp,
        lineHeight = 1.4.em
    ),

    body1R14 = CherrishTextStyle(
        fontFamily = cherrishFontRegular,
        fontSize = 14.sp,
        lineHeight = 1.4.em
    ),

    body2R13 = CherrishTextStyle(
        fontFamily = cherrishFontRegular,
        fontSize = 13.sp,
        lineHeight = 1.4.em
    ),

    body3M12 = CherrishTextStyle(
        fontFamily = cherrishFontMedium,
        fontSize = 12.sp,
        lineHeight = 1.4.em
    ),

    body3R12 = CherrishTextStyle(
        fontFamily = cherrishFontRegular,
        fontSize = 12.sp,
        lineHeight = 1.4.em
    ),

    captionR11 = CherrishTextStyle(
        fontFamily = cherrishFontRegular,
        fontSize = 11.sp,
        lineHeight = 1.4.em
    )

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
                text = "headlineSB20",
                style = CherrishTheme.typography.headlineSB20,
                color = CherrishTheme.colors.gray900
            )

            Text(
                text = "title1SB18",
                style = CherrishTheme.typography.title1SB18,
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
                text = "title2SB16",
                style = CherrishTheme.typography.title2SB16,
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
                text = "body1SB14",
                style = CherrishTheme.typography.body1SB14,
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
