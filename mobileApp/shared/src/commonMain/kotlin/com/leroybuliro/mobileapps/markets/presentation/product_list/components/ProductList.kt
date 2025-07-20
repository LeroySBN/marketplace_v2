package com.leroybuliro.mobileapps.markets.presentation.product_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.leroybuliro.mobileapps.markets.domain.Product

@Composable
fun ProductList(
    modifier: Modifier = Modifier,
    onProductClick: () -> Unit,
    product: List<Product>,
    scrollState: LazyGridState = rememberLazyGridState(),
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 2),
        state = scrollState,
        modifier = modifier.padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ){
        items(
            items = product,
            key = { it.id }
        ) {
                product ->
            ProductListItem(
                modifier = Modifier
                    .widthIn(max = 700.dp),
                onClick = onProductClick,
                product = product,
            )
        }
    }
}