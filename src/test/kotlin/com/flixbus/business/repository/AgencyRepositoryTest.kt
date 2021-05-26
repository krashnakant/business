package com.flixbus.business.repository

import com.flixbus.business.domain.Agency
import com.flixbus.business.domain.CountryCode
import com.flixbus.business.domain.Currency
import org.bson.types.ObjectId
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DataMongoTest
class AgencyRepositoryTest {

    @Autowired
    lateinit var agencyRepository: AgencyRepository

    @DisplayName(
        "given agency object to save" +
            " when save object using MongoRepository" +
            " then object is saved"
    )
    @Test
    fun save() {
        agencyRepository.save(agencyObject)
        val result = agencyRepository.findAll().size
        Assertions.assertEquals(result, 1)
    }

    @DisplayName(
        "given no object saved into db" +
            " when findAll function using MongoRepository" +
            " then return empty list of agencies"
    )

    @Test
    fun findAllWithEmptyList() {
        Assertions.assertTrue(agencyRepository.findAll().isEmpty())
    }

    @DisplayName(
        "given already two saved object saved into db" +
            " when findAll function using MongoRepository" +
            " then return list of agencies"
    )
    @Test
    fun findAllAgencies() {
        val copyAgencyObject = agencyObject.copy(name = "anotherName")
        agencyRepository.saveAll(listOf(copyAgencyObject, agencyObject.copy(id = ObjectId.get())))
        Assertions.assertTrue(agencyRepository.findAll().isNotEmpty())
        Assertions.assertEquals(agencyRepository.findAll().size, 2)
    }

    @DisplayName(
        "given agency object to update" +
            " when save object using MongoRepository" +
            " then object should update"
    )
    @Test
    fun updateAgencies() {
        agencyRepository.save(agencyObject)
        val savedAgency = agencyRepository.findAll()[0]
        agencyRepository.save(savedAgency.copy(name = "newUpdateName"))
        val result = agencyRepository.findById(savedAgency.id.toString()).get()
        Assertions.assertEquals(result.name, "newUpdateName")
    }

    @DisplayName("given agency id to delete when object is present into db then object should delete from db")
    @Test
    fun delete() {
        agencyRepository.save(agencyObject)
        val savedAgency = agencyRepository.findAll()[0]
        agencyRepository.deleteById(savedAgency.id.toString())
        Assertions.assertTrue(agencyRepository.findAll().isEmpty())
    }

    @BeforeEach
    fun cleanup() {
        agencyRepository.deleteAll()
    }

    companion object {
        private val agencyObject = Agency(
            name = "name", country = "country", street = "street", contactPerson = "contactPerson",
            city = "city", currency = Currency.EUR, countryCode = CountryCode.DEU
        )
    }
}
