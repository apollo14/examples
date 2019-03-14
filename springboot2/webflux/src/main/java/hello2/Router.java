package hello2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;


import hello2.h1.H1Handler;
import hello2.h2.H2Handler;
import hello2.v.VHandler;

@Configuration
public class Router {
	@Autowired
	private VHandler vHandler;
	@Autowired
	private H1Handler h1;
	@Autowired
	private H2Handler h2;

	@Bean
	public RouterFunction<ServerResponse> route() {
		RouterFunction<ServerResponse> response = vHandler.route();
		
		response = h1.route(response);		
		response = h2.route(response);
		
		return response;
	}	
}