package ollama.async.wrapper.service;

import java.time.Duration;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import io.github.resilience4j.reactor.ratelimiter.operator.RateLimiterOperator;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class OllamaAsyncWrapperGenerateServiceImpl implements OllamaAsyncWrapperGenerateService {

	private final WebClient webClient;
	private final RateLimiter rateLimiter;
    private final CircuitBreaker circuitBreaker;
    
	public Mono<String> ollamaGenerate(String payload) {
		return Mono.just(payload)
                .transformDeferred(RateLimiterOperator.of(rateLimiter))
                .transformDeferred(CircuitBreakerOperator.of(circuitBreaker))
                .onErrorResume(RequestNotPermitted.class, e -> Mono.error(new RuntimeException("Rate limit exceeded, please try again later")))
                .onErrorResume(Throwable.class, e -> Mono.error(new RuntimeException("Service is currently unavailable, please try again later")))
                .delayElement(Duration.ofSeconds(1)) 
                .flatMap(request -> webClient.post()
                        .uri("/api/generate")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(request)
                        .retrieve()
                        .bodyToMono(String.class));
	}
	
	public Flux<String> ollamaGenerateStream(String payload) {
		return Mono.just(payload)
                .transformDeferred(RateLimiterOperator.of(rateLimiter))
                .transformDeferred(CircuitBreakerOperator.of(circuitBreaker))
                .onErrorResume(RequestNotPermitted.class, e -> Mono.error(new RuntimeException("Rate limit exceeded, please try again later")))
                .onErrorResume(Throwable.class, e -> Mono.error(new RuntimeException("Service is currently unavailable, please try again later")))
                .delayElement(Duration.ofSeconds(1)) 
                .flatMapMany(request -> webClient.post()
                        .uri("/api/generate")
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(request)
                        .retrieve()
                        .bodyToFlux(String.class));
	}
}