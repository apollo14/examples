package com.nofluffjobs;

import io.vertx.core.Launcher;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

// HINT: start this class as regular Java application
public class Starter {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new PostingVerticle());
        //vertx.deployVerticle(new CachingVerticle());
    }

}
