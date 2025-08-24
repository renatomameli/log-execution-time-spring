package com.mameli

import com.mameli.component.TestLoggingComponent
import com.mameli.config.LoggingAutoConfiguration
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.system.CapturedOutput
import org.springframework.boot.test.system.OutputCaptureExtension

@SpringBootTest(classes = [LoggingAutoConfiguration::class, TestLoggingComponent::class])
@ExtendWith(OutputCaptureExtension::class)
class LoggingTest(@Autowired private val testLoggingComponent: TestLoggingComponent) {

    @Test
    fun `basic logging test`(capture: CapturedOutput) {
        testLoggingComponent.baseMethod()

        assertThat(capture.out).contains("executed in")
    }

    @Test
    fun `logs method execution when time exceeds afterMillis`(capture: CapturedOutput) {
        testLoggingComponent.slowMethodLogAfterMillis("World")

        assertThat(capture.out).contains("executed in")
        assertThat(capture.out).contains("slowMethod")
        assertThat(capture.out).contains("World")
    }

    @Test
    fun `does not log when below afterMillis`(capture: CapturedOutput) {
        testLoggingComponent.fastMethodLogAfterMillis(21)

        assertThat(capture.out).doesNotContain("fastMethod")
    }

    @Test
    fun `does not log when time exceeds beforeMillis`(capture: CapturedOutput) {
        testLoggingComponent.slowMethodBeforeMillis()

        assertThat(capture.out).doesNotContain("slowMethod")
    }

    // TODO Add test for is within beforeMillis
    // Combine before and after millis
}