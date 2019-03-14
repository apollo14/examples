package com.nofluffjobs;

import io.vertx.rxjava.core.AbstractVerticle;

public class CachingVerticle extends AbstractVerticle {

    // TODO: create simple default logger (https://vertx.io/docs/vertx-core/java/#_logging)

    @Override
    public void start() {
        // HINT: comment setPeriodic and uncomment setTimer to test more easily if exponential backoff works as expected
        // vertx.setTimer(5000, this::updateCache);
        vertx.setPeriodic(120000, this::updateCache);
    }

    private void updateCache(long counter) {
        // TODO add logging when this method is called
        vertx.eventBus().rxSend("posting::get", null)
                .toObservable()
                /* TODO:
                 * 1. Log received string
                 * 2. Deserialize received string to List<Posting>
                 * 3. Add exponential backoff using .retryWhen
                 */
                .subscribe();

    }

}
