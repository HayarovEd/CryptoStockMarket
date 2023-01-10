package com.edurda77.cryptostockmarket.ui.cryptolistscreen

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.edurda77.cryptostockmarket.R
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
                text = stringResource(R.string.name),
                selected = coinOrder is CoinOrder.Name,
                onSelect = { onOrderChange(CoinOrder.Name(coinOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(4.dp))
            DefaultRadioButton(
                text = stringResource(R.string.symbol),
                selected = coinOrder is CoinOrder.Symbol,
                onSelect = { onOrderChange(CoinOrder.Symbol(coinOrder.orderType)) }
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = stringResource(R.string.ascend),
                selected = coinOrder.orderType is OrderType.Ascending,
                onSelect = {
                    onOrderChange(coinOrder.copy(OrderType.Ascending))
                }
            )
            Spacer(modifier = Modifier.width(4.dp))
            DefaultRadioButton(
                text = stringResource(R.string.descend),
                selected = coinOrder.orderType is OrderType.Descending,
                onSelect = {
                    onOrderChange(coinOrder.copy(OrderType.Descending))
                }
            )
        }
    }
}