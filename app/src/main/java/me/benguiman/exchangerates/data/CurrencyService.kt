package me.benguiman.exchangerates.data

import retrofit2.Call
import retrofit2.http.GET

interface CurrencyService {

    @GET("latest/{currency}")
    fun currencyList(currency : String) : Call<CurrencyListResponse>

}