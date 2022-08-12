package me.benguiman.exchangerates.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.benguiman.exchangerates.data.ExchangeRateRepository
import me.benguiman.exchangerates.data.ExchangeRateRepositoryImpl
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class DataBindingModule {

    @Singleton
    @Binds
    abstract fun bindExchangeRateRepository(exchangeRateRepositoryImpl: ExchangeRateRepositoryImpl): ExchangeRateRepository
}