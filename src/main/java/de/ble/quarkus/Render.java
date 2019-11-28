package de.ble.quarkus;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.web.templ.pebble.PebbleTemplateEngine;

/**
 * Render
 */
public class Render {

    public static CompletionStage<String> page(Vertx vertx, JsonObject data, String template) {
        final PebbleTemplateEngine engine = PebbleTemplateEngine.create(vertx);

        final CompletableFuture<String> future = new CompletableFuture<>();
        // and now delegate to the engine to render it.
        engine.render(data, "templates/" + template + ".html", res -> {
            if (res.succeeded()) {
                future.complete(res.result().toString());
            } else {
                future.complete(res.cause().toString());
            }
        });
        return future;

    }

}