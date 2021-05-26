package com.flixbus.business.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class CorsConfig : WebMvcConfigurer {

    @Value("\${cors.allowed-origins}")
    private val corsAllowedOrigins: List<String> = emptyList()

    @Bean
    fun corsConfigurer() = object : WebMvcConfigurer {
        override fun addCorsMappings(registry: CorsRegistry) {
            registry.addMapping("/**")
                .allowedOrigins(*corsAllowedOrigins.toTypedArray())
                .allowedHeaders("*")
                .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE")
            super.addCorsMappings(registry)
        }
    }
}
