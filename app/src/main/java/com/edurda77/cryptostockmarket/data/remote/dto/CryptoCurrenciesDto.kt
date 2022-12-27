package com.edurda77.cryptostockmarket.data.remote.dto

import com.google.gson.annotations.SerializedName


data class CryptoCurrenciesDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("symbol")
    val symbol: String
)