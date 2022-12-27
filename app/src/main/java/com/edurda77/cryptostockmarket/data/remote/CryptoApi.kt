package com.edurda77.cryptostockmarket.data.remote

import com.edurda77.cryptostockmarket.data.remote.dto.CoinByIdDto
import com.edurda77.cryptostockmarket.data.remote.dto.CryptoCurrenciesDto
import com.edurda77.cryptostockmarket.data.remote.dto.HistoryPriceCoinDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CryptoApi {
    @GET("coins/list")
    suspend fun getListCoin() : List<CryptoCurrenciesDto>
    @GET ("coins/{id}")
    suspend fun getCoinById(
        @Path("id") id: String
    ) : CoinByIdDto
    @GET ("coins/{id}/market_chart/range")
    suspend fun getHistoryRange(
        @Path("id") id: String,
        @Query("vs_currency") currency: String = "usd",
        @Query ("from") beginDate: Long,
        @Query ("to") endDate: Long
    ) : HistoryPriceCoinDto
}