package com.test3deep.test3_deep.controller;

import com.test3deep.test3_deep.entity.Student;
import com.test3deep.test3_deep.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Show all students
    @GetMapping("/students")
    public String getAllStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }

    // Show add student form
    @GetMapping("/students/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "add-student";
    }

    // Save new student
    @PostMapping("/students/save")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.addStudent(student);
        return "redirect:/students";
    }
}
