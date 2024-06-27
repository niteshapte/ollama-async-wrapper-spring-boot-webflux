package ollama.async.wrapper.config;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;

@Configuration
public class CircuitBreakerCustomConfig {

	@Bean
	CircuitBreaker circuitBreaker() {
		CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .permittedNumberOfCallsInHalfOpenState(2)
                .slidingWindowSize(5)
                .build();
        return CircuitBreaker.of("ollamaCircuitBreaker", circuitBreakerConfig);
	}
}
