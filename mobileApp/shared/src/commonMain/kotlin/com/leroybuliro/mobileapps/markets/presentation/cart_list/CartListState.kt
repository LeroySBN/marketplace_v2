package com.leroybuliro.mobileapps.markets.presentation.cart_list

import com.leroybuliro.mobileapps.markets.domain.Cart

data class CartListState (
    val carts: List<Cart> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)