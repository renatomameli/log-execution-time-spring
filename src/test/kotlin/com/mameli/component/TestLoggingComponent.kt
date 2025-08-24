package com.mameli.component

import com.mameli.Logging
import org.springframework.stereotype.Component

@Component
open class TestLoggingComponent {

    @Logging
    open fun baseMethod() {
    }

    @Logging(afterMillis = 50)
    open fun slowMethodLogAfterMillis(param: String): String {
        Thread.sleep(51)
        return "Hello $param"
    }

    @Logging(afterMillis = 50)
    open fun fastMethodLogAfterMillis(x: Int): Int {
        return x * 2
    }

    @Logging(beforeMillis = 50)
    open fun slowMethodBeforeMillis() {
        Thread.sleep(51)
    }

}
