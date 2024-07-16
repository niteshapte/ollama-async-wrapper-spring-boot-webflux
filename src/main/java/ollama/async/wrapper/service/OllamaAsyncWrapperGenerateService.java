package ollama.async.wrapper.service;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service interface for handling asynchronous Ollama generation operations.
 * <p>
 * This interface defines methods for performing generation operations asynchronously,
 * supporting both single request and streaming scenarios.
 */
@Service
public interface OllamaAsyncWrapperGenerateService {

	/**
	* Initiates an asynchronous generation request.
	*
	* @param payload The payload containing generation request details.
	* @return A Mono emitting a String representing the generation response.
	*/
	public Mono<String> ollamaGenerate(String payload);

	/**
	* Initiates an asynchronous streaming generation request.
	*
	* @param payload The payload containing generation request details.
	* @return A Flux emitting String events representing the streaming generation responses.
	*/
	public Flux<String> ollamaGenerateStream(String payload);
}
