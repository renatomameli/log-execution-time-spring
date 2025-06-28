package com.mameli.component

import com.mameli.Logging
import org.slf4j.LoggerFactory
import org.slf4j.event.Level
import org.springframework.stereotype.Component

@Component
open class DemoComponent {

    companion object {
        private val log = LoggerFactory.getLogger(DemoComponent::class.java)
    }

    @Logging
    open fun baseMethod() {
        log.info("foo")
    }

    @Logging(afterMillis = 1000)
    open fun afterMillis() {
        Thread.sleep(1001)
    }

    @Logging(afterMillis = 1000)
    open fun beforeMillis() {
        Thread.sleep(500)
    }

    @Logging(logLevel = Level.WARN)
    open fun debugLevel() {
        Thread.sleep(500)
    }
}
