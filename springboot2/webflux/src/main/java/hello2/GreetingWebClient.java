package hello2;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

public class GreetingWebClient {
	private WebClient client = WebClient.create("http://localhost:8080");

	private Mono<ClientResponse> vResult = client.get()
			.uri("/version")
			.accept(MediaType.TEXT_PLAIN)
			.exchange();

	private Mono<ClientResponse> result = client.get()
			.uri("/hello")
			.accept(MediaType.TEXT_PLAIN)
			.exchange();

	private Mono<ClientResponse> h2result = client.get()
			.uri("/hello2")
			.accept(MediaType.TEXT_PLAIN)
			.exchange();

	public String getVersion() {
		return ">> result = " + vResult.flatMap(res -> res.bodyToMono(String.class)).block();
	}

	public String getResult() {
		return ">> result = " + result.flatMap(res -> res.bodyToMono(String.class)).block();
	}
	
	public String getResultH2() {
		return ">> result = " + h2result.flatMap(res -> res.bodyToMono(String.class)).block();
	}
}