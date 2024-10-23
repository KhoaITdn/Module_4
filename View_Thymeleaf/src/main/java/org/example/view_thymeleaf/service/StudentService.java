package org.example.view_thymeleaf.service;

import org.example.view_thymeleaf.model.Student;
import org.example.view_thymeleaf.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements IStudentService {

    @Autowired
    private IStudentRepository iStudentRepository;

    @Override
    public List<Student> findAll() {

        return iStudentRepository.findAll();
    }
}
