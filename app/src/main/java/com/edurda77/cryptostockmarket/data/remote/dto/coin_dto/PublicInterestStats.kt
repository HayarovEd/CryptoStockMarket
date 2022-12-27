package com.edurda77.cryptostockmarket.data.remote.dto.coin_dto


import com.google.gson.annotations.SerializedName

data class PublicInterestStats(
    @SerializedName("alexa_rank")
    val alexaRank: Int,
    @SerializedName("bing_matches")
    val bingMatches: Any
)