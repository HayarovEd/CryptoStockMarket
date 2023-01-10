package com.edurda77.cryptostockmarket.ui.cryptolistscreen

import com.edurda77.cryptostockmarket.domain.util.CoinOrder

sealed class CryptoCoinsEvent {
    data class Order(val coinOrder: CoinOrder): CryptoCoinsEvent()
    data class OnSearch(val query:String) : CryptoCoinsEvent()
    object Refresh: CryptoCoinsEvent()
    object ToggleOrderSection: CryptoCoinsEvent()
}
