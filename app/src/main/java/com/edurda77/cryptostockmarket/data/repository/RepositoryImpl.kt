package com.edurda77.cryptostockmarket.data.repository

import com.edurda77.cryptostockmarket.data.local.CryptoDataBase
import com.edurda77.cryptostockmarket.data.mapper.*
import com.edurda77.cryptostockmarket.data.remote.CryptoApi
import com.edurda77.cryptostockmarket.domain.entities.Coin
import com.edurda77.cryptostockmarket.domain.entities.CoinInfo
import com.edurda77.cryptostockmarket.domain.entities.HistoryPrice
import com.edurda77.cryptostockmarket.domain.repository.Repository
import com.edurda77.cryptostockmarket.utils.Resource
import com.edurda77.cryptostockmarket.utils.Resource.Companion.handleResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.time.LocalDateTime
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: CryptoApi,
    db: CryptoDataBase) : Repository {

    private val dao = db.dao

    override suspend fun getCoins(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<Coin>>> {
        return flow {
            val localListings = dao.searchCryptoListing(query)
            emit(Resource.Success(
                data = localListings.toCoins()
            ))
            val isDbEmpty = localListings.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if(shouldJustLoadFromCache) {
                return@flow
            }
            val remoteListings = try {
                api.getListCoin()
            } catch(e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            remoteListings?.let { listings ->
                dao.clearCryptoListings()
                dao.insertCryptoListings(
                    listings.toCoinsEntity()
                )
                emit(Resource.Success(
                    data = dao
                        .searchCryptoListing("")
                        .toCoins()
                ))
            }
        }
    }

    override suspend fun getCoinInfo(
        id: String
    ): Resource<CoinInfo> {
        return handleResponse(suspend { api.getCoinById(id).toCoinInfo() })

    }

    override suspend fun getCoinHistoryPrice(
        id: String,
        beginDateTime: LocalDateTime,
        endDateTime: LocalDateTime
    ): Resource<List<HistoryPrice>> {
        return handleResponse(suspend { api.getHistoryRange(
            id = id,
            beginDate = beginDateTime.toTimestamp(),
            endDate = endDateTime.toTimestamp()
        ).toHistoryPrice() })
    }
}