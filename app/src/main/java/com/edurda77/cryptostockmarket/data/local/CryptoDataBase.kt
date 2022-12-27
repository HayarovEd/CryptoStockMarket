package com.edurda77.cryptostockmarket.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [CryptoListingEntity::class],
    version = 1
)
abstract class CryptoDataBase: RoomDatabase() {
    abstract val dao: CryptoDao
}