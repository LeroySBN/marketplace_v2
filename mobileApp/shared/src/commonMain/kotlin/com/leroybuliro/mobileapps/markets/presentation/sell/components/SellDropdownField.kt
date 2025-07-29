package com.leroybuliro.mobileapps.markets.presentation.sell.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun SellDropdownField(
    label: String,
    options: List<String>,
    selected: String?,
    onSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .clickable { expanded = true },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Text(
                    text = label,
                    color = if (enabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.inversePrimary,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(24.dp))
                Text(
                    text = selected ?: "",
                    color = MaterialTheme.colorScheme.inversePrimary,
                    fontWeight = FontWeight.Normal,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = if (enabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.inversePrimary
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
            shape = RoundedCornerShape(16.dp)
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = option,
                            color = MaterialTheme.colorScheme.primary
                        ) },
                    onClick = {
                        onSelected(option)
                        expanded = false
                    },
                )
            }
        }
    }
}
