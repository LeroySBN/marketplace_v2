package com.leroybuliro.mobileapps.markets.presentation.wallet

sealed class WalletAction {
    data class AddFunds(val amount: Double) : WalletAction()
}
