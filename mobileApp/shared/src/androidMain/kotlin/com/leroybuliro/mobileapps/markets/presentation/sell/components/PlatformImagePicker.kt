package com.leroybuliro.mobileapps.markets.presentation.sell.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import markets.shared.generated.resources.Res
import markets.shared.generated.resources.sell_add_photo
import org.jetbrains.compose.resources.stringResource

@Composable
actual fun PlatformImagePicker(
    onImagesPicked: (List<String>) -> Unit,
    maxImages: Int,
    imageUris: Boolean,
) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents()
    ) { uris: List<Uri> ->
        onImagesPicked(uris.map { it.toString() }.take(maxImages))
    }
    IconButton(
        onClick = { launcher.launch("image/*") },
        enabled = imageUris,
        modifier = Modifier
            .height(48.dp)
    ) {
        Icon(
            imageVector = Icons.Default.AddAPhoto,
            contentDescription = stringResource(Res.string.sell_add_photo),
            tint = MaterialTheme.colorScheme.onTertiary
        )
    }
}

@Composable
actual fun PlatformImagePreview(uri: String, modifier: Modifier) {
    AsyncImage(
        model = uri,
        contentDescription = null,
        modifier = modifier.fillMaxSize(),
        contentScale = ContentScale.Fit
    )
}
