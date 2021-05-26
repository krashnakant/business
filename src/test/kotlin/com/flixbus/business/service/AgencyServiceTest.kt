package com.flixbus.business.service

import com.flixbus.business.TestObject
import com.flixbus.business.TestObject.agencyObject
import com.flixbus.business.domain.Agency
import com.flixbus.business.domain.CountryCode
import com.flixbus.business.domain.Currency
import com.flixbus.business.repository.AgencyRepository
import com.flixbus.business.service.dto.CreateOrUpdateAgencyDTO
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.bson.types.ObjectId
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.util.Optional

internal class AgencyServiceTest {

    private val agencyRepository = mockk<AgencyRepository>()
    private val agencyService: AgencyService = AgencyService(agencyRepository = agencyRepository)

    @DisplayName(
        "given nothing" +
            " when objects are already persist into db" +
            " then should return list of agency object"
    )

    @Test
    fun getAllAgenciesWithAgencyObject() {
        every { agencyRepository.findAll() } returns listOf(
            agencyObject.copy(id = ObjectId.get()),
            agencyObject.copy(id = ObjectId.get(), name = "newName")
        )

        val result = agencyService.getAllAgencies()
        verify(atLeast = 1) { agencyRepository.findAll() }
        Assertions.assertEquals(result.size, 2)
    }

    @DisplayName(
        "given nothing" +
            " when no object are present into db" +
            " then should return empty list of agency object"
    )

    @Test
    fun getAllAgenciesWithEmptyAgencyObject() {
        every { agencyRepository.findAll() } returns emptyList()
        val result = agencyService.getAllAgencies()
        verify(atLeast = 1) { agencyRepository.findAll() }
        Assertions.assertTrue(result.isEmpty())
    }

    @DisplayName(
        "given agency object to update" +
            " when object is not present in db" +
            " then do nothing"
    )
    @Test
    fun updateWithOutAgencyExists() {
        val id = ObjectId.get().toString()
        every { agencyRepository.findById(id) } returns Optional.empty()

        agencyService.update(TestObject.createAgencyDto.copy(name = "updateName"), id)

        verify(atLeast = 1) { agencyRepository.findById(id) }
        verify(exactly = 0) { agencyRepository.hint(Agency::class).save(agencyObject) }
    }

    @DisplayName(
        "given agency object to update" +
            " when object is present in db" +
            " then update object"
    )
    @Test
    fun updateWithAgencyExists() {
        val id = ObjectId.get()
        val agencyObjectWithID = agencyObject.copy(id = id, name = "updateName")
        every { agencyRepository.hint(Agency::class).save<Agency>(agencyObjectWithID) } returns agencyObjectWithID
        every { agencyRepository.findById(id.toString()) } returns Optional.of(
            agencyObjectWithID
        )

        agencyService.update(TestObject.createAgencyDto.copy(name = "updateName"), id.toString())

        verify(atLeast = 1) { agencyRepository.findById(id.toString()) }
        verify(atLeast = 1) { agencyRepository.hint(Agency::class).save(agencyObjectWithID) }
    }

    @DisplayName(
        "given agency dto object to save" +
            " when save object using agencyService" +
            " then agency object is saved"
    )

    @Test
    fun save() {
        val dtoRequest = dtoRequest.copy(id = ObjectId.get())
        every { agencyRepository.hint(Agency::class).save<Agency>(any()) } returns dtoRequest
        agencyService.save(TestObject.createAgencyDto)
    }

    @DisplayName("given id to delete when object is present into db then object should delete from db")
    @Test
    fun deleteById() {

        val id = "qwertyuiop"

        every { agencyService.deleteById(id) } just runs
        agencyService.deleteById(id)
        verify(atLeast = 1) { agencyService.deleteById(id) }
        verify(atLeast = 1) { agencyRepository.deleteById(id) }
    }

    @DisplayName("should mapped correctly from dto to agency object")
    @Test
    fun dtoToAgencyMapper() {

        Assertions.assertEquals(dtoRequest.city, "city")
        Assertions.assertEquals(dtoRequest.name, "name")
        Assertions.assertEquals(dtoRequest.street, "street")
        Assertions.assertEquals(dtoRequest.contactPerson, "contactPerson")
        Assertions.assertEquals(dtoRequest.country, "country")
        Assertions.assertEquals(dtoRequest.countryCode, CountryCode.DEU)
        Assertions.assertEquals(dtoRequest.currency, Currency.EUR)
    }

    @DisplayName("should mapped correctly from agency to dto object")
    @Test
    fun agencyToDtoMapper() {

        Assertions.assertEquals(apiToDto.city, "city")
        Assertions.assertEquals(apiToDto.name, "name")
        Assertions.assertEquals(apiToDto.street, "street")
        Assertions.assertEquals(apiToDto.contactPerson, "contactPerson")
        Assertions.assertEquals(apiToDto.country, "country")
        Assertions.assertEquals(apiToDto.countryCode, CountryCode.DEU.name)
        Assertions.assertEquals(apiToDto.currency, Currency.EUR.name)
    }

    private val dtoRequest = agencyService.run {
        CreateOrUpdateAgencyDTO(
            name = "name",
            city = "city",
            street = "street",
            contactPerson = "contactPerson",
            country = "country",
            countryCode = "DEU",
            currency = "EUR"
        ).dtoToApi()
    }

    private val apiToDto = agencyService.run {
        Agency(
            name = "name",
            city = "city",
            street = "street",
            contactPerson = "contactPerson",
            country = "country",
            countryCode = CountryCode.DEU,
            currency = Currency.EUR
        ).apiToDto()
    }
}
