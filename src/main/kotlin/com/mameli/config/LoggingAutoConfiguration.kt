package com.mameli.config

import com.mameli.LoggingAspect
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.EnableAspectJAutoProxy

@AutoConfiguration
@EnableAspectJAutoProxy
open class LoggingAutoConfiguration {

    @Bean
    open fun loggingAspect() = LoggingAspect()
}
