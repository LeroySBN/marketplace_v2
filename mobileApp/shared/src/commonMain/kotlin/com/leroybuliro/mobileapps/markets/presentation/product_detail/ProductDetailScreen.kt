package com.leroybuliro.mobileapps.markets.presentation.product_detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.leroybuliro.mobileapps.markets.domain.Product
import com.leroybuliro.mobileapps.markets.presentation.product_detail.components.ProductDetailItem
import com.leroybuliro.mobileapps.markets.presentation.theme.DarkColorPalette
import com.leroybuliro.mobileapps.markets.presentation.theme.LightColorPalette

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    product: Product,
    isDarkTheme: Boolean,
//    onAction: (ProductDetailAction) -> Unit,
) {

    MaterialTheme(colorScheme = if (isDarkTheme) DarkColorPalette else LightColorPalette) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            ProductDetailItem(
                product = product
            )
        }
    }
}