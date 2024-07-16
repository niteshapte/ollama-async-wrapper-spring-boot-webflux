package ollama.async.wrapper.config;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;

/**
 * Configuration class for customizing a CircuitBreaker instance using Resilience4j.
 * <p>
 * This configuration defines a CircuitBreaker with custom parameters including failure rate threshold,
 * wait duration in open state, permitted number of calls in half-open state, and sliding window size.
 * The CircuitBreaker instance is configured as a Spring bean with the name "ollamaCircuitBreaker".
 */
@Configuration
public class CircuitBreakerCustomConfig {

	/**
     	* Defines a custom CircuitBreaker bean with specified configuration parameters.
     	*
     	* @return CircuitBreaker instance configured with custom parameters.
     	*/
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
