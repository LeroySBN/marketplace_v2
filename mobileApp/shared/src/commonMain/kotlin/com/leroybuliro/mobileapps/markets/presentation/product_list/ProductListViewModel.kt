package com.leroybuliro.mobileapps.markets.presentation.product_list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*

class ProductListViewModel: ViewModel() {
    private val _state = MutableStateFlow(ProductListState())
    val state: StateFlow<ProductListState> = _state.asStateFlow()

    fun onAction(action: ProductListAction) {
        when(action) {
            is ProductListAction.OnProductClick -> {
                _state.update { it.copy() }
            }
            is ProductListAction.OnTabSelected -> {
                _state.update { it.copy(selectedTabIndex = action.index) }
            }
        }
    }
}