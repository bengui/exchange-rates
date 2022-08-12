package me.benguiman.exchangerates.domain

import me.benguiman.exchangerates.data.ExchangeRate
import me.benguiman.exchangerates.data.ExchangeRateRepository
import javax.inject.Inject


class GetAllExchangeRatesUseCase @Inject constructor(
    private val exchangeRateRepository: ExchangeRateRepository,
) {
    suspend operator fun invoke(): List<ExchangeRate> {
        val exchangeRateMap = exchangeRateRepository.getExchangeRate("USD", false)

        return transformMapToExchangeRate(exchangeRateMap)
    }
}