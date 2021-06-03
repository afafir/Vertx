import io.vertx.core.AbstractVerticle;

public class ClusteredConsumer extends AbstractVerticle {

    @Override
    public void start() {
        vertx.eventBus().consumer("greetingsClustered", System.out::println);
    }
}
