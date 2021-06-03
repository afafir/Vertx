import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.Message;
import io.vertx.ext.web.client.WebClient;

import java.util.concurrent.ThreadLocalRandom;

public class HelloVerticle extends AbstractVerticle {
    private String name = "Kazan";
    private String apiKey = "c6b10521e81ef7d989991b2df727b7cf";
    @Override
    public void start() {
        String uri = String.format("/data/2.5/weather?q=%s&appid=%s", name, apiKey);
        WebClient client = WebClient.create(vertx);
        vertx.setPeriodic(10000, x -> {
            client
                    .get(443, "api.openweathermap.org", uri)
                    .ssl(true)
                    .send()
                    .onSuccess(response -> {
                        System.out.println(response.body());
                    })
                    .onFailure(err ->
                            System.out.println(err.getMessage()) );
        });
    }
}
