package spring.entity;

import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student {

    @Id
    private Long id;
    private String name;

    private Student() {
    }

    @PersistenceConstructor
    public Student(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "name [id=" + id + ", article=" + name + "]";
    }

    public Long getStudentId() {
        return id;
    }

    public void setStudentId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
