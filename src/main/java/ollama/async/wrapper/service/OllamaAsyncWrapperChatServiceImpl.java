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
import reactor.core.scheduler.Schedulers;
import reactor.util.retry.Retry;

@RequiredArgsConstructor
@Service
public class OllamaAsyncWrapperChatServiceImpl implements OllamaAsyncWrapperChatService {
	
	private final WebClient webClient;
	private final RateLimiter rateLimiter;
        private final CircuitBreaker circuitBreaker;

	public Mono<String> ollamaChat(String payload) {
		return Mono.just(payload)
                .transformDeferred(RateLimiterOperator.of(rateLimiter))
                .transformDeferred(CircuitBreakerOperator.of(circuitBreaker))
                .onErrorResume(RequestNotPermitted.class, e -> Mono.error(new RuntimeException("Rate limit exceeded, please try again later")))
                .onErrorResume(Throwable.class, e -> Mono.error(new RuntimeException("Service is currently unavailable, please try again later")))
                .delayElement(Duration.ofSeconds(1)) 
                .flatMap(request -> webClient.post()
                        .uri("/api/chat")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(request)
                        .retrieve()
                        .bodyToMono(String.class)
                        .timeout(Duration.ofSeconds(60))
                        .retry(3)
                        .doOnError(Throwable::printStackTrace))
				.subscribeOn(Schedulers.boundedElastic());
	}
	
	public Flux<String> ollamaChatStream(String payload) {
		return Mono.just(payload)
                .transformDeferred(RateLimiterOperator.of(rateLimiter))
                .transformDeferred(CircuitBreakerOperator.of(circuitBreaker))
                .onErrorResume(RequestNotPermitted.class, e -> Mono.error(new RuntimeException("Rate limit exceeded, please try again later")))
                .onErrorResume(Throwable.class, e -> Mono.error(new RuntimeException("Service is currently unavailable, please try again later")))
                .delayElement(Duration.ofSeconds(1)) 
                .flatMapMany(request -> webClient.post()
                        .uri("/api/chat")
                        .accept(MediaType.TEXT_PLAIN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(request)
                        .retrieve()
                        .bodyToFlux(String.class)
                        .timeout(Duration.ofSeconds(60))
                        .retryWhen(Retry.backoff(3, Duration.ofSeconds(1)).maxBackoff(Duration.ofSeconds(5)))
                        .doOnError(Throwable::printStackTrace))
                .subscribeOn(Schedulers.boundedElastic());
	}
}
