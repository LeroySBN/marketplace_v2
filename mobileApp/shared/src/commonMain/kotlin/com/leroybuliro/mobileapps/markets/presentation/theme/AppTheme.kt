package com.leroybuliro.mobileapps.markets.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val LightColorPalette = lightColorScheme(
    primary = Color(0xFF2196F3),      // Blue
    onPrimary = Color.White,
    inversePrimary = Color(0xFF1976D2),
    secondary = Color(0xFFFF9800),     // Orange
    onSecondary = Color.Black,
    background = Color.White,
    onBackground = Color.Black,
    surface = Color(0xFFF5F5F5),       // Very light gray
    onSurface = Color.Black,
    error = Color(0xFFB00020),
    onError = Color.White
)

val DarkColorPalette = darkColorScheme(
    primary = Color(0xFF64B5F6),      // Light blue
    onPrimary = Color.Black,           // Black text on light blue
    inversePrimary = Color(0xFF42A5F5),
    secondary = Color(0xFFFFCC80),     // Light orange
    onSecondary = Color.Black,         // Black text on light orange
    background = Color(0xFF212121),
    onBackground = Color.White,        // Black text on medium gray
    surface = Color(0xFFDDDDDD),       // Light gray surface
    onSurface = Color.Black,           // Black text on light gray
    error = Color(0xFFCF6679),
    onError = Color.White              // White text on error color
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