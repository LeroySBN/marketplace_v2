package com.leroybuliro.mobileapps.markets.presentation.cart_list

import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.ShoppingCartCheckout
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.leroybuliro.mobileapps.markets.domain.Cart
import com.leroybuliro.mobileapps.markets.presentation.cart_list.components.CartList
import com.leroybuliro.mobileapps.markets.presentation.theme.DarkColorPalette
import com.leroybuliro.mobileapps.markets.presentation.theme.LightColorPalette
import markets.shared.generated.resources.Res
import markets.shared.generated.resources.cart_checkout
import org.jetbrains.compose.resources.stringResource


@Composable
fun CartListScreen(
    state: LazyListState,
    cart: List<Cart>,
    isDarkTheme: Boolean,
    onAction: () -> Unit,
//    onAction: (CartListAction) -> Unit,
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
                            text = stringResource(Res.string.cart_checkout)
                        )
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.ShoppingCartCheckout,
                            contentDescription = stringResource(Res.string.cart_checkout)
                        )
                    },
                    containerColor = Color.Red,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    expanded = true
                )
            },
            floatingActionButtonPosition = FabPosition.Center
        ) { innerPadding ->
            CartList(
                scrollState = state,
                onCartClick = onAction,
                cart = cart
            )
        }
    }
}