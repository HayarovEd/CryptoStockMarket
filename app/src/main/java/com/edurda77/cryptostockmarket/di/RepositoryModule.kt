package com.edurda77.cryptostockmarket.di

import com.edurda77.cryptostockmarket.data.repository.RepositoryImpl
import com.edurda77.cryptostockmarket.domain.repository.Repository
import com.edurda77.cryptostockmarket.domain.usecases.GetCoins
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindCryptoRepository(
        repositoryImpl: RepositoryImpl
    ): Repository


}