package com.mameli.component

import com.mameli.Logging
import org.springframework.stereotype.Component

@Component
open class TestLoggingComponent {

    @Logging
    open fun baseMethod() {
    }

    @Logging(logParams = true)
    open fun slowMethod(param: String): String {
        Thread.sleep(50)
        return "Hello $param"
    }

    @Logging(afterMillis = 500)
    open fun fastMethod(x: Int): Int {
        return x * 2
    }
}
