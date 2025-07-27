package com.leroybuliro.mobileapps.markets.presentation.wallet

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WalletScreen(balance: Double, onAddFunds: (Double) -> Unit) {
    val (amount, setAmount) = remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Total Balance: Ksh ${String. format("%,.2f", balance)}",
            style = androidx.compose.material.MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(
            value = amount,
            onValueChange = setAmount,
            label = { Text("Amount to Add") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                val amt = amount.toDoubleOrNull()
                if (amt != null && amt > 0) {
                    onAddFunds(amt)
                    setAmount("")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Funds")
        }
    }
}
