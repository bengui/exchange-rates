package me.benguiman.exchangerates.data

data class ExchangeRate(
    val currency: String,
    val exchangeRate: Double,
    val twoLetterCountryRegion: String, //ISO-3166 country code
    val currencyName: String
)