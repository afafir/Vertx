import io.vertx.core.Vertx;
import verticles.HelloVerticle;
import verticles.HelloVerticle2;

public class Starter {

    public static void main(String[] args) throws InterruptedException {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new HelloVerticle2());
        vertx.deployVerticle(new HelloVerticle());
    }
}
