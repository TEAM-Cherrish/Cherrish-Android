package com.cherrish.android.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
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
