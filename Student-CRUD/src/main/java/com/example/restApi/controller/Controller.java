package com.example.restApi.controller;

import com.example.restApi.service.StudentService;
import com.example.restApi.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/")
    public String home(){
        return "Welcome to Our CRUD Student Rest API";
    }

    @RequestMapping(value = "/student")
    public List<Student> getStudents(){
        return this.studentService.getStudents();
    }

    @RequestMapping(value = "/student/{id}")
    public Student getStudent(@PathVariable String id){
        return this.studentService.getStudent(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = ("/student"))
    public Student addStudent(@RequestBody Student student){
        return this.studentService.addStudent(student);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/student/{id}")
    public void updateStudent(@RequestBody Student student, @PathVariable String id){
        studentService.updateStudent(id, student);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/student/{id}")
    public void deleteStudent(@PathVariable String id){
        studentService.deleteStudent(id);
    }
}
