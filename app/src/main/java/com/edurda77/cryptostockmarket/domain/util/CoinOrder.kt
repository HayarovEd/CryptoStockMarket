package com.edurda77.cryptostockmarket.domain.util

sealed class CoinOrder(val orderType: OrderType) {
    class Name(orderType: OrderType) : CoinOrder(orderType)
    class Symbol(orderType: OrderType) : CoinOrder(orderType)

    fun copy(orderType: OrderType): CoinOrder {
        return when (this) {
            is Name -> Name(orderType)
            is Symbol -> Symbol(orderType)
        }
    }
}
