package com.leroybuliro.mobileapps.markets.presentation.product_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.leroybuliro.mobileapps.markets.domain.Product
import markets.shared.generated.resources.Res
import markets.shared.generated.resources.description
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ProductDetailItem(
    modifier: Modifier = Modifier,
    product: Product
) {
    var verticalScrollState = rememberScrollState()

    Surface (
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 100.dp),
    ) {
        Column(
            modifier = modifier.verticalScroll(verticalScrollState)
        ) {
            Box (
                modifier = modifier.padding(0.dp)
            ) {
                LazyRow (
                    modifier = modifier.fillMaxWidth(),
                ) {
                    items (
                        count = product.image.size
                    ) { item ->
                        Image(
                            painter = painterResource(resource = product.image[item]),
                            contentDescription = product.name,
                            modifier = modifier.fillParentMaxWidth()
                        )
                    }
                }
            }
            Column (
                modifier = modifier.padding(
                    start = 8.dp,
                    end = 8.dp,
                    top = 16.dp,
                    bottom = 16.dp
                ),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Text (
                    text = product.name,
                    maxLines = 2,
                    overflow = TextOverflow.Clip,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                )
                Row (
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Text(
                        text = "Ksh " + product.price.toString(),
                        fontWeight = FontWeight.Bold,
                        color = Color.Red,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
                Column {
                    Text (
                        text = stringResource(Res.string.description),
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary,
                    )
                    Text (
                        text = product.description.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        overflow = TextOverflow.Visible,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            }
        }
    }
}