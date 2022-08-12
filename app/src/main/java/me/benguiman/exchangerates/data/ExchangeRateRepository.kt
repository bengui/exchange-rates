package me.benguiman.exchangerates.data

interface ExchangeRateRepository {
    suspend fun getExchangeRate(
        currency: String,
        refresh: Boolean
    ): Map<String, Double>
}