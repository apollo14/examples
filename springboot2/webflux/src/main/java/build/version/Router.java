package build.version;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import build.version.VHandler;
import hello2.h1.H1Handler;
import hello2.h2.H2Handler;

@Configuration
public class Router {
	@Autowired
	private VHandler vHandler;

	@Bean
	public RouterFunction<ServerResponse> route() {
		RouterFunction<ServerResponse> response = vHandler.route();
		
		return response;
	}	
}