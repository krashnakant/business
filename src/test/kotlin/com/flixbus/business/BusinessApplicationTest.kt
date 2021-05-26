package com.flixbus.business

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class BusinessApplicationTest {

    @Test
    fun main() {
        assertDoesNotThrow { main(arrayOf<String>()) }
    }
}
