package com.leroybuliro.mobileapps.markets.presentation.order

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class OrderViewModel : ViewModel() {
    var state by mutableStateOf(OrderState())
        private set

    fun onAction(action: OrderAction) {
        when (action) {
            is OrderAction.LoadOrders -> {
                // Simulate loading orders
                state = state.copy(
                    orders = listOf(
                        Order("1", "Pending"),
                        Order("2", "Shipped"),
                        Order("3", "Delivered")
                    )
                )
            }
        }
    }
}
