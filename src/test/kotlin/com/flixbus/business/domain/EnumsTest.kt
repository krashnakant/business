package com.flixbus.business.domain

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class EnumsTest {

    @Test
    fun `Country Enum should return correct values`() {

        Assertions.assertEquals(CountryCode.DEU.name, "DEU")
        Assertions.assertEquals(CountryCode.GBR.name, "GBR")
        Assertions.assertEquals(CountryCode.FRA.name, "FRA")
        Assertions.assertEquals(CountryCode.ITA.name, "ITA")
        Assertions.assertEquals(CountryCode.USA.name, "USA")
        Assertions.assertEquals(CountryCode.USA.country, "United State")
    }

    @Test
    fun `Currency Enum should return correct value`() {

        Assertions.assertEquals(Currency.EUR.name, "EUR")
        Assertions.assertEquals(Currency.GBP.name, "GBP")
        Assertions.assertEquals(Currency.USD.name, "USD")
    }
}
