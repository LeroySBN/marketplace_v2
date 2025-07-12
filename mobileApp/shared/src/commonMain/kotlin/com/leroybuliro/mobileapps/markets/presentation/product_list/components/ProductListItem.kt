package com.leroybuliro.mobileapps.markets.presentation.product_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.leroybuliro.mobileapps.markets.domain.Product
import org.jetbrains.compose.resources.painterResource

@Composable
fun ProductListItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    product: Product
) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = modifier.clickable { onClick() }
    ) {
        Column(
            modifier = Modifier
                .padding(
                    top = 16.dp,
                    bottom = 24.dp,
                    start = 0.dp,
                    end = 0.dp
                ),
        ) {
            Box(
                modifier = Modifier.padding(0.dp)
            ) {
                Image(
                    painter = painterResource(resource = product.image),
                    contentDescription = product.name,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
            }
            Column(
                modifier = Modifier
                    .padding(
                        top = 8.dp,
                        start = 8.dp,
                        bottom = 0.dp
                    ),
            ) {
                Text(
                    text = product.name,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.width(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Text(
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        text = product.price.toString(),
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    IconButton(
                        onClick = onClick,
                        colors = IconButtonDefaults.filledIconButtonColors(Color.Red),
                        modifier = Modifier
                            .width(40.dp)
                            .height(22.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.AddShoppingCart,
                            contentDescription = "Add to cart",
                            tint = Color.White,
                            modifier = Modifier
                                .padding(top = 2.dp, bottom = 2.dp)
                        )
                    }
                }
            }
        }
    }
}