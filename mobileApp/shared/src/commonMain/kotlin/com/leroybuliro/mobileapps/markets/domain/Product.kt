package com.leroybuliro.mobileapps.markets.domain

data class Product (
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val quantity: Int
)