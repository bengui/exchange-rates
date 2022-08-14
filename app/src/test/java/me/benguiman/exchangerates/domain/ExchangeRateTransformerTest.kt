package me.benguiman.exchangerates.domain

import me.benguiman.exchangerates.data.db.CurrencyEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ExchangeRateTransformerTest {

    @Test
    fun transformMapToExchangeRate_valid() {
        val exchangeRateMap = mapOf(
            "USD" to 1.0,
            "AED" to 3.6725,
            "AFN" to 89.684242,
            "ALL" to 113.494061,
            "EUR" to 0.969186,
        )

        val currencies = listOf(
            CurrencyEntity(
                currencyCode = "USD",
                currencyName = "US Dollar",
                region = "UNITED STATES OF AMERICA",
                twoLetterRegionCode = "US"
            ),
            CurrencyEntity(
                currencyCode = "EUR",
                currencyName = "Euro",
                region = "EUROPEAN UNION",
                twoLetterRegionCode = "EU"
            )
        )

        val exchangeRateList = transformMapToExchangeRate(exchangeRateMap, currencies)

        assertEquals(2, exchangeRateList.size)

        assertEquals("USD", exchangeRateList[0].currencyCode)
        assertTrue(1.0 == exchangeRateList[0].exchangeRate)
        assertEquals("US Dollar", exchangeRateList[0].currencyName)
        assertEquals("UNITED STATES OF AMERICA", exchangeRateList[0].region)
        assertEquals("US", exchangeRateList[0].twoLetterCountryRegion)

        assertEquals("EUR", exchangeRateList[1].currencyCode)
        assertTrue(0.969186 == exchangeRateList[1].exchangeRate)
        assertEquals("Euro", exchangeRateList[1].currencyName)
        assertEquals("EUROPEAN UNION", exchangeRateList[1].region)
        assertEquals("EU", exchangeRateList[1].twoLetterCountryRegion)
    }
}