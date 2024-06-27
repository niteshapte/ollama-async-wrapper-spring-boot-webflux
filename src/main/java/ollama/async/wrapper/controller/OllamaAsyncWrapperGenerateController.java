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

@RequiredArgsConstructor
@RestController
@RequestMapping("ollama")
public class OllamaAsyncWrapperGenerateController {
	
	private final OllamaAsyncWrapperGenerateService generateService;
	
	@PostMapping(value = "/generate/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<String> generate(@RequestBody String payload) {
		return generateService.ollamaGenerate(payload);
	}
	
	@PostMapping(value = "/generate/stream", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<String> generateStream(@RequestBody String payload) {
		return generateService.ollamaGenerateStream(payload);
	}
}