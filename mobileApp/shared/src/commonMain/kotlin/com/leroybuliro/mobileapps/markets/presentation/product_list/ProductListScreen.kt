package com.leroybuliro.mobileapps.markets.presentation.product_list

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.leroybuliro.mobileapps.markets.domain.Product
import com.leroybuliro.mobileapps.markets.presentation.product_list.components.ProductList
import com.leroybuliro.mobileapps.markets.presentation.theme.DarkColorPalette
import com.leroybuliro.mobileapps.markets.presentation.theme.LightColorPalette

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(
    isDarkTheme: Boolean,
    onAction: () -> Unit,
    products: List<Product>,
//    onAction: (ProductListAction) -> Unit,
) {
    MaterialTheme(
        colorScheme = if (isDarkTheme) DarkColorPalette else LightColorPalette
    ) {
        Surface (
            color = MaterialTheme.colorScheme.surface,
        ) {
            ProductList(
                onProductClick = onAction,
//                onAction(ProductListAction.OnProductClick),
                product = products
            )
        }
    }
}