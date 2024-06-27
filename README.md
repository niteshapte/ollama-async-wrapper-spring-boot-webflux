# Ollama Async Wrapper using Spring Boot WebFlux
This project provides a Spring Boot WebFlux application that acts as an asynchronous wrapper around a local LLM service called Ollama. The wrapper adds rate limiting, delay, and circuit breaker functionalities to ensure stable and reliable interactions with Ollama.

## Features

- Asynchronous processing with WebFlux
- Rate limiting to control the number of requests
- Circuit breaker to prevent system overload
- Configurable connection and read timeouts

## Requirements

- Java 11 or higher
- Maven or Gradle
- Ollama service running locally on port 11434
