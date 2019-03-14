package build.version;

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


	public String getVersion() {
		return ">> result = " + vResult.flatMap(res -> res.bodyToMono(String.class)).block();
	}
}