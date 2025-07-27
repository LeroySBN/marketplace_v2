package com.leroybuliro.mobileapps.markets.presentation.order

sealed class OrderAction {
    data class LoadOrders(val userId: String) : OrderAction()
}
