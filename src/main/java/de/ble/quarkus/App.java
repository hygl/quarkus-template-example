package de.ble.quarkus;

import static de.ble.quarkus.Render.page;

import java.util.concurrent.CompletionStage;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.Vertx;

@Path("/hello")
public class App {

    @Inject
    Vertx vertx;

   
    @GET
    @Produces(MediaType.TEXT_HTML)
    public CompletionStage<String> hello() {
        JsonObject data = new JsonObject().put("name", "Vert.x Web").put("path", "/hello");
        return page(vertx,data,"index");
    }
}