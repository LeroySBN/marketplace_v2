package com.leroybuliro.mobileapps.markets.presentation.product_list

interface ProductListAction {
    object OnProductClick: ProductListAction
    data class OnTabSelected(val index: Int): ProductListAction
}