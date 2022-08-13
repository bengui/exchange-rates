package me.benguiman.exchangerates.domain

import me.benguiman.exchangerates.data.ExchangeRate

fun transformMapToExchangeRate(exchangeRateMap: Map<String, Double>): List<ExchangeRate> =
    exchangeRateMap.map {
        ExchangeRate(
            currency = it.key,
            exchangeRate = it.value,
            twoLetterCountryRegion = "",
            currencyName = ""
        )
    }