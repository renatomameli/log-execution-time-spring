package com.mameli

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.slf4j.LoggerFactory
import org.slf4j.event.Level
import java.lang.reflect.Method
import kotlin.system.measureTimeMillis

@Aspect
open class LoggingAspect {

    private val log = LoggerFactory.getLogger(this::class.java)

    @Around("@annotation(com.mameli.Logging)")
    fun logExecutionTime(joinPoint: ProceedingJoinPoint): Any? {
        var result: Any?
        val time = measureTimeMillis {
            result = joinPoint.proceed()
        }

        val method = (joinPoint.signature as MethodSignature).method
        val annotation = method.getAnnotation(Logging::class.java)
        val afterMillis = annotation.afterMillis

        if (time > afterMillis) {
            log(method, time, annotation.logLevel, annotation.logParams, joinPoint.args)
        }

        return result
    }

    private fun log(method: Method, time: Long, level: Level, logParams: Boolean, args: Array<Any?>) {
        val signatureStr = if (logParams) {
            formatMethodSignature(method, args.joinToString(", ") { it?.toString() ?: "null" })
        } else {
            formatMethodSignature(method, method.parameters.joinToString(", ") { it.type.simpleName })
        }

        val msg = "Method $signatureStr executed in $time ms."

        when (level) {
            Level.TRACE -> log.trace(msg)
            Level.DEBUG -> log.debug(msg)
            Level.INFO -> log.info(msg)
            Level.WARN -> log.warn(msg)
            Level.ERROR -> log.error(msg)
        }
    }

    private fun formatMethodSignature(method: Method, paramString: String): String {
        return "${method.returnType.simpleName} ${method.declaringClass.name}.${method.name}($paramString)"
    }

}
