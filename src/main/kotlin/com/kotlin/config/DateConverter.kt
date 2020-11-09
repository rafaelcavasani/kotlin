package com.kotlin.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.format.datetime.DateFormatter
import org.springframework.format.datetime.DateFormatterRegistrar
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar
import org.springframework.format.support.DefaultFormattingConversionService
import org.springframework.format.support.FormattingConversionService
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport
import java.time.format.DateTimeFormatter

@Configuration
class DateTimeConfig : WebMvcConfigurationSupport() {
    @Bean
    override fun mvcConversionService(): FormattingConversionService {
        val conversionService = DefaultFormattingConversionService(false)
        val dateTimeRegistrar = DateTimeFormatterRegistrar()
        dateTimeRegistrar.setDateFormatter(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        dateTimeRegistrar.setDateTimeFormatter(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        dateTimeRegistrar.registerFormatters(conversionService)
        val dateRegistrar = DateFormatterRegistrar()
        dateRegistrar.setFormatter(DateFormatter("yyyy-MM-dd"))
        dateRegistrar.registerFormatters(conversionService)
        return conversionService
    }
}