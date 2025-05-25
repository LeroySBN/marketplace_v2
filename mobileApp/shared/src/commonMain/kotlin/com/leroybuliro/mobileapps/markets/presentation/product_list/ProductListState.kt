package com.leroybuliro.mobileapps.markets.presentation.product_list

import com.leroybuliro.mobileapps.markets.domain.Product

data class ProductListState(
    val products: List<Product> = emptyList(),
    val wishlist: List<Product> = emptyList(),
    val selectedTabIndex: Int = 0,
    val isLoading: Boolean = false,
    val error: String? = null
)
