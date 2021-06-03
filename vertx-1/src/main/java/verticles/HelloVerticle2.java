package verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.MessageConsumer;

public class HelloVerticle2 extends AbstractVerticle {

    @Override
    public void start() {
        vertx.eventBus().consumer("greetingsPubSub", this::hello);
    }

    private void hello(Message<Object> data) {
        System.out.println(System.currentTimeMillis() + " started");
        for (long i = 0; i < (long) 10* Integer.MAX_VALUE ; i++) {
        }
        String message = (String) data.body();
        System.out.println(message);
        System.out.println(System.currentTimeMillis() + " ended");
    }
}
