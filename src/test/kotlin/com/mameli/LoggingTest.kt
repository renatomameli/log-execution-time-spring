package com.mameli

import com.mameli.component.DemoComponent
import com.mameli.config.LoggingAutoConfiguration
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext

@SpringBootTest(classes = [LoggingAutoConfiguration::class, DemoComponent::class])
class LoggingTest(@Autowired private val demoComponent: DemoComponent) {

    @Autowired
    lateinit var applicationContext: ApplicationContext

    @Test
    fun `aspect is loaded`() {
        assertThat(applicationContext.getBeansOfType(LoggingAspect::class.java)).isNotEmpty()
    }

    @Test
    fun `basic test`() {
        demoComponent.baseMethod()
    }

    @Test
    fun `afterMillis test`() {
        demoComponent.afterMillis()
    }

    @Test
    fun `beforeMillis test`() {
        demoComponent.beforeMillis()
    }

    @Test
    fun `level log`() {
        demoComponent.debugLevel()
    }

    @Test
    fun `log params`() {
        demoComponent.logParams("1", "LOOOOL")
    }
}