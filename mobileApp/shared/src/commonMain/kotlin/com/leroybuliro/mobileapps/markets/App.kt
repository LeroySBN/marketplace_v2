package com.leroybuliro.mobileapps.markets

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.leroybuliro.mobileapps.markets.presentation.product_list.ProductListScreen

enum class Screen {
    Welcome,
    ProdustList,
}

@Composable
fun App() {
    var isDarkMode by remember { mutableStateOf(false) }
    var screen by remember { mutableStateOf(Screen.ProdustList) }
    val toggleTheme = { isDarkMode = !isDarkMode }

    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            when (screen) {
                Screen.Welcome -> {
                    WelcomeScreen
                }
                Screen.ProdustList -> {
                    ProductListScreen(
                        toggleTheme,
                        isDarkTheme = isDarkMode,
                        onAction = {}
                    )
                }
            }

        }
    }
}