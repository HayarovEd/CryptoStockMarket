package com.edurda77.cryptostockmarket.domain.repository

import com.edurda77.cryptostockmarket.domain.entities.Coin
import com.edurda77.cryptostockmarket.domain.entities.CoinInfo
import com.edurda77.cryptostockmarket.domain.entities.HistoryPrice
import com.edurda77.cryptostockmarket.utils.Resource
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

interface Repository {
    suspend fun getCoins(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<Coin>>>
    suspend fun getCoinInfo(
        id: String,
    ): Resource<CoinInfo>

    suspend fun getCoinHistoryPrice(
        id: String,
        beginDateTime: LocalDateTime,
        endDateTime: LocalDateTime
    ): Resource<List<HistoryPrice>>
}