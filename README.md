# Logging Aspect

A simple Kotlin-based library that uses AspectJ to log the execution time of annotated methods.

## Usage

Annotate any method with `@LogExecutionTime` to log its runtime:

```kotlin
@Logging
fun doSomething() {
    // your code
}

```

For a detailed description of the available properties of the `@Logging `annotation,
check out the class' javadoc