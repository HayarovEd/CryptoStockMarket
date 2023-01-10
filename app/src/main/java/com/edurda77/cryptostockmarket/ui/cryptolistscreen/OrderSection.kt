package com.edurda77.cryptostockmarket.ui.cryptolistscreen

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.edurda77.cryptostockmarket.domain.util.CoinOrder
import com.edurda77.cryptostockmarket.domain.util.OrderType

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    coinOrder: CoinOrder = CoinOrder.Symbol(OrderType.Ascending),
    onOrderChange: (CoinOrder) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Название",
                selected = coinOrder is CoinOrder.Name,
                onSelect = { onOrderChange(CoinOrder.Name(coinOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(4.dp))
            DefaultRadioButton(
                text = "Обозначание",
                selected = coinOrder is CoinOrder.Symbol,
                onSelect = { onOrderChange(CoinOrder.Symbol(coinOrder.orderType)) }
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "По возрастанию",
                selected = coinOrder.orderType is OrderType.Ascending,
                onSelect = {
                    onOrderChange(coinOrder.copy(OrderType.Ascending))
                }
            )
            Spacer(modifier = Modifier.width(4.dp))
            DefaultRadioButton(
                text = "По убыванию",
                selected = coinOrder.orderType is OrderType.Descending,
                onSelect = {
                    onOrderChange(coinOrder.copy(OrderType.Descending))
                }
            )
        }
    }
}