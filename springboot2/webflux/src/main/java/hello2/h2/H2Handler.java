package hello2.h2;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
public class H2Handler {

	public Mono<ServerResponse> hello(ServerRequest request) {
		return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
			.body(BodyInserters.fromObject("Hello - 2, Spring!"));
	}
	
	public RouterFunction<ServerResponse> route(RouterFunction<ServerResponse> response){
		return response.andRoute(RequestPredicates.GET("/hello2").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), this::hello);
	}
}