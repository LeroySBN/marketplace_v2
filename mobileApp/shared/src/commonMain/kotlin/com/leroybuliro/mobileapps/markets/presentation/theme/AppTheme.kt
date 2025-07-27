package com.leroybuliro.mobileapps.markets.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val LightColorPalette = lightColorScheme(
    primary = Color(0xFF191210),
    onPrimary = Color.White,
    primaryContainer = Color(0xFF009688),
    onPrimaryContainer = Color.White,
//    inversePrimary = Color(0xFF8c665a),
    inversePrimary = Color(0xFF888888),
    secondary = Color(0xFFFF9800),
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFFf1ebe9),
    onSecondaryContainer = Color(0xFF191210),
    onTertiary = Color(0xFFB00020),
    background = Color.White,
    onBackground = Color.Black,
//    surface = Color(0xFFF5F5F5),
    surface = Color(0xFFF6F6F6),
    onSurface = Color.Black,
    surfaceVariant = Color(0xFFfbf9f9),
    onSurfaceVariant = Color(0xFF8c665a),
    error = Color(0xFFB00020),
    onError = Color.White
)

val DarkColorPalette = darkColorScheme(
    primary = Color.White,
    onPrimary = Color.Black,
    primaryContainer = Color(0xFF05d5c0),
    onPrimaryContainer = Color(0xFF102321),
    inversePrimary = Color(0xFF8eccc6),
    secondary = Color(0xFFFFCC80),
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFF81C784),
    onSecondaryContainer = Color.White,
    background = Color(0xFF102321),
    onBackground = Color.White,
//    surface = Color(0xFFDDDDDD),
    surface = Color(0xFF102321),
    onSurface = Color.Black,
    surfaceVariant = Color(0xFF183532),
    onSurfaceVariant = Color(0xFF8ECCC6),
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