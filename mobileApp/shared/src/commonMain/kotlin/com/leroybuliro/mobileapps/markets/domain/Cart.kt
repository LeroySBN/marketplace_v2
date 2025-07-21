package com.leroybuliro.mobileapps.markets.domain

import org.jetbrains.compose.resources.DrawableResource

data class Cart(
    val id: Int,
    val name: String,
    val image: DrawableResource,
    val price: Double,
    val offerPrice: Double,
    val stock: Int?,
    var quantity: Int?,
)
