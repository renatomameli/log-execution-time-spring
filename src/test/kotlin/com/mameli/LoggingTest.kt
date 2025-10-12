package com.mameli

import com.mameli.component.TestLoggingClassAnnotatedComponent
import com.mameli.component.TestLoggingComponent
import com.mameli.config.LoggingAutoConfiguration
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.system.CapturedOutput
import org.springframework.boot.test.system.OutputCaptureExtension

@SpringBootTest(classes = [LoggingAutoConfiguration::class, TestLoggingComponent::class, TestLoggingClassAnnotatedComponent::class])
@ExtendWith(OutputCaptureExtension::class)
class LoggingTest(
    @Autowired private val testLoggingComponent: TestLoggingComponent,
    @Autowired private val testLoggingClassAnnotatedComponent: TestLoggingClassAnnotatedComponent
) {
    companion object {
        private const val COMMON_LOG_MESSAGE = "executed in"
    }

    @Test
    fun `basic logging test`(capture: CapturedOutput) {
        testLoggingComponent.baseMethod()
        assertLog(capture)
    }

    @Test
    fun `logs method execution when time exceeds afterMillis`(capture: CapturedOutput) {
        testLoggingComponent.slowMethodLogAfterMillis("World")

        assertLog(capture)
        assertThat(capture.out).contains("World")
    }

    @Test
    fun `does not log when below afterMillis`(capture: CapturedOutput) {
        testLoggingComponent.fastMethodLogAfterMillis(21)
        assertNotLog(capture)
    }

    @Test
    fun `does not log when time exceeds beforeMillis`(capture: CapturedOutput) {
        testLoggingComponent.slowMethodBeforeMillis()
        assertNotLog(capture)
    }

    @Test
    fun `class annotation`(capture: CapturedOutput) {
        testLoggingClassAnnotatedComponent.baseMethod()
        assertLog(capture)
    }

    @Test
    fun `logs if below beforeMillis`(capture: CapturedOutput) {
        testLoggingComponent.fastMethodBeforeMillis()
        assertLog(capture)
    }

    private fun assertLog(capture: CapturedOutput) {
        assertThat(capture.out).contains(COMMON_LOG_MESSAGE)
    }

    private fun assertNotLog(capture: CapturedOutput) {
        assertThat(capture.out).doesNotContain(COMMON_LOG_MESSAGE)
    }

    // TODO Add test for is within beforeMillis
    // Combine before and after millis
}