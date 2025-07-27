package com.leroybuliro.mobileapps.markets.presentation.order

import androidx.compose.runtime.Composable
import androidx.compose.material.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Dummy data class for Order
 data class Order(val id: String, val status: String)

@Composable
fun OrderScreen(orders: List<Order>) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text("Orders")
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(orders) { order ->
                Column(modifier = Modifier.padding(8.dp)) {
                    Text("Order #${order.id}")
                    Text("Status: ${order.status}")
                }
            }
        }
    }
}
