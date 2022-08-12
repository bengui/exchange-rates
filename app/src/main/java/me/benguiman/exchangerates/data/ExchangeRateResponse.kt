package me.benguiman.exchangerates.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ExchangeRateResponse(
    @field:Json(name = "base_code") val baseCurrency: String,
    @field:Json(name = "rates") val exchangeRateList: Map<String, Double>
)