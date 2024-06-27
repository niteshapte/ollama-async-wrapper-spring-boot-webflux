package ollama.async.wrapper.service;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface OllamaAsyncWrapperGenerateService {

	public Mono<String> ollamaGenerate(String payload);
	
	public Flux<String> ollamaGenerateStream(String payload);
}
