package spring.verticle.web;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.springframework.stereotype.Component;
import spring.verticle.service.StudentVerticle;

@Component
public class ServerVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        super.start();

        Router router = Router.router(vertx);
        router.get("/api/javalab/students")
                .handler(this::getAllArticlesHandler);

        vertx.createHttpServer()
                .requestHandler(router)
                .listen(7070);
    }

    private void getAllArticlesHandler(RoutingContext routingContext) {
        vertx.eventBus()
                .<String>request(StudentVerticle.STUDENT_GET_ALL, "", result -> {
                    if (result.succeeded()) {
                        routingContext.response()
                                .putHeader("content-type", "application/json")
                                .setStatusCode(200)
                                .end(result.result()
                                        .body());
                    } else {
                        routingContext.response()
                                .setStatusCode(500)
                                .end();
                    }                });
    }
}
