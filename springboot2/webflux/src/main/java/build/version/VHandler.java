package build.version;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
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
public class VHandler {
	//Configure build info in spring-boot-maven-plugin
	@Autowired
	BuildProperties buildProperties;

	public Mono<ServerResponse> get(ServerRequest request) {
		return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
			.body(BodyInserters.fromObject("version: " + buildProperties.getVersion()));
	}
	
	
	public RouterFunction<ServerResponse> route(){
		return RouterFunctions.route(RequestPredicates.GET("/version").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), this::get);
	}
}