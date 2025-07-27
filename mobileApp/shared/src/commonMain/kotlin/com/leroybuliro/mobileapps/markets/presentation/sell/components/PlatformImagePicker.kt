package com.leroybuliro.mobileapps.markets.presentation.sell.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun PlatformImagePicker(
    onImagesPicked: (List<String>) -> Unit,
    maxImages: Int,
    imageUris: Boolean,
)

@Composable
expect fun PlatformImagePreview(
    uri: String,
    modifier: Modifier
)
