package me.benguiman.exchangerates.data.db

import org.junit.Assert.assertEquals
import org.junit.Test

class ParseCurrencyEntityListFromCsvTest {

    @Test
    fun parseCurrencyEntityListFromCsv_validList() {
        val reader = this.javaClass.classLoader!!
            .getResourceAsStream("currency_data_20220814.csv")
            .reader()

        val currencyEntityList = parseCurrencyEntityListFromCsv(reader)

        assertEquals(156, currencyEntityList.size)

        //ARUBA,Aruban Florin,AWG,533,AW,ABW
        assertEquals("AWG", currencyEntityList[0].currencyCode)
        assertEquals("Aruban Florin", currencyEntityList[0].currencyName)
        assertEquals("ARUBA", currencyEntityList[0].region)
        assertEquals("AW", currencyEntityList[0].twoLetterRegionCode)


        //ZIMBABWE,Zimbabwe Dollar,ZWL,932,ZW,ZWE
        assertEquals("ZWL", currencyEntityList[155].currencyCode)
        assertEquals("Zimbabwe Dollar", currencyEntityList[155].currencyName)
        assertEquals("ZIMBABWE", currencyEntityList[155].region)
        assertEquals("ZW", currencyEntityList[155].twoLetterRegionCode)
    }

}