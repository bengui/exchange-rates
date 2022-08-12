package me.benguiman.exchangerates.ui

import me.benguiman.exchangerates.data.ExchangeRate

data class AllExchangeRatesUiState(
    val exchangeRates: List<ExchangeRate> = emptyList(),
    val errorMessage : String = ""
)