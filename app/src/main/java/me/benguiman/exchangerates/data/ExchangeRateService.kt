package me.benguiman.exchangerates.data

import retrofit2.http.GET
import retrofit2.http.Path

interface ExchangeRateService {

    @GET("latest/{currency}")
    suspend fun exchangeRate(@Path("currency") currency: String): ExchangeRateResponse
}