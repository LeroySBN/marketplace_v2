package com.leroybuliro.mobileapps.markets.presentation.wallet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.leroybuliro.mobileapps.markets.presentation.theme.DarkColorPalette
import com.leroybuliro.mobileapps.markets.presentation.theme.LightColorPalette

@Composable
fun WalletScreen(
    balance: Double,
    onAddFunds: (Double) -> Unit,
    isDarkTheme: Boolean
) {
    val (amount, setAmount) = remember { mutableStateOf("") }
    val verticalScrollState = rememberScrollState()
    MaterialTheme(colorScheme = if (isDarkTheme) DarkColorPalette else LightColorPalette) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(verticalScrollState)
                .background(
                    color = MaterialTheme.colorScheme.surface,
                )
                .padding(start = 8.dp, top = 0.dp, end = 8.dp, bottom = 100.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Total Balance: Ksh ${String.format("%,.2f", balance)}",
                style = androidx.compose.material.MaterialTheme.typography.h6
            )
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
}
