package ollama.async.wrapper.config;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;

/**
 * Configuration class for customizing a Rate Limiter instance using Resilience4j.
 * <p>
 * This configuration defines a Rate Limiter with custom parameters including limit refresh period,
 * limit for period, and timeout duration. The Rate Limiter instance is configured as a Spring bean
 * with the name "ollamaRateLimiter".
 */
@Configuration
public class RateLimiterCustomConfig {

	/**
     	* Defines a custom Rate Limiter bean with specified configuration parameters.
        * The configuration parameter can be made dynamic via properties or yaml file.
     	*
     	* @return RateLimiter instance configured with custom parameters.
     	*/
	@Bean
	RateLimiter rateLimiter() {
		RateLimiterConfig rateLimiterConfig = RateLimiterConfig.custom()
                .limitRefreshPeriod(Duration.ofSeconds(1))
                .limitForPeriod(5)
                .timeoutDuration(Duration.ofMillis(500))
                .build();
        return RateLimiter.of("ollamaRateLimiter", rateLimiterConfig);
	}
}
