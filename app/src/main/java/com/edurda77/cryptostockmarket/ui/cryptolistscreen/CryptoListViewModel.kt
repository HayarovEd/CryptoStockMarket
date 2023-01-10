package com.edurda77.cryptostockmarket.ui.cryptolistscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edurda77.cryptostockmarket.domain.usecases.GetCoins
import com.edurda77.cryptostockmarket.domain.util.CoinOrder
import com.edurda77.cryptostockmarket.domain.util.OrderType
import com.edurda77.cryptostockmarket.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Delay
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoListViewModel @Inject constructor(private val getCoins: GetCoins) : ViewModel() {
    var state by mutableStateOf(CryptoListState())

    private var getCoinsJob: Job? = null

    init {
        getCoins(CoinOrder.Symbol(OrderType.Ascending), true, "")
    }

    fun onEvent(event: CryptoCoinsEvent) {
        when (event) {
            is CryptoCoinsEvent.OnSearch -> {
                state = state.copy(searchQuery = event.query)
                getCoins(search = event.query)
            }
            is CryptoCoinsEvent.Order -> {
                if (state.coinOrder::class == event.coinOrder::class && state.coinOrder.orderType == event.coinOrder.orderType) {
                    return
                }
                getCoins(coinOrder = event.coinOrder)
            }
            is CryptoCoinsEvent.Refresh -> {

                getCoins(
                    isRefresh = true,
                    search = ""
                )
            }
        }
    }

    private fun getCoins(
        coinOrder: CoinOrder = state.coinOrder,
        isRefresh: Boolean = false,
        search: String = state.searchQuery.lowercase()
    ) {
        getCoinsJob?.cancel()
        state = state.copy(isloading = true)
        viewModelScope.launch {
            getCoinsJob =
                getCoins.invoke(coinOrder = coinOrder, fetchFromRemote = isRefresh, query = search)
                    .onEach { result ->
                        state = when (result) {
                            is Resource.Success -> {
                                state.copy(
                                    isloading = false,
                                    cryptoCoins = result.data ?: emptyList(),
                                    coinOrder = coinOrder
                                )
                            }
                            is Resource.Error -> {
                                state.copy(
                                    isloading = false,
                                    error = result.message
                                )
                            }
                        }
                    }
                    .launchIn(viewModelScope)
        }
    }
}