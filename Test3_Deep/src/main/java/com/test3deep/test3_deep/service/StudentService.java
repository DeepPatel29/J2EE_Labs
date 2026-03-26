package com.test3deep.test3_deep.service;


import com.test3deep.test3_deep.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();

    void addStudent(Student student);
}
