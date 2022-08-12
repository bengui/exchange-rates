package me.benguiman.exchangerates.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.benguiman.exchangerates.data.ExchangeRateService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModule {
    @Singleton
    @Provides
    fun provideCurrencyService(): ExchangeRateService =
        Retrofit.Builder()
            .baseUrl("https://open.er-api.com/v6/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ExchangeRateService::class.java)

}