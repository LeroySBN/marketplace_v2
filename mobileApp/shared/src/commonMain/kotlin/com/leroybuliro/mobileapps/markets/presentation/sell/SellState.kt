package com.leroybuliro.mobileapps.markets.presentation.sell

import com.leroybuliro.mobileapps.markets.domain.Product

data class SellState(
    val lastPosted: Product? = null,
    val postedProducts: List<Product> = emptyList(),
    val isPosting: Boolean = false
)
