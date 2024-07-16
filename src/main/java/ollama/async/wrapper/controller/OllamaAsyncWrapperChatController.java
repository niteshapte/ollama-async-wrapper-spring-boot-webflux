package ollama.async.wrapper.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ollama.async.wrapper.service.OllamaAsyncWrapperChatService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * REST controller for handling Ollama chat operations asynchronously.
 * <p>
 * Provides endpoints for initiating chat requests and streaming chat responses.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("ollama")
public class OllamaAsyncWrapperChatController {
	
	private final OllamaAsyncWrapperChatService chatService;
	
	/**
     	* Handles POST requests to initiate a chat operation.
     	*
     	* @param payload The JSON payload containing chat request details.
     	* @return A Mono emitting a String representing the chat response.
     	*/
	@PostMapping(value = "/chat/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<String> chat(@RequestBody String payload) {
		return chatService.ollamaChat(payload);
	}

	/**
     	* Handles POST requests to initiate a streaming chat operation.
     	*
     	* @param payload The JSON payload containing chat request details.
     	* @return A Flux emitting String events representing the streamed chat responses.
     	*/
	@PostMapping(value = "/chat/stream", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<String> chatStream(@RequestBody String payload) {
		return chatService.ollamaChatStream(payload);
	}
}
