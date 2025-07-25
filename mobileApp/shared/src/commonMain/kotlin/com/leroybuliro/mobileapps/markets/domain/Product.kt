package com.leroybuliro.mobileapps.markets.domain

import org.jetbrains.compose.resources.DrawableResource

data class Product (
    val id: Int,
    val name: String,
    val description: String?,
    val image: ArrayList<DrawableResource>,
    val price: Double,
    val offerPrice: Double,
    val stock: Int?,
    val category: String?,
)