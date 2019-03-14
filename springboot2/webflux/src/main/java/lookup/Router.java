package lookup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import lookup.h1.H1Handler;



@Configuration
public class Router {
	@Autowired
	private H1Handler h1;

	@Bean
	public RouterFunction<ServerResponse> route() {
		RouterFunction<ServerResponse> response = h1.route();		
		
		return response;
	}	
}