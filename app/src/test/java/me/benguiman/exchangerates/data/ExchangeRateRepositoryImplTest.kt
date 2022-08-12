package me.benguiman.exchangerates.data

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ExchangeRateRepositoryImplTest {

    lateinit var exchangeRateRepositoryImpl: ExchangeRateRepositoryImpl
    private val exchangeRateMap = mapOf(
        "USD" to 1.0,
        "AED" to 3.6725,
        "AFN" to 89.684242,
        "ALL" to 113.494061,
        "AMD" to 405.350234,
        "ANG" to 1.79,
        "AOA" to 429.24225,
        "ARS" to 133.516963,
        "AUD" to 1.40703,
        "AWG" to 1.79,
        "AZN" to 1.694988,
        "BAM" to 1.895484,
        "BBD" to 2.0,
        "BDT" to 94.169892,
        "BGN" to 1.89501,
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        exchangeRateRepositoryImpl = ExchangeRateRepositoryImpl(
            exchangeRateService = object : ExchangeRateService {
                override suspend fun exchangeRate(currency: String) =
                    ExchangeRateResponse("USD", exchangeRateMap)
            },
            coroutineDispatcher = UnconfinedTestDispatcher()
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getExchangeRate_forceRefresh_validList() {
        runTest {
            val exchangeRateMap = exchangeRateRepositoryImpl.getExchangeRate("USD", true)

            assert(exchangeRateMap["USD"] == 1.0)
            assert(exchangeRateMap["BGN"] == 1.89501)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getExchangeRate_noRefresh_validList() {
        runTest {
            val exchangeRateMap = exchangeRateRepositoryImpl.getExchangeRate("USD", false)

            assert(exchangeRateMap["USD"] == 1.0)
            assert(exchangeRateMap["BGN"] == 1.89501)
        }
    }
}