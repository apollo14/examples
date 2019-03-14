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

//@Configuration
public class GreetingRouter3 {

	//@Bean
	public RouterFunction<ServerResponse> route3(GreetingHandler3 greetingHandler) {

		return RouterFunctions
			.route(GET("/hello3").and(accept(APPLICATION_JSON)), greetingHandler::hello3)
			;
	}
}