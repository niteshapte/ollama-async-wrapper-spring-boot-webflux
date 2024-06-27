package ollama.async.wrapper.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.config.EnableWebFlux;

@ComponentScan("ollama.async.wrapper*")
@EnableAutoConfiguration
@EnableWebFlux
@SpringBootApplication
public class OllamaAsyncWrapperApplication {

	public static void main(String[] args) {
		SpringApplication.run(OllamaAsyncWrapperApplication.class, args);
	}
}