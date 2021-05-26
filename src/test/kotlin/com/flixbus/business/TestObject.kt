package com.flixbus.business

import com.flixbus.business.domain.Agency
import com.flixbus.business.domain.CountryCode
import com.flixbus.business.domain.Currency
import com.flixbus.business.service.dto.AgenciesDTO
import com.flixbus.business.service.dto.CreateOrUpdateAgencyDTO

object TestObject {

    val agencyObject = Agency(
        name = "name", country = "country", street = "street", contactPerson = "contactPerson",
        city = "city", currency = Currency.EUR, countryCode = CountryCode.DEU
    )

    val createAgencyDto = CreateOrUpdateAgencyDTO(
        name = "name", city = "city", street = "street", contactPerson = "contactPerson",
        country = "country", countryCode = "DEU", currency = "EUR"
    )

    val agenciesDTO = AgenciesDTO(
        id = "id", name = "name", city = "city", street = "street", contactPerson = "contactPerson",
        country = "country", countryCode = "DEU", currency = "EUR"
    )
}
