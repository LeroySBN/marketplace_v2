package com.leroybuliro.mobileapps.markets.domain

import org.jetbrains.compose.resources.DrawableResource

data class Product (
    val id: Int?,
    val image: ArrayList<DrawableResource>,
    val name: String,
    val description: String?,
    val category: String?,
    val subCategory: String?,
    val material: String?,
    val price: Double,
    val offerPrice: Double?,
    val stock: Int?,
)