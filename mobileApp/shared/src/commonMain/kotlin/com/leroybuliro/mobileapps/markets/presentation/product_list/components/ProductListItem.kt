package com.leroybuliro.mobileapps.markets.presentation.product_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.leroybuliro.mobileapps.markets.domain.Product
import markets.shared.generated.resources.Res
import markets.shared.generated.resources.add_cart
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ProductListItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    product: Product
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
    ) {
        Column {
            Box (
                modifier = modifier
                    .fillMaxWidth(),
            ) {
                Image(
                    painter = painterResource(resource = product.image[0]),
                    contentDescription = product.name,
                    contentScale = ContentScale.FillWidth,
                    modifier = modifier.fillMaxWidth()
                )
            }
            Column(
                modifier = modifier
                    .padding(
                        top = 8.dp,
                        start = 8.dp,
                        bottom = 0.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = product.name,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.inversePrimary,
                    maxLines = 1,
                )
                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Text(
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        text = "Ksh " + product.price.toString(),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary,
                    )
                    IconButton(
                        onClick = onClick,
                        colors = IconButtonDefaults.filledIconButtonColors(Color.Red),
                        modifier = modifier
                            .width(40.dp)
                            .height(22.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.AddShoppingCart,
                            contentDescription = stringResource(Res.string.add_cart),
                            tint = MaterialTheme.colorScheme.surfaceVariant,
                            modifier = modifier
                                .padding(top = 2.dp, bottom = 2.dp)
                        )
                    }
                }
            }
        }
    }
}