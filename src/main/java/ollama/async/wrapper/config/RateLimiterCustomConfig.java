package ollama.async.wrapper.config;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;

@Configuration
public class RateLimiterCustomConfig {

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
