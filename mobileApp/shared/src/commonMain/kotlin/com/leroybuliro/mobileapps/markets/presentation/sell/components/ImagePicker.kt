package com.leroybuliro.mobileapps.markets.presentation.sell.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Cross-platform image picker stub for Compose Multiplatform.
 * On Android/iOS, this should be implemented using platform-specific APIs.
 * For now, simulates image URI input for demo/testing.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImagePicker(
    selectedImages: List<String>,
    onImagesSelected: (List<String>) -> Unit,
    maxImages: Int = 7
) {
    var showInput by remember { mutableStateOf(false) }
    var inputUris by remember { mutableStateOf("") }

    Column {
        Button(
            onClick = { showInput = true },
            enabled = selectedImages.size < maxImages,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Select Images (${selectedImages.size}/$maxImages)")
        }
        Spacer(modifier = Modifier.height(8.dp))
        if (selectedImages.isNotEmpty()) {
            Text("Selected:")
            for (uri in selectedImages) {
                Text(uri, maxLines = 1)
            }
        }
    }

    if (showInput) {
        AlertDialog(
            onDismissRequest = { showInput = false },
            confirmButton = {
                Button(onClick = {
                    val uris = inputUris.split(",").map { it.trim() }.filter { it.isNotEmpty() }
                    if (uris.size + selectedImages.size <= maxImages) {
                        onImagesSelected(selectedImages + uris)
                        showInput = false
                        inputUris = ""
                    }
                }) { Text("OK") }
            },
            dismissButton = {
                Button(onClick = { showInput = false }) { Text("Cancel") }
            },
            title = { Text("Enter Image URIs (comma separated)") },
            text = {
                OutlinedTextField(
                    value = inputUris,
                    onValueChange = { inputUris = it },
                    label = { Text("URIs") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        )
    }
}
