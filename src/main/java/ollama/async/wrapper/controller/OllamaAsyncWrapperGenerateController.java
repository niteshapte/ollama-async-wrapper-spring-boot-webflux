package ollama.async.wrapper.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import ollama.async.wrapper.service.OllamaAsyncWrapperGenerateService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * REST controller for handling Ollama generation operations asynchronously.
 * <p>
 * Provides endpoints for initiating data generation requests and streaming generated data responses.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("ollama")
public class OllamaAsyncWrapperGenerateController {
	
	private final OllamaAsyncWrapperGenerateService generateService;

	/**
     	* Handles POST requests to initiate a data generation operation.
     	*
     	* @param payload The JSON payload containing data generation request details.
     	* @return A Mono emitting a String representing the generated data response.
     	*/
	@PostMapping(value = "/generate/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<String> generate(@RequestBody String payload) {
		return generateService.ollamaGenerate(payload);
	}

	/**
     	* Handles POST requests to initiate a streaming data generation operation.
     	*
     	* @param payload The JSON payload containing data generation request details.
     	* @return A Flux emitting String events representing the streamed generated data responses.
     	*/
	@PostMapping(value = "/generate/stream", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<String> generateStream(@RequestBody String payload) {
		return generateService.ollamaGenerateStream(payload);
	}
}
