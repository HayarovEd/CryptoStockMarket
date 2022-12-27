package com.edurda77.cryptostockmarket.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CryptoListingEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val symbol: String
)