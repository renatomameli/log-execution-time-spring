package com.mameli.component

import com.mameli.LogTime
import org.springframework.stereotype.Component

@Component
open class TestLoggingComponent {

    @LogTime
    open fun baseMethod() {
    }

    @LogTime(afterMillis = 50)
    open fun slowMethodLogAfterMillis(param: String): String {
        Thread.sleep(51)
        return "Hello $param"
    }

    @LogTime(afterMillis = 50)
    open fun fastMethodLogAfterMillis(x: Int): Int {
        return x * 2
    }

    @LogTime(beforeMillis = 50)
    open fun slowMethodBeforeMillis() {
        Thread.sleep(51)
    }

    @LogTime(beforeMillis = 50)
    open fun fastMethodBeforeMillis() {
    }

}
