package me.benguiman.exchangerates.data

data class ExchangeRate(
    val currencyCode: String,
    val exchangeRate: Double,
    val region: String,
    val twoLetterCountryRegion: String, //ISO-3166 country code
    val currencyName: String
)