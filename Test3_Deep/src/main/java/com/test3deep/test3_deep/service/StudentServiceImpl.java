package com.test3deep.test3_deep.service;

import com.test3deep.test3_deep.entity.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    private Map<Integer, Student> studentMap = new HashMap<>();

    // Prepopulate with 3 default records
    public StudentServiceImpl() {
        studentMap.put(1, new Student(1, "John Smith", 20, "Male",
                "john@email.com", "Toronto", "2004-01-15"));
        studentMap.put(2, new Student(2, "Sara Jones", 22, "Female",
                "sara@email.com", "Mississauga", "2002-05-20"));
        studentMap.put(3, new Student(3, "Mike Brown", 21, "Male",
                "mike@email.com", "Brampton", "2003-09-10"));
    }

    @Override
    public List<Student> getAllStudents() {
        return new ArrayList<>(studentMap.values());
    }

    @Override
    public void addStudent(Student student) {
        studentMap.put(student.getId(), student);
    }
}
