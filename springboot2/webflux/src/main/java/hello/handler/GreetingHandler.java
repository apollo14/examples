package hello.handler;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import hello.model.Person;
import hello.service.PersonService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class GreetingHandler {
	
	public Mono<ServerResponse> hello(ServerRequest request) {
		return ServerResponse.ok().body(BodyInserters.fromObject("Hello, Spring!"));
	}	
	
	public Mono<ServerResponse> hello2(ServerRequest request){
		return ServerResponse.ok().body(Flux.just(new Person("a", "A"), new Person("b", "B")), Person.class);
	}	
	
}