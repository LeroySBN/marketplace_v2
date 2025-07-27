package com.leroybuliro.mobileapps.markets.presentation.sell.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun ImagePickerDialog(
    open: Boolean,
    onDismiss: () -> Unit,
    onImagesPicked: (List<String>) -> Unit,
    maxImages: Int = 7
) {
    var inputUris by remember { mutableStateOf("") }
    if (open) {
        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                Button(onClick = {
                    val uris = inputUris.split(',').map { it.trim() }.filter { it.isNotEmpty() }.take(maxImages)
                    onImagesPicked(uris)
                    onDismiss()
                }) {
                    Text("Add Images")
                }
            },
            dismissButton = {
                Button(onClick = onDismiss) { Text("Cancel") }
            },
            title = { Text("Select Images (comma separated URIs)") },
            text = {
                OutlinedTextField(
                    value = inputUris,
                    onValueChange = { inputUris = it },
                    label = { Text("Image URIs") },
                    modifier = Modifier
                )
            }
        )
    }
}
