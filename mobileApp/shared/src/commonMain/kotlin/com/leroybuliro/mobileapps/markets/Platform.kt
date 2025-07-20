package com.leroybuliro.mobileapps.markets

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
