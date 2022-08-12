package me.benguiman.exchangerates.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import me.benguiman.exchangerates.di.IoDispatcher
import javax.inject.Inject

class ExchangeRateRepositoryImpl @Inject constructor(
    private val exchangeRateService: ExchangeRateService,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) : ExchangeRateRepository {

    private val latestExchangeRateMutex = Mutex()
    private var latestExchangeRateList: Map<String, Double> = emptyMap()

    override suspend fun getExchangeRate(
        currency: String,
        refresh: Boolean
    ): Map<String, Double> {
        return withContext(coroutineDispatcher) {
            if (refresh || latestExchangeRateList.isEmpty()) {
                val exchangeRateList = exchangeRateService
                    .exchangeRate(currency)
                    .exchangeRateList

                latestExchangeRateMutex.withLock {
                    latestExchangeRateList = exchangeRateList
                }
            }
            latestExchangeRateMutex.withLock { latestExchangeRateList }
        }
    }

}