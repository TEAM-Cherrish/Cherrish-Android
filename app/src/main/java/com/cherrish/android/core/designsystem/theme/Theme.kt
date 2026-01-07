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

/**
 * Supplies a CherrishColors and CherrishTypography instance to the composition for descendant composables.
 *
 * This wraps [content] with a CompositionLocalProvider that sets the values for
 * LocalCherrishColorsProvider and LocalCherrishTypographyProvider.
 *
 * @param colors The CherrishColors instance to provide to descendants.
 * @param typography The CherrishTypography instance to provide to descendants.
 * @param content Composable content that will read the provided colors and typography.
 */
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

/**
 * Applies the Cherrish design system to the given UI content.
 *
 * Provides CherrishColors and CherrishTypography to the composition, applies a MaterialTheme
 * using the Cherrish color scheme, and sets the system status bar to a light appearance when
 * running (not in preview/edit mode).
 *
 * @param content The composable UI tree to be wrapped by the Cherrish theme.
 */
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

/**
 * Produces a Material 3 light ColorScheme by mapping Cherrish color tokens to Material color roles.
 *
 * @receiver The source CherrishColors used to derive the scheme (light-oriented).
 * @return A light ColorScheme where:
 * - `primary` = CherrishPink
 * - `onPrimary` = Gray0
 * - `secondary` = Red700
 * - `onSecondary` = Gray0
 * - `background` = Gray0
 * - `onBackground` = Gray900
 * - `surface` = Gray900
 * - `onSurface` = Gray0
 * - `error` = Red700
 * - `onError` = Gray0
 */
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