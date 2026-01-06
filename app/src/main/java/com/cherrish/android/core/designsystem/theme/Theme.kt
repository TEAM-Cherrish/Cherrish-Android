package com.cherrish.android.core.designsystem.theme

import android.app.Activity
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

object CherrishTheme {

    val colors: CherrishColors
        @Composable
        @ReadOnlyComposable
        get() = LocalCherrishColorsProvider.current

    val typography: CherrishTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalCherrishTypographyProvider.current
}

@Composable
fun ProvideCherrishColorsAndTypography(
    colors: CherrishColors,
    typography: CherrishTypography,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalCherrishColorsProvider provides colors,
        LocalCherrishTypographyProvider provides typography,
        content = content
    )
}

@Composable
fun CherrishTheme(
    content: @Composable () -> Unit
) {
    ProvideCherrishColorsAndTypography(
        colors = defaultCherrishColors,
        typography = defaultCherrishTypography
    ) {
        val view = LocalView.current
        if (!view.isInEditMode) {
            SideEffect {
                (view.context as Activity).window.run {
                    WindowCompat
                        .getInsetsController(this, view)
                        .isAppearanceLightStatusBars = true
                }
            }
        }

        MaterialTheme(
            colorScheme = defaultCherrishColors.toMaterialColorScheme(),
            content = content
        )
    }
}

@Composable
private fun CherrishColors.toMaterialColorScheme(): ColorScheme {
    return lightColorScheme(
        primary = CherrishTheme.colors.CherrishPink,
        onPrimary = CherrishTheme.colors.Gray0,

        secondary = CherrishTheme.colors.Red700,
        onSecondary = CherrishTheme.colors.Gray0,

        background = CherrishTheme.colors.Gray0,
        onBackground = CherrishTheme.colors.Gray900,

        surface = CherrishTheme.colors.Gray900,
        onSurface = CherrishTheme.colors.Gray0,

        error = CherrishTheme.colors.Red700,
        onError = CherrishTheme.colors.Gray0,
    )
}
