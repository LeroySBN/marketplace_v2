package com.leroybuliro.mobileapps.markets.presentation.sell.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
actual fun PlatformImagePicker(onImagesPicked: (List<String>) -> Unit, maxImages: Int) {
    // TODO: Implement real iOS image picker
    Text("[iOS Image Picker not implemented]")
}

@Composable
actual fun PlatformImagePreview(uri: String, modifier: Modifier) {
    // TODO: Implement real iOS image preview
    Text("img", modifier = modifier)
}
