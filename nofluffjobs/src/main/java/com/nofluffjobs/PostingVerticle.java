package com.nofluffjobs;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpStatusClass;
import io.vertx.core.Future;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpMethod;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.buffer.Buffer;
import io.vertx.rxjava.core.eventbus.Message;
import io.vertx.rxjava.core.file.FileSystem;
import io.vertx.rxjava.core.http.HttpServer;
import io.vertx.rxjava.core.http.HttpServerResponse;
import io.vertx.rxjava.ext.web.Route;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.RoutingContext;
import io.vertx.rxjava.ext.web.handler.BodyHandler;
import rx.Observable;
import rx.Single;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class PostingVerticle extends AbstractVerticle {

    // TODO: create simple default logger (https://vertx.io/docs/vertx-core/java/#_logging)
	final Logger log = LoggerFactory.getLogger(PostingVerticle.class);

    private static final String DB_LOCATION = "nfj_skeleton_db.json";

    private FileSystem fs;

    private List<Posting> DEFAULT_POSTINGS = Arrays.asList(
            new Posting("Java Developer", "No Fluff Jobs", "Remote", "Plac Kaszubski 8/503",
                    "01-001", 10000, 12000),
            new Posting("Javascript Developer", "No Fluff Jobs", "Remote", "Plac Kaszubski 8/503",
                    "01-001", 10000, 12000)
    );

    @Override
    public void start(Future<Void> startFuture) {
        fs = vertx.fileSystem();

        /* TODO
         * Create Router and expose endpoints (https://vertx.io/docs/vertx-web/java/#_basic_vert_x_web_concepts)
         * HINT: you can use methods httpGetPostings and httpPostPosting as handlers
         * HINT: you can use BodyHandler in router configuration to use RoutingContext#getBodyAsString in httpPostPosting method
         */
        Router router = Router.router(vertx);

        router.route().handler(BodyHandler.create());
        Route route = router.route(HttpMethod.GET, "/postings");
        route.handler(routingContext -> httpGetPostings(routingContext));
        
        route = router.route(HttpMethod.POST, "/postings");
        route.handler(routingContext -> httpPostPosting(routingContext));
        
        HttpServer server = vertx.createHttpServer();
        server.requestHandler(router).listen(8080);

        // HINT: this is used to provide Postings list to CachingVerticle
        vertx.eventBus().consumer("posting::get", this::busGetPostings);

        // HINT: You can leave the initialization below to have something in DB when starting the app for the first time
        fs.rxExists(DB_LOCATION)
                .toObservable()
                .filter(exists -> !exists)
                .flatMapSingle(exists -> fs.rxCreateFile(DB_LOCATION))
                .flatMapSingle(ignore -> fsWritePostings(DEFAULT_POSTINGS))
                .doOnNext(ignore -> log.info("Initialized DB in PostingVerticle"))
                .doOnCompleted(() -> log.info("Started PostingVerticle"))
                .subscribe(ignore -> startFuture.complete());        
        
        
    }

    private void httpGetPostings(RoutingContext context) {
        // TODO:
        // 1. Read postings from DB
        // 2. Serialize to JSON
        // 3. Reply to HTTP request
    	
    	log.info("httpGetPostings");
    	HttpServerResponse response = context.response();
    	response.putHeader(HttpHeaders.CONTENT_TYPE, "application/json");
    	fsReadPostings().subscribe(postingsList -> {
    		log.info("httpGetPostings: postingsList:" + postingsList);
    		response.setStatusCode(HttpResponseStatus.OK.code()).end(new Gson().toJson(postingsList));    		
    	}, throwable -> {
    		response.setStatusCode(HttpResponseStatus.INTERNAL_SERVER_ERROR.code()).end(throwable.getMessage());
    	});
    }

    private void httpPostPosting(RoutingContext context) {
        // TODO:
        // 1. Read string content from http request and deserialize it to Posting
        // 2. Read postings from DB
        // 3. Add deserialized Posting to the list of Postings read from DB
        // 4. Write postings to DB
        // 5. reply to HTTP request with the content that was saved to DB
    	
    	
    	log.info("httpPostPosting: ");
    	
    		HttpServerResponse response = context.response();
    		response.putHeader(HttpHeaders.CONTENT_TYPE, "application/json");
    		
    		Posting posting = new Gson().fromJson(context.getBody().toString(), Posting.class);    		
    		fsReadPostings()
    		.toObservable()
    		.flatMapSingle(m -> {
    			List<Posting> l = new ArrayList<Posting>(m);
    			l.add(posting);
    			return Single.just(l);
    		})
    		.flatMapSingle(mapper -> fsWritePostings(mapper))
    		.subscribe(postingsList -> {    			
    			response.setStatusCode(HttpResponseStatus.OK.code()).end(new Gson().toJson(posting));
    		}, throwable -> {
    			response.setStatusCode(HttpResponseStatus.INTERNAL_SERVER_ERROR.code()).end(throwable.getMessage());
    		});
    }

    private void busGetPostings(Message<Void> message) {
        // TODO:
        // 1. Read postings from DB
        // 2. Serialize to JSON
        // 2a. Randomly add some garbage to serialized JSON to trigger error in CachingVerticle (exponential backoff kicks in)
        // 3. Reply to EventBus Message
    }

    private Single<List<Posting>> fsReadPostings() {
        // TODO:
        // 1. Use FileSystem.rxReadFile to read DB content
        // 2. Deserialize content to a list of Postings
    	
    	log.info("fsReadPostings");
    	
    	Type type = new TypeToken<List<Posting>>() {}.getType();
    	
    	Single<List<Posting>> result = fs.rxReadFile(DB_LOCATION)
    		.map(buffer -> buffer.toString())
    		.map(content ->	new Gson().fromJson(content, type));
    	
    	result.subscribe(posting -> {log.info("fsReadPostings: " + posting);});
    		
    	return result;
    }

    private Single<List<Posting>> fsWritePostings(List<Posting> postings) {
        // TODO:
        // 1. Serialize List<Posting> to JSON
        // 2. Use FileSystem.rxWriteFile to write DB content
        // 3. Return List<Posting> that was written
    	
    	Single.just(postings)
    	.flatMap(content -> {
    		log.info("content=" + content);
    		return Single.just( new Gson().toJson(content));
    		})
    	.flatMap(buffer -> w(buffer))
    	.subscribe()
    	;
//    	/fs.rxWriteFile(DB_LOCATION, Buffer.buffer(mapper))
    	
    	//fs.rxWriteFile(DB_LOCATION, Buffer.buffer(new Gson().toJson(postings)))
//    	.subscribe(postingsList ->{
  //  		log.info("fsWritePostings: " + postings);
    //	});
    	return Single.just(postings);
    }

    private Single<String> w(String s){
    	log.info("w--" + s);
    	fs.rxWriteFile(DB_LOCATION, Buffer.buffer(s))
    	.subscribe();
    	return Single.just(s);
    }
}
