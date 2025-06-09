package com.mameli

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.AutoConfiguration
import kotlin.system.measureTimeMillis

@Aspect
@AutoConfiguration
open class LoggingAspect {

    private val log = LoggerFactory.getLogger(this::class.java)

    @Around("@annotation(com.mameli.Logging)")
    fun logExecutionTime(joinPoint: ProceedingJoinPoint): Any? {
        var result: Any?
        val time = measureTimeMillis {
            result = joinPoint.proceed()
        }
        log.info("Method [${joinPoint.signature}] executed in ${time}ms")
        return result
    }

}
