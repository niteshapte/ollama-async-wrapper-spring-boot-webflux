# Ollama Async Wrapper using Spring Boot WebFlux
This project provides a Spring Boot WebFlux application that acts as an asynchronous wrapper around a local LLM service called Ollama. The wrapper adds rate limiting, delay, and circuit breaker functionalities to ensure stable and reliable interactions with Ollama.

##### Blog Post - https://blog.niteshapte.com/2024-07-04-ollama-async-wrapper-using-spring-boot-webflux.htm

## Features

- Asynchronous processing with WebFlux
- Rate limiting to control the number of requests
- Circuit breaker to prevent system overload
- Configurable connection and read timeouts

## Requirements

- Java 11 or higher
- Maven or Gradle
- Ollama service running locally on port 11434

## Endpoints

- /ollama/generate
- /ollama/generate/stream
- /ollama/chat
- /ollama/chat/stream

## Running the project

### Clone the Repository

```bash
git clone https://github.com/niteshapte/ollama-async-wrapper-spring-boot-webflux.git
cd ollama-async-wrapper-spring-boot-webflux
```

### Build the Project

```bash
./mvnw clean install
```

### Run the Application

```bash
./mvnw spring-boot:run
```

### Send a Test Request

```bash
curl -X POST "http://localhost:5151/ollama/generate" -H "Content-Type: application/json" -d '{
  "model": "llama3",
  "prompt": "Hi!"
}'
```
