package com.flixbus.business.service

import com.flixbus.business.domain.Agency
import com.flixbus.business.domain.CountryCode
import com.flixbus.business.domain.Currency
import com.flixbus.business.repository.AgencyRepository
import com.flixbus.business.service.dto.AgenciesDTO
import com.flixbus.business.service.dto.CreateOrUpdateAgencyDTO
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class AgencyService(private val agencyRepository: AgencyRepository) {

    fun getAllAgencies(): List<AgenciesDTO> {
        return this.agencyRepository.findAll().map { it.apiToDto() }
    }

    fun save(createOrUpdateAgency: CreateOrUpdateAgencyDTO) {
        this.agencyRepository.save(createOrUpdateAgency.dtoToApi())
    }

    fun update(updateOrUpdateAgency: CreateOrUpdateAgencyDTO, id: String) {
        this.agencyRepository.findById(id).ifPresent {
            this.agencyRepository.save(updateOrUpdateAgency.dtoToApi().copy(id = ObjectId(id)))
        }
    }

    fun deleteById(id: String) {
        this.agencyRepository.deleteById(id)
    }

    fun CreateOrUpdateAgencyDTO.dtoToApi(): Agency {
        return Agency(
            name = name,
            city = city,
            street = street,
            contactPerson = contactPerson,
            country = country,
            countryCode = CountryCode.valueOf(countryCode),
            currency = Currency.valueOf(currency)
        )
    }

    fun Agency.apiToDto(): AgenciesDTO {
        return AgenciesDTO(
            id = id.toString(),
            name = name,
            city = city,
            street = street,
            contactPerson = contactPerson,
            country = country,
            countryCode = countryCode.name,
            currency = currency.name
        )
    }
}
