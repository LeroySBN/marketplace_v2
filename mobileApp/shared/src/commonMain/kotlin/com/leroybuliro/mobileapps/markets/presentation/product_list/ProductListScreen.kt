package com.leroybuliro.mobileapps.markets.presentation.product_list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.leroybuliro.mobileapps.markets.presentation.theme.DarkColorPalette
import com.leroybuliro.mobileapps.markets.presentation.theme.LightColorPalette

@Composable
fun ProductListScreen(
    isDarkTheme: Boolean,
    onAction: (ProductListAction) -> Unit,
    toggleTheme: () -> Unit,
) {
    MaterialTheme( colorScheme = if (isDarkTheme) DarkColorPalette else LightColorPalette ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ){
            Scaffold(
                topBar = {},
                bottomBar = {},
                snackbarHost = {},
                floatingActionButton = {},
                floatingActionButtonPosition = FabPosition.End,
                contentWindowInsets = ScaffoldDefaults.contentWindowInsets,
                content = {},
            )
        }
    }
}