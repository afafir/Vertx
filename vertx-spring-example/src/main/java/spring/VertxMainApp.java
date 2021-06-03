package spring;

import io.vertx.core.Vertx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import spring.verticle.service.StudentVerticle;
import spring.verticle.web.ServerVerticle;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableJpaRepositories
public class VertxMainApp {
    @Autowired
    private ServerVerticle serverVerticle;

    @Autowired
    private StudentVerticle serviceVerticle;

    public static void main(String[] args) {
        SpringApplication.run(VertxMainApp.class, args);
    }

    @PostConstruct
    public void deployVerticle() {
        final Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(serverVerticle);
        vertx.deployVerticle(serviceVerticle);
    }
}
