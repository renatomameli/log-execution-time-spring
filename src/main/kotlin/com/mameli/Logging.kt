package com.mameli

import org.slf4j.event.Level


@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Logging(

    /**
     * Defines the minimum execution time in milliseconds required
     * to trigger a log message. If the method execution time exceeds
     * this threshold, it will be logged.
     *
     * The default is -1, which disables time-based logging.
     */
    val afterMillis: Long = -1,

    val logLevel: Level = Level.INFO
)
