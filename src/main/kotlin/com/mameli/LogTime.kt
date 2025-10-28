package com.mameli

import org.slf4j.event.Level


/**
 * Annotation used to log method execution details.
 *
 * Can be applied to functions, and the associated [LogTimeAspect] will intercept the call.
 * You can configure the minimum execution time threshold, log level, and whether
 * method parameters should be included in the log output.
 *
 * Example:
 * ```
 * @LogTime(afterMillis = 500, logLevel = Level.DEBUG, logParams = false)
 * fun someExpensiveMethod(input: String): String { ... }
 * ```
 *
 * @property beforeMillis maximum execution time (inclusive) in milliseconds required to trigger a log message.
 * Default is [Long.MAX_VALUE].
 * @property afterMillis Minimum execution time in milliseconds required to trigger a log message.
 * Default is `-1`, which disables time-based logging.
 * @property logLevel The logging level to be used (default is [Level.INFO]).
 * @property logParams Whether the parameter values should be logged (true) or just their types (false).
 */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class LogTime(
    val beforeMillis: Long = Long.MAX_VALUE,
    val afterMillis: Long = -1,
    val logLevel: Level = Level.INFO,
    val logParams: Boolean = true
)
