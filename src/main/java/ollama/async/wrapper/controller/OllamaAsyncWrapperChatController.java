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

@RequiredArgsConstructor
@RestController
@RequestMapping("ollama")
public class OllamaAsyncWrapperChatController {
	
	private final OllamaAsyncWrapperChatService chatService;

	@PostMapping(value = "/chat/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<String> chat(@RequestBody String payload) {
		return chatService.ollamaChat(payload);
	}
	
	@PostMapping(value = "/chat/stream", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<String> chatStream(@RequestBody String payload) {
		return chatService.ollamaChatStream(payload);
	}
}
