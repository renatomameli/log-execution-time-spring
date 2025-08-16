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
    fun `logs method execution when time exceeds threshold`(capture: CapturedOutput) {
        testLoggingComponent.slowMethod("World")

        assertThat(capture.out).contains("executed in")
        assertThat(capture.out).contains("slowMethod")
        assertThat(capture.out).contains("World")
    }

    @Test
    fun `does not log when below threshold`(capture: CapturedOutput) {
        testLoggingComponent.fastMethod(21)

        assertThat(capture.out).doesNotContain("fastMethod")
    }
}