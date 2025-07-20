package com.leroybuliro.mobileapps.markets.presentation.cart_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.leroybuliro.mobileapps.markets.domain.Cart

@Composable
fun CartList(
    scrollState: LazyListState = rememberLazyListState(),
    modifier: Modifier = Modifier,
    onCartClick: () -> Unit,
    cart: List<Cart>,
) {
    LazyColumn(
        state = scrollState,
        modifier = modifier.fillMaxWidth().padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ){
        items(
            items = cart,
            key = { it.id }
        ) {
                cart ->
            CartListItem(
                modifier = modifier,
                onClick = { onCartClick() },
                cart = cart,
            )
        }
    }
}