package com.mameli.component

import com.mameli.LogTime
import org.springframework.stereotype.Component

@Component
@LogTime
open class TestLoggingClassAnnotatedComponent {

    open fun baseMethod() {
    }

}
