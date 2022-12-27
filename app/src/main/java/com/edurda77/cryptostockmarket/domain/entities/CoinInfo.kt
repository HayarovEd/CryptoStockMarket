package com.edurda77.cryptostockmarket.domain.entities

import com.edurda77.cryptostockmarket.data.remote.dto.coin_dto.Description
import com.edurda77.cryptostockmarket.data.remote.dto.coin_dto.Image

data class CoinInfo(
    val description: Description,
    val id: String,
    val image: Image,
    val symbol: String,
    val name: String
)
