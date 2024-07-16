package ollama.async.wrapper.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * Main entry point for the Ollama Async Wrapper application.
 * <p>
 * This class initializes the Spring Boot application, enabling auto-configuration,
 * component scanning, and Spring WebFlux to support reactive web application features.
 */
@ComponentScan("ollama.async.wrapper*")
@EnableAutoConfiguration
@EnableWebFlux
@SpringBootApplication
public class OllamaAsyncWrapperApplication {

	/**
	* Main method to start the Ollama Async Wrapper application.
	*
	* @param args Command-line arguments passed to the application.
	*/
	public static void main(String[] args) {
		SpringApplication.run(OllamaAsyncWrapperApplication.class, args);
	}
}
