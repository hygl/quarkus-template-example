package de.ble.quarkus;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.web.templ.pebble.PebbleTemplateEngine;

@Path("/hello")
public class App {

    @Inject
    Vertx vertx;

   
    @GET
    @Produces(MediaType.TEXT_HTML)
    public CompletionStage<String> hello() {
        final PebbleTemplateEngine engine = PebbleTemplateEngine.create(vertx);

        CompletableFuture<String> future = new CompletableFuture<>();
        JsonObject data = new JsonObject().put("name", "Vert.x Web").put("path", "/hello");
        // and now delegate to the engine to render it.
        engine.render(data, "templates/index.html", res -> {
            if (res.succeeded()) {
                future.complete(res.result().toString());
            } else {
                future.complete(res.cause().toString());
            }
        });
        return future;
    }
}