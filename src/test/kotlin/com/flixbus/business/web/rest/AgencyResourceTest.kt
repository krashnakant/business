package com.flixbus.business.web.rest

import com.fasterxml.jackson.databind.ObjectMapper
import com.flixbus.business.TestObject.agenciesDTO
import com.flixbus.business.TestObject.agencyObject
import com.flixbus.business.TestObject.createAgencyDto
import com.flixbus.business.repository.AgencyRepository
import com.flixbus.business.service.AgencyService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.just
import io.mockk.runs
import org.bson.types.ObjectId
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest
class AgencyResourceTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var agencyService: AgencyService

    @MockkBean
    private lateinit var agencyRepository: AgencyRepository

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun getAllAgenciesResultInOk() {

        every {
            agencyService.run {
                agencyObject.apiToDto()
            }
        } returns agenciesDTO

        val apiToDto = agencyService.run {
            agencyObject.apiToDto()
        }
        every { agencyService.getAllAgencies() } returns listOf(
            apiToDto.copy(id = "id"),
            apiToDto.copy(id = "ida", name = "anotherName")
        )
        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/agencies")
        ).andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun createAgencyResultInOk() {

        every { agencyService.save(createAgencyDto) } just runs

        every { agencyService.run { createAgencyDto.dtoToApi() } } returns agencyObject

        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/agencies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createAgencyDto))
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun updateAgencyResultInOk() {
        every { agencyService.update(createAgencyDto, "fooId") } just runs
        every { agencyService.run { createAgencyDto.dtoToApi() } } returns agencyObject.copy(id = ObjectId.get())

        mockMvc.perform(
            MockMvcRequestBuilders.put("/api/agencies/{id}", "fooId")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createAgencyDto))
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun deleteAgencyResultInOk() {

        every { agencyService.deleteById("fooId") } just runs
        mockMvc.perform(
            MockMvcRequestBuilders.delete("/api/agencies/{id}", "fooId")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk)
    }
}
