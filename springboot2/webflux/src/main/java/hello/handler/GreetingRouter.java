package hello.handler;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class GreetingRouter {

	@Bean
	public RouterFunction<ServerResponse> route(GreetingHandler greetingHandler) {

		return RouterFunctions
			.route(GET("/hello").and(accept(APPLICATION_JSON)), greetingHandler::hello)
			.andRoute(GET("/hello2").and(accept(APPLICATION_JSON)), greetingHandler::hello2)
			//.andRoute(GET("/hello3").and(accept(APPLICATION_JSON)), greetingHandler::hello3)
			;
	}
}