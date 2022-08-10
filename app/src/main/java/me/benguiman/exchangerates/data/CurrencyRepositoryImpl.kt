package me.benguiman.exchangerates.data

import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    val currencyService: CurrencyService
) : CurrencyRepository {

}