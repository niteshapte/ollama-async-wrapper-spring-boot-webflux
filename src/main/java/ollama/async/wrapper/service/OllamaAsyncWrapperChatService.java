package ollama.async.wrapper.service;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service interface for handling asynchronous Ollama chat operations.
 * <p>
 * This interface defines methods for performing chat operations asynchronously,
 * supporting both single request and streaming scenarios.
 */
@Service
public interface OllamaAsyncWrapperChatService {

	/**
	* Initiates an asynchronous chat request.
	*
	* @param payload The payload containing chat request details.
	* @return A Mono emitting a String representing the chat response.
	*/
	public Mono<String> ollamaChat(String payload);

	/**
	* Initiates an asynchronous streaming chat request.
	*
	* @param payload The payload containing chat request details.
	* @return A Flux emitting String events representing the streaming chat responses.
	*/
	public Flux<String> ollamaChatStream(String payload);
}
