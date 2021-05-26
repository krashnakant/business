package com.flixbus.business.repository

import com.flixbus.business.domain.Agency
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface AgencyRepository : MongoRepository<Agency, String>
