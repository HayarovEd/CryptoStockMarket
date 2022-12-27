package com.edurda77.cryptostockmarket.data.mapper

import com.edurda77.cryptostockmarket.data.local.CryptoListingEntity
import com.edurda77.cryptostockmarket.data.remote.dto.CoinByIdDto
import com.edurda77.cryptostockmarket.data.remote.dto.CryptoCurrenciesDto
import com.edurda77.cryptostockmarket.data.remote.dto.HistoryPriceCoinDto
import com.edurda77.cryptostockmarket.domain.entities.Coin
import com.edurda77.cryptostockmarket.domain.entities.CoinInfo
import com.edurda77.cryptostockmarket.domain.entities.HistoryPrice
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

fun HistoryPriceCoinDto.toHistoryPrice(): List<HistoryPrice> {
    return this.prices.map {
        HistoryPrice(
            date = it.first().toLocalDateTime(),
            price = it.last()
        )
    }
}

fun Double.toLocalDateTime(): LocalDateTime {
    return Instant.ofEpochSecond(this.toLong())
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime()

}

fun LocalDateTime.toTimestamp(): Long {
    return atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
}

fun List<CryptoCurrenciesDto>.toCoinsEntity(): List<CryptoListingEntity> {
    return this.map {
        CryptoListingEntity(
            id = it.id,
            name = it.name,
            symbol = it.symbol
        )
    }
}

fun List<CryptoListingEntity>.toCoins(): List<Coin> {
    return this.map {
        Coin(
            id = it.id,
            name = it.name,
            symbol = it.symbol
        )
    }
}

fun CoinByIdDto.toCoinInfo(): CoinInfo {
    return CoinInfo(
        description = this.description,
        id = this.id,
        image = this.image,
        symbol = this.symbol,
        name = this.name
    )
}


