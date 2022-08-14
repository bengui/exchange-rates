package me.benguiman.exchangerates.data

import me.benguiman.exchangerates.data.db.CurrencyEntity

interface CurrencyRepository {

    suspend fun getAllCurrencies() : List<CurrencyEntity>
}