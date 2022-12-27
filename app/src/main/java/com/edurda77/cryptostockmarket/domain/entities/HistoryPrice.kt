package com.edurda77.cryptostockmarket.domain.entities

import java.time.LocalDateTime

data class HistoryPrice(
    val date: LocalDateTime,
    val price: Double
)
