package com.edurda77.cryptostockmarket.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CryptoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCryptoListings(
        cryptoListingEntities: List<CryptoListingEntity>
    )

    @Query("DELETE FROM cryptoListingEntity")
    suspend fun clearCryptoListings()

    @Query(
        """
            SELECT * 
            FROM cryptoListingEntity
            WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' OR
                UPPER(:query) == symbol
        """
    )
    suspend fun searchCryptoListing(query: String): List<CryptoListingEntity>
}