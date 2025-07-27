package com.leroybuliro.mobileapps.markets.presentation.wallet

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class WalletViewModel : ViewModel() {
    var state by mutableStateOf(WalletState())
        private set

    fun onAction(action: WalletAction) {
        when (action) {
            is WalletAction.AddFunds -> {
                state = state.copy(
                    balance = state.balance + action.amount,
                    lastAdded = action.amount
                )
            }
        }
    }
}
