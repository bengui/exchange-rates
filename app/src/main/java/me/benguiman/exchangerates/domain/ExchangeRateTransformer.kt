package me.benguiman.exchangerates.domain

import me.benguiman.exchangerates.data.ExchangeRate
import me.benguiman.exchangerates.data.db.CurrencyEntity

fun transformMapToExchangeRate(
    exchangeRateMap: Map<String, Double>,
    currencies: List<CurrencyEntity>
): List<ExchangeRate> =
    currencies
        .filter { exchangeRateMap.containsKey(it.currencyCode) }
        .map {
            ExchangeRate(
                currencyCode = it.currencyCode,
                exchangeRate = exchangeRateMap.getValue(it.currencyCode),
                region = it.region,
                twoLetterCountryRegion = it.twoLetterRegionCode,
                currencyName = it.currencyName
            )
        }
