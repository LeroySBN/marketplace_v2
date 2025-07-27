package com.leroybuliro.mobileapps.markets.presentation.sell

data class CategorySubcategory(
    val name: String,
    val subcategories: List<String>
)

val categorySubcategoryList = listOf(
    CategorySubcategory("Women", listOf("Sneakers", "Clothing", "Other")),
    CategorySubcategory("Men", listOf("Sneakers", "Clothing", "Other")),
    CategorySubcategory("Designer", listOf("Sneakers", "Clothing", "Other")),
    CategorySubcategory("Kids", listOf("Sneakers", "Clothing", "Other")),
    CategorySubcategory("Home", listOf("Living Room", "Kitchen", "Bedroom", "Other")),
    CategorySubcategory("Electronics", listOf("Phones", "Computers", "Other")),
    CategorySubcategory("Entertainment", listOf("Games", "Movies", "Other")),
    CategorySubcategory("Hobbies & Collectables", listOf("Sports Cards", "Memorabilia", "Other")),
    CategorySubcategory("Sports", listOf("Equipment", "Apparel", "Other")),
    CategorySubcategory("Other", listOf("Other"))
)
