package ollama.async.wrapper.service;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface OllamaAsyncWrapperChatService {

	public Mono<String> ollamaChat(String payload);
	
	public Flux<String> ollamaChatStream(String payload);
}
