package com.leroybuliro.mobileapps.markets.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val LightColorPalette = lightColorScheme(
    primary = Color(0xFF191210),
    inversePrimary = Color(0xFF888888),
    onPrimary = Color.White,
    secondary = Color(0xFFFF9800),
    onSecondary = Color.Black,
    tertiary = Color.Red,
    onTertiary = Color(0xFFB00020),

    surface = Color(0xFFF8FEFF), // Egg Shell
    onSurface = Color(0xFF888888),
    surfaceVariant = Color(0xFFF6F6F6),
    onSurfaceVariant = Color.Black,

    surfaceContainer = Color(0xFFF9FDFE), // Winter
    surfaceContainerHigh = Color(0xFFFFFFF5), // Creamy
    surfaceContainerHighest = Color(0xFFF8FEFF), // Egg Shell

    primaryContainer = Color(0xFF009688),
    onPrimaryContainer = Color.White,
    secondaryContainer = Color(0xFFf1ebe9),
    onSecondaryContainer = Color(0xFF191210),
    tertiaryContainer = Color.White,
    onTertiaryContainer = Color.Black,

    background = Color(0xFFfbf9f9),
    onBackground = Color(0xFF8c665a),

    error = Color(0xFFB00020),
    onError = Color.White
)

val DarkColorPalette = darkColorScheme(
    primary = Color.White,
    inversePrimary = Color(0xFF8eccc6),
    onPrimary = Color.Black,
    secondary = Color(0xFFFFCC80),
    onSecondary = Color.Black,
    tertiary = Color.Red,
    onTertiary = Color(0xFFB00020),

    surfaceContainer = Color(0xFF0B0B0B),
    surfaceContainerHigh = Color(0xFF050505),
    surfaceContainerHighest = Color.Black,

    surface = Color.Black,
    onSurface = Color(0xFF888888),
    surfaceVariant = Color.Black,
    onSurfaceVariant = Color.White,

    primaryContainer = Color(0xFF05d5c0),
    onPrimaryContainer = Color(0xFF102321),
    secondaryContainer = Color(0xFF214a46),
    onSecondaryContainer = Color.White,
    tertiaryContainer = Color(0xFF0B0B0B),
    onTertiaryContainer = Color.Black,

    background = Color(0xFF1A1A1A),
    onBackground = Color(0xFF8ECCC6),

    error = Color(0xFFCF6679),
    onError = Color.White
)

@Composable
fun AppTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorPalette = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    MaterialTheme(
        colorScheme = colorPalette,
        content = content
    )
}