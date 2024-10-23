package org.example.view_thymeleaf.repository;

import org.example.view_thymeleaf.model.Student;

import java.util.List;

public interface IStudentRepository {
    List<Student> findAll();
}
