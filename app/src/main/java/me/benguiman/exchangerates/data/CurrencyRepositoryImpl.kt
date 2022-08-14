package me.benguiman.exchangerates.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.benguiman.exchangerates.data.db.CurrencyEntity
import me.benguiman.exchangerates.data.db.ExchangeRatesDatabase
import me.benguiman.exchangerates.di.IoDispatcher
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val database: ExchangeRatesDatabase,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) : CurrencyRepository {
    override suspend fun getAllCurrencies(): List<CurrencyEntity> {
        return withContext(coroutineDispatcher) {
            database.currencyDao().getAll()
        }
    }
}