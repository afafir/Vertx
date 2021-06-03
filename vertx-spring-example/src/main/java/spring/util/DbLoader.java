package spring.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import spring.entity.Student;
import spring.repository.StudentRepository;

import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

@Component
public class DbLoader  {
    @Autowired
    private StudentRepository studentRepository;

    @PostConstruct
    public void init() {

        IntStream.range(0, 10)
                .forEach(count -> this.studentRepository.save(new Student(new Random().nextLong(), UUID.randomUUID()
                        .toString())));
    }
}
