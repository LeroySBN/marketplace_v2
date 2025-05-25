package com.leroybuliro.mobileapps.markets.presentation.product_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.leroybuliro.mobileapps.markets.domain.Product

@Composable
fun ProductList(
    scrollState: LazyListState = rememberLazyListState(),
    modifier: Modifier = Modifier,
    onProductClick: () -> Unit,
    product: List<Product>,
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
        state = scrollState,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            items = product,
            key = { it.id }
        ) {
            product ->
            ProductListItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal =16.dp)
                    .widthIn(max = 700.dp),
                onClick = { onProductClick() },
                product = product,
            )
        }
    }
}