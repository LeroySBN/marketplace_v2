package com.leroybuliro.mobileapps.markets.presentation.cart_list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.leroybuliro.mobileapps.markets.domain.Cart
import com.leroybuliro.mobileapps.markets.presentation.cart_list.components.CartList
import com.leroybuliro.mobileapps.markets.presentation.theme.DarkColorPalette
import com.leroybuliro.mobileapps.markets.presentation.theme.LightColorPalette


@Composable
fun CartListScreen(
    cart: List<Cart>,
    isDarkTheme: Boolean,
    onAction: () -> Unit,
//    onAction: (CartListAction) -> Unit,
) {

//    fun getCost(quantity: Int?, price: Double): Double {
//        var cost = 0.0
//        if (quantity != null) {
//            cost = quantity * price
//        }
//        return cost
//    }
//
//    fun getTotalCost(cart: List<Cart>): String {
//        var totalCost = 0.0
//        for (cartItem in cart)
//            totalCost += if (cartItem.offerPrice > cartItem.price)
//                getCost(cartItem.quantity, cartItem.offerPrice)
//            else
//                getCost(cartItem.quantity, cartItem.price)
//        return "Ksh $totalCost"
//    }

    MaterialTheme(colorScheme = if (isDarkTheme) DarkColorPalette else LightColorPalette) {
        Surface(
            color = MaterialTheme.colorScheme.background,
        ) {
            CartList(
                onCartClick = onAction,
                cart = cart
            )
        }
    }
}