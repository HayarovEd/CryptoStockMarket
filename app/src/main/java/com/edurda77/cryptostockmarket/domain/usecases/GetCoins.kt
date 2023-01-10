package com.edurda77.cryptostockmarket.domain.usecases

import com.edurda77.cryptostockmarket.domain.entities.Coin
import com.edurda77.cryptostockmarket.domain.repository.Repository
import com.edurda77.cryptostockmarket.domain.util.CoinOrder
import com.edurda77.cryptostockmarket.domain.util.OrderType
import com.edurda77.cryptostockmarket.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCoins @Inject constructor (private val repository: Repository) {
    suspend operator fun invoke (
        coinOrder: CoinOrder = CoinOrder.Symbol(OrderType.Ascending),
        fetchFromRemote: Boolean,
        query: String
    ) : Flow<Resource<List<Coin>>> {
        return  flow {
            repository.getCoins(fetchFromRemote, query).collect { resource ->
                when (resource) {
                    is Resource.Error -> {
                        emit(Resource.Error(resource.message?: ""))
                    }
                    is Resource.Success -> {
                        when (coinOrder.orderType) {
                            is OrderType.Ascending -> {
                                when (coinOrder) {
                                    is CoinOrder.Symbol -> {
                                        emit(Resource.Success(resource.data?.sortedBy { it.symbol.lowercase() }))
                                    }
                                    is CoinOrder.Name -> {
                                        emit(Resource.Success(resource.data?.sortedBy { it.name.lowercase() }))
                                    }
                                }
                            }
                            is OrderType.Descending -> {
                                when (coinOrder) {
                                    is CoinOrder.Symbol -> {
                                        emit(Resource.Success(resource.data?.sortedByDescending { it.symbol.lowercase() }))
                                    }
                                    is CoinOrder.Name -> {
                                        emit(Resource.Success(resource.data?.sortedByDescending { it.name.lowercase() }))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }
}