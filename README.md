# Logging Aspect

A simple Kotlin-based library that uses AspectJ to log the execution time of annotated methods.

## Features

- 📌 Logs execution time of methods
- 📌 Written in Kotlin
- 📌 Easy integration via annotation
- 📦 Local Maven publishing support

## Usage

Annotate any method with `@LogExecutionTime` to log its runtime:

```kotlin
@LogExecutionTime
fun doSomething() {
    // ...
}

```
## Sample Output
`[INFO] Execution time for doSomething: 152ms`
