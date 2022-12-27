package com.edurda77.cryptostockmarket.di

import android.app.Application
import androidx.room.Room
import com.edurda77.cryptostockmarket.data.local.CryptoDataBase
import com.edurda77.cryptostockmarket.data.remote.CryptoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCryptoApi(): CryptoApi {
        return Retrofit.Builder()
            .baseUrl("https://api.coingecko.com/api/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CryptoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCryptoDatabase(app: Application): CryptoDataBase {
        return Room.databaseBuilder(
            app,
            CryptoDataBase::class.java,
            "cryptodb.db"
        ).build()
    }
}