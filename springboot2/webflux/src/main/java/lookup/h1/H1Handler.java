package lookup.h1;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import lookup.Proto;
import reactor.core.publisher.Mono;

@Component
public class H1Handler {
	
	@Autowired
	private Proto proto;
	
	/*@Lookup
	public Proto getProto() {
		return null;
	}*/

	public Mono<ServerResponse> hello(ServerRequest request) {
		//Proto proto = getProto();
		
		return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
			.body(BodyInserters.fromObject("Proto="+ proto + " - " + proto.getCount()));
	}
	
	public RouterFunction<ServerResponse> route(){
		return RouterFunctions.route(RequestPredicates.GET("/hello").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), this::hello);
	}

}