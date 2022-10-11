package com.example.StudentApi.controller;

import com.example.StudentApi.entity.Student;
import com.example.StudentApi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {
    @RequestMapping(value = "/")
    public String home(){
        return "To get the list of all students search in the url (localhost:8080/student) || " +
                "You can also check a list of single student by searching url like (localhost:8080/student/1)";
    }
    @Autowired
    private StudentService studentService;

    // Get the all student detail
    @RequestMapping(value = "/student")
    public List<Student> getStudents(){
        return this.studentService.getStudents();
    }

    // Get the detail of single student
    @RequestMapping(value = "/student/{id}")
    public Student getStudent(@PathVariable String id){
        return this.studentService.getStudent(id);
    }
}
