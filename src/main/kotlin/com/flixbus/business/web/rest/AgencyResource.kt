package com.flixbus.business.web.rest

import com.flixbus.business.service.AgencyService
import com.flixbus.business.service.dto.AgenciesDTO
import com.flixbus.business.service.dto.CreateOrUpdateAgencyDTO
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class AgencyResource(private val agencyService: AgencyService) {

    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping("/agencies")
    fun getAll(): ResponseEntity<List<AgenciesDTO>> = ResponseEntity.ok(agencyService.getAllAgencies())

    @PostMapping("/agencies")
    fun createAgency(@RequestBody request: CreateOrUpdateAgencyDTO): ResponseEntity<*> {
        log.debug("REST request to save agency : {}", request)
        return ResponseEntity.ok(agencyService.save(request))
    }

    @PutMapping("/agencies/{id}")
    fun updateAgency(@RequestBody request: CreateOrUpdateAgencyDTO, @PathVariable id: String): ResponseEntity<*> {
        log.debug("REST request to update agency : {}", request)
        return ResponseEntity.ok(agencyService.update(request, id))
    }

    @DeleteMapping("/agencies/{id}")
    fun delete(@PathVariable id: String): ResponseEntity<*> {
        log.debug("REST request to delete agency : {}", id)
        return ResponseEntity.ok(this.agencyService.deleteById(id))
    }
}
