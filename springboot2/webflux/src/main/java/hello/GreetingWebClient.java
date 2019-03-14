package hello;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import hello.model.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class GreetingWebClient {	
	private WebClient client = WebClient.create("http://localhost:8080");

	private Mono<ClientResponse> result = client.get()
			.uri("/hello")
			.accept(MediaType.APPLICATION_JSON)
			.exchange();

	private Mono<ClientResponse> result3 = client.get()
			.uri("/hello3")
			.accept(MediaType.APPLICATION_JSON)
			.exchange();

	
	public String getResult() {
		return ">> result = " + result.flatMap(res -> res.bodyToMono(String.class)).block();
	}

	public List<Person> getResult3() {
		Flux<Person> m = result3.flatMapMany(res -> res.bodyToFlux(Person.class));
		
		return m.collectList().block();
	}

}