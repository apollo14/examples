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
import reactor.core.publisher.Mono;

@Component
public class GreetingHandler3 {
	
	@Autowired
	private PersonService personService;
	
	public Mono<ServerResponse> hello3(ServerRequest request){
		return ServerResponse.ok().body(personService.getList(), Person.class);
	}
	
	//@Configuration
	public class Router {
		@Bean
		public RouterFunction<ServerResponse> route3(GreetingHandler3 greetingHandler) {

			return RouterFunctions
				.route(GET("/hello3").and(accept(APPLICATION_JSON)), greetingHandler::hello3)
				;
		}
	}
}