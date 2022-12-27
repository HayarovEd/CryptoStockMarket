package com.edurda77.cryptostockmarket.data.remote.dto.coin_dto


import com.google.gson.annotations.SerializedName

data class ReposUrl(
    @SerializedName("bitbucket")
    val bitbucket: List<Any>,
    @SerializedName("github")
    val github: List<String>
)