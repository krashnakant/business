package com.flixbus.business.domain

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "agency")
data class Agency(
    @Id
    val id: ObjectId = ObjectId.get(),
    val name: String,
    val countryCode: CountryCode,
    val country: String,
    val city: String,
    val street: String,
    val currency: Currency,
    val contactPerson: String
)
