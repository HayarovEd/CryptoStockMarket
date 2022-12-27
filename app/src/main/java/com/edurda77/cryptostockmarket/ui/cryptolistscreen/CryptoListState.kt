package com.edurda77.cryptostockmarket.ui.cryptolistscreen

import com.edurda77.cryptostockmarket.domain.entities.Coin
import com.edurda77.cryptostockmarket.domain.util.CoinOrder
import com.edurda77.cryptostockmarket.domain.util.OrderType

data class CryptoListState(
    val cryptoCoins: List<Coin> = emptyList(),
    val error: String? = null,
    val searchQuery: String = "",
    val coinOrder: CoinOrder = CoinOrder.Symbol(OrderType.Ascending),
    val isloading: Boolean = false
)
