package com.mameli

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.Signature
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.slf4j.LoggerFactory
import org.slf4j.event.Level
import kotlin.system.measureTimeMillis

@Aspect
open class LoggingAspect {

    private val log = LoggerFactory.getLogger(this::class.java)

    @Around("@annotation(com.mameli.Logging)")
    fun logExecutionTime(joinPoint: ProceedingJoinPoint): Any? {
        val method = (joinPoint.signature as MethodSignature).method
        val annotation = method.getAnnotation(Logging::class.java)

        val afterMillis = annotation.afterMillis

        var result: Any?
        val time = measureTimeMillis {
            result = joinPoint.proceed()
        }

        if (time > afterMillis) {
            log(joinPoint.signature, time, annotation.logLevel)
        }
        return result
    }

    private fun log(signature: Signature, time: Long, level: Level) {
        val msg = "Method [${signature}] executed in ${time}ms"
        when (level) {
            Level.TRACE -> log.trace(msg)
            Level.DEBUG -> log.debug(msg)
            Level.INFO -> log.info(msg)
            Level.WARN -> log.warn(msg)
            Level.ERROR -> log.error(msg)
        }
    }

}
