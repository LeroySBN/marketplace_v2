package com.leroybuliro.mobileapps.markets.presentation.sell

import com.leroybuliro.mobileapps.markets.domain.Product

sealed class SellAction {
    data class PostProduct(val product: Product) : SellAction()
}
