package verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;


public class HelloVerticle extends AbstractVerticle {

    @Override
    public void start() {
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);
        router
                .get("/api/javalab")
                .respond(x -> Future.succeededFuture(new JsonObject().put("hello", "javalab")));

        router
                .post("/api/javalab/:name")
                .respond(x -> {
                    String name = x.pathParam("name");
                    return Future.succeededFuture(new JsonObject().put("hello", name));
                });


        server.requestHandler(router).listen(8000);
    }

}
