import io.vertx.core.AbstractVerticle;

public class ClusteredSender extends AbstractVerticle {

    @Override
    public void start() {
        vertx.setPeriodic(2000, x -> {
            vertx.eventBus().publish("greetingsClustered", "Its message from first cluster");
        });
    }
}
