package com.leroybuliro.mobileapps.markets.presentation.sell

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SellViewModel : ViewModel() {
    var state by mutableStateOf(SellState())
        private set

    fun onAction(action: SellAction) {
    when (action) {
        is SellAction.PostProduct -> {
            state = state.copy(
                lastPosted = action.product,
                postedProducts = state.postedProducts + action.product,
                isPosting = true
            )
            // Simulate post logic here
            state = state.copy(isPosting = false)
        }
    }
}
}
