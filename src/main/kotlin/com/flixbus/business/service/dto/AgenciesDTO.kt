package com.flixbus.business.service.dto

data class AgenciesDTO(
    val id: String,
    val name: String,
    val countryCode: String,
    val country: String,
    val city: String,
    val street: String,
    val currency: String,
    val contactPerson: String
)
