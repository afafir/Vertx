import io.vertx.core.Vertx;
import verticles.HelloVerticle;

public class Starter {

    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(new HelloVerticle());
    }
}
