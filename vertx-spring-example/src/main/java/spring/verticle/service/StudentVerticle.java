package spring.verticle.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.service.StudentService;

@Component
public class StudentVerticle extends AbstractVerticle {

    public static final String STUDENT_GET_ALL =  "students.get.all";

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private StudentService studentService;

    @Override
    public void start() {
        vertx.eventBus()
                .<String>consumer(STUDENT_GET_ALL)
                .handler(getAllArticleService());
    }

    private Handler<Message<String>> getAllArticleService() {
        return msg -> vertx.<String>executeBlocking(future -> {
            try {
                future.complete(mapper.writeValueAsString(studentService.getAllStudents()));
            } catch (JsonProcessingException e) {
                System.out.println("Failed to serialize result");
                future.fail(e);
            }
        }, result -> {
            if (result.succeeded()) {
                msg.reply(result.result());
            } else {
                msg.reply(result.cause()
                        .toString());
            }
        });
    }



}
