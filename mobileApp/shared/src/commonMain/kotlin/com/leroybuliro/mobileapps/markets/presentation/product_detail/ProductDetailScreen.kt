package com.leroybuliro.mobileapps.markets.presentation.product_detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.leroybuliro.mobileapps.markets.domain.Product
import com.leroybuliro.mobileapps.markets.presentation.product_detail.components.ProductDetailItem
import com.leroybuliro.mobileapps.markets.presentation.theme.DarkColorPalette
import com.leroybuliro.mobileapps.markets.presentation.theme.LightColorPalette
import markets.shared.generated.resources.Res
import markets.shared.generated.resources.add_cart
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    state: LazyListState,
    product: Product,
    isDarkTheme: Boolean,
//    onAction: (ProductDetailAction) -> Unit,
) {

    MaterialTheme(colorScheme = if (isDarkTheme) DarkColorPalette else LightColorPalette) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.background,
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    text = {
                        Text(
                            text = stringResource(Res.string.add_cart)
                        )
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.AddShoppingCart,
                            contentDescription = stringResource(Res.string.add_cart)
                        )
                    },
                    containerColor = Color.Red,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    expanded = true
                )
            },
            floatingActionButtonPosition = FabPosition.Center
        ) { innerPadding ->
            ProductDetailItem(
                scrollState = state,
                product = product
            )
        }
    }
}