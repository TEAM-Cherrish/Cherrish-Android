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

    val headline_b_20: TextStyle,
    val headline_sb_20: TextStyle,

    val title1_sb_18: TextStyle,
    val title1_m_18: TextStyle,
    val title1_r_18: TextStyle,

    val title2_sb_16: TextStyle,
    val title2_m_16: TextStyle,
    val title2_r_16: TextStyle,

    val body1_sb_14: TextStyle,
    val body1_m_14: TextStyle,
    val body1_r_14: TextStyle,

    val body2_r_13: TextStyle,

    val body3_m_12: TextStyle,
    val body3_r_12: TextStyle,

    val caption_r_11: TextStyle,

)

val defaultCherrishTypography = CherrishTypography(

    headline_b_20 = TextStyle(
        fontFamily = cherrishFontBold,
        fontSize = 20.sp,
        letterSpacing = 0.01.em,
    ),
    headline_sb_20 = TextStyle(
        fontFamily = cherrishFontSemiBold,
        fontSize = 20.sp,
        letterSpacing = 0.01.em,
    ),

    title1_sb_18 = TextStyle(
        fontFamily = cherrishFontSemiBold,
        fontSize = 18.sp,
        letterSpacing = 0.01.em,
    ),
    title1_m_18 = TextStyle(
        fontFamily = cherrishFontMedium,
        fontSize = 18.sp,
        letterSpacing = 0.01.em,
    ),
    title1_r_18 = TextStyle(
        fontFamily = cherrishFontRegular,
        fontSize = 18.sp,
        letterSpacing = 0.01.em,
    ),

    title2_sb_16 = TextStyle(
        fontFamily = cherrishFontSemiBold,
        fontSize = 16.sp,
    ),

    title2_m_16 = TextStyle(
        fontFamily = cherrishFontMedium,
        fontSize = 16.sp,
    ),

    title2_r_16 = TextStyle(
        fontFamily = cherrishFontRegular,
        fontSize = 16.sp,
    ),

    body1_sb_14 = TextStyle(
        fontFamily = cherrishFontSemiBold,
        fontSize = 14.sp,
    ),

    body1_m_14 = TextStyle(
        fontFamily = cherrishFontMedium,
        fontSize = 14.sp,
    ),

    body1_r_14 = TextStyle(
        fontFamily = cherrishFontRegular,
        fontSize = 14.sp,
    ),

    body2_r_13 = TextStyle(
        fontFamily = cherrishFontRegular,
        fontSize = 13.sp,
    ),

    body3_m_12 = TextStyle(
        fontFamily = cherrishFontMedium,
        fontSize = 12.sp,
    ),

    body3_r_12 = TextStyle(
        fontFamily = cherrishFontRegular,
        fontSize = 12.sp,
    ),

    caption_r_11 = TextStyle(
        fontFamily = cherrishFontRegular,
        fontSize = 11.sp,
    ),

    )

val LocalCherrishTypographyProvider = staticCompositionLocalOf { defaultCherrishTypography }

/**
 * Preview that renders a sample label for each style in CherrishTypography.
 *
 * Displays each typography variant (headline, title, body, caption) using the theme's
 * typography and Gray900 color so designers can inspect visual appearance in Android Studio.
 */
@Preview
@Composable
fun CherrishTypographyPreview() {
    CherrishTheme {
        Column {
            Text(
                "headline_b_20",
                style = CherrishTheme.typography.headline_b_20,
                color = CherrishTheme.colors.Gray900
            )
            Text(
                "headline_sb_20",
                style = CherrishTheme.typography.headline_sb_20,
                color = CherrishTheme.colors.Gray900
            )

            Text(
                "title1_sb_18",
                style = CherrishTheme.typography.title1_sb_18,
                color = CherrishTheme.colors.Gray900
            )
            Text(
                "title1_m_18",
                style = CherrishTheme.typography.title1_m_18,
                color = CherrishTheme.colors.Gray900
            )
            Text(
                "title1_r_18",
                style = CherrishTheme.typography.title1_r_18,
                color = CherrishTheme.colors.Gray900
            )

            Text(
                "title2_sb_16",
                style = CherrishTheme.typography.title2_sb_16,
                color = CherrishTheme.colors.Gray900
            )
            Text(
                "title2_m_16",
                style = CherrishTheme.typography.title2_m_16,
                color = CherrishTheme.colors.Gray900
            )
            Text(
                "title2_r_16",
                style = CherrishTheme.typography.title2_r_16,
                color = CherrishTheme.colors.Gray900
            )

            Text(
                "body1_sb_14",
                style = CherrishTheme.typography.body1_sb_14,
                color = CherrishTheme.colors.Gray900
            )
            Text(
                "body1_m_14",
                style = CherrishTheme.typography.body1_m_14,
                color = CherrishTheme.colors.Gray900
            )
            Text(
                "body1_r_14",
                style = CherrishTheme.typography.body1_r_14,
                color = CherrishTheme.colors.Gray900
            )

            Text(
                "body2_r_13",
                style = CherrishTheme.typography.body2_r_13,
                color = CherrishTheme.colors.Gray900
            )

            Text(
                "body3_m_12",
                style = CherrishTheme.typography.body3_m_12,
                color = CherrishTheme.colors.Gray900
            )
            Text(
                "body3_r_12",
                style = CherrishTheme.typography.body3_r_12,
                color = CherrishTheme.colors.Gray900
            )

            Text(
                "caption_r_11",
                style = CherrishTheme.typography.caption_r_11,
                color = CherrishTheme.colors.Gray900
            )
        }
    }
}