package ollama.async.wrapper.config;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

/**
 * Configuration class for customizing a WebClient instance using Reactor Netty.
 * <p>
 * This configuration sets up a WebClient with a custom HttpClient that includes connection
 * and response timeouts, read and write timeouts, and wiretap logging for debugging purposes.
 * The WebClient instance is configured with a base URL pointing to "http://localhost:11434".
 */
@Configuration
public class WebClientCustomConfig {

	/**
     	* Defines a custom WebClient bean with a Reactor Netty HttpClient configuration.
        * The configuration parameter can be made dynamic via properties or yaml file.
     	*
     	* @return WebClient instance configured with a custom Reactor Netty HttpClient.
     	*/
	@Bean
	WebClient webClient() {
		HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 60000)
                .responseTimeout(Duration.ofSeconds(60))
                .doOnConnected(conn -> conn
                        .addHandlerLast(new ReadTimeoutHandler(60)) 
                        .addHandlerLast(new WriteTimeoutHandler(60)))
                .wiretap("reactor.netty.http.client.HttpClient", LogLevel.INFO, AdvancedByteBufFormat.TEXTUAL);
		
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl("http://localhost:11434")
                .build();
	}
}
