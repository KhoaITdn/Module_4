package org.example.view_thymeleaf.repository;
import org.example.view_thymeleaf.model.Student;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Repository
public class StudentRepository implements org.example.view_thymeleaf.repository.IStudentRepository {
    private static final List<Student> list;
    static {
        list = new ArrayList<>();
        Student s1 = new Student(1, "khoa1", 21, 1, "abc", 10000000, LocalDate.of(2000, 1, 1));
        Student s2 = new Student(2, "khoa2", 21, 1, "abc", 100000000, LocalDate.of(1999, 1, 1));

        list.add(s1);
        list.add(s2);
    }

    @Override
    public List<Student> findAll(){
        return list;
    }


}
