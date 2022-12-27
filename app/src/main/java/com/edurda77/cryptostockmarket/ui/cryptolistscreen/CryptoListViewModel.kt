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
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoListViewModel @Inject constructor(private val getCoins: GetCoins) : ViewModel() {
    private var state by mutableStateOf(CryptoListState())

    private var getCoinsJob: Job? = null

    init {
        getCoins(CoinOrder.Symbol(OrderType.Ascending), true, "")
    }

    fun onEvent(event: CryptoCoinsEvent) {
        when (event) {
            is CryptoCoinsEvent.OnSearch -> {
                state = state.copy(searchQuery = event.query)
                getCoinsJob?.cancel()
                getCoinsJob = viewModelScope.launch {
                    getCoins(search = event.query, isRefresh = false)
                }
            }
            is CryptoCoinsEvent.Order -> {
                state = state.copy(coinOrder = event.coinOrder)
                getCoinsJob?.cancel()
                getCoinsJob = viewModelScope.launch {
                    getCoins(coinOrder = event.coinOrder, isRefresh = false)
                }
            }
            is CryptoCoinsEvent.Rerfresh -> {
                getCoins(isRefresh = true)
            }
        }
    }

    private fun getCoins(
        coinOrder: CoinOrder = state.coinOrder,
        isRefresh: Boolean,
        search: String = state.searchQuery.lowercase()
    ) {
        getCoinsJob?.cancel()
        state = state.copy(isloading = true)
        viewModelScope.launch {
            getCoinsJob = getCoins.invoke(coinOrder, isRefresh, search)
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            state = state.copy(
                                isloading = false,
                                cryptoCoins = result.data ?: emptyList()
                            )
                        }
                        is Resource.Error -> {
                            state = state.copy(
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