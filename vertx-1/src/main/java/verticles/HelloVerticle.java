package verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.Message;

import java.util.Locale;


public class HelloVerticle extends AbstractVerticle {

    @Override
    public void start() {
        vertx.eventBus().consumer("greetingsPubSub", msg -> vertx.executeBlocking(handler -> hello(msg)));
        vertx.eventBus().consumer("greetingsPubSub", msg -> vertx.executeBlocking(handler -> hello(msg)));
        vertx.eventBus().publish("greetingsPubSub", "Hello, javlab");

//        vertx.eventBus().consumer("greetingsRequestResponse", message -> {
//            String response = helloWithReturn(message);
//            message.reply(response);
//        });
//        vertx.eventBus().request("greetingsRequestResponse",
//                "hello, java lab",
//                r -> System.out.println("Receiving reply: " + r.result().body()));
    }

    private void hello(Message<Object> data) {
        System.out.println(System.currentTimeMillis() + " started");
        for (long i = 0; i < (long) 10 * Integer.MAX_VALUE; i++) {
        }
        String message = (String) data.body();
        System.out.println(message);
        System.out.println(System.currentTimeMillis() + " ended");
    }

    private String helloWithReturn(Message<Object> data) {
        return  data.body().toString().toUpperCase(Locale.ROOT);
    }
}
