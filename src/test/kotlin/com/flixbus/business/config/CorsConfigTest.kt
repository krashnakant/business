package com.flixbus.business.config

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpMethod
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CorsConfigTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    // testing are with localhost
    val URL = "http://localhost:4300"

    @Test
    fun `Cors Options request with Origins inside the whitelist should get 200 in response`() {
        mockMvc
            .perform(
                MockMvcRequestBuilders.options("/api/agencies")
                    .header("Access-Control-Request-Method", HttpMethod.GET)
                    .header("Origin", URL)
            ).andExpect(MockMvcResultMatchers.status().isOk)

        mockMvc
            .perform(
                MockMvcRequestBuilders.options("/api/agencies")
                    .header("Access-Control-Request-Method", HttpMethod.POST)
                    .header("Origin", URL)
            ).andExpect(MockMvcResultMatchers.status().isOk)

        mockMvc
            .perform(
                MockMvcRequestBuilders.options("/api/agencies/{id}", "fooId")
                    .header("Access-Control-Request-Method", HttpMethod.PUT)
                    .header("Origin", URL)
            ).andExpect(MockMvcResultMatchers.status().isOk)

        mockMvc
            .perform(
                MockMvcRequestBuilders.options("/api/agencies/{id}", "fooId")
                    .header("Access-Control-Request-Method", HttpMethod.DELETE)
                    .header("Origin", URL)
            ).andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun `Cors Options request from the other Origins should get 403 in response`() {
        mockMvc
            .perform(
                MockMvcRequestBuilders.options("/api/agencies")
                    .header("Access-Control-Request-Method", HttpMethod.GET)
                    .header("Origin", "http://xyz.de")
            )
            .andExpect(MockMvcResultMatchers.status().is4xxClientError)

        mockMvc
            .perform(
                MockMvcRequestBuilders.options("/api/agencies")
                    .header("Access-Control-Request-Method", HttpMethod.GET)
                    .header("Origin", "https://www.zyx123.de")
            )
            .andExpect(MockMvcResultMatchers.status().is4xxClientError)
    }

    @Test
    fun `Cors requests with HEAD method should return 200 in response`() {
        mockMvc
            .perform(
                MockMvcRequestBuilders.options("/api/agencies")
                    .header("Access-Control-Request-Method", HttpMethod.HEAD)
                    .header("Origin", URL)
            )
            .andExpect(MockMvcResultMatchers.status().isOk)
    }
}
