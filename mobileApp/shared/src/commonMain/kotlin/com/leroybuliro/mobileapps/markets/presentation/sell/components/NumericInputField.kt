package com.leroybuliro.mobileapps.markets.presentation.sell.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NumericInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    modifier: Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = { newValue ->
            val filtered = newValue.filter { it.isDigit() }
            onValueChange(filtered.ifEmpty { "0" })
        },
        label = { Text(label) },
        singleLine = true,
        modifier = modifier,
        trailingIcon = {
            Row {
                IconButton(onClick = onDecrement) {
                    Icon(Icons.Default.Remove, contentDescription = "Decrease $label")
                }
                IconButton(onClick = onIncrement) {
                    Icon(Icons.Default.Add, contentDescription = "Increase $label")
                }
            }
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.background,
            unfocusedContainerColor = MaterialTheme.colorScheme.background,
            disabledContainerColor = MaterialTheme.colorScheme.background,
            focusedTextColor = MaterialTheme.colorScheme.primary,
            unfocusedTextColor = MaterialTheme.colorScheme.primary,
            disabledTextColor = MaterialTheme.colorScheme.inversePrimary,
        )
    )
}
