package me.benguiman.exchangerates.domain

import me.benguiman.exchangerates.data.CurrencyRepository
import me.benguiman.exchangerates.data.ExchangeRate
import me.benguiman.exchangerates.data.ExchangeRateRepository
import javax.inject.Inject


class GetAllExchangeRatesUseCase @Inject constructor(
    private val exchangeRateRepository: ExchangeRateRepository,
    private val currencyRepository: CurrencyRepository
) {
    suspend operator fun invoke(): List<ExchangeRate> {
        val exchangeRateMap = exchangeRateRepository.getExchangeRate("USD", false)

        val currencies = currencyRepository.getAllCurrencies()

        return transformMapToExchangeRate(exchangeRateMap, currencies)
    }
}
