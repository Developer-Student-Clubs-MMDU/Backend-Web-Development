package com.example.StudentApi.service;

import com.example.StudentApi.entity.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    // creating list
    List<Student> list;

    // adding the student value to the list
    public StudentServiceImpl(){
        list = new ArrayList<>();
        list.add(new Student("1","ujjwal","kumar","123456789","uk47kumar@gmail.com"));
        list.add(new Student("2","john","wick","987654321","johnwick@gmail.com"));
    }
    // method to get the student list and send to the controller class
    @Override
    public List<Student> getStudents() {
        return list;
    }

    @Override
    public Student getStudent(String id) {
        Student student = getStudents().stream()
                .filter(t->id.equals(t.getId()))
                .findFirst()
                .orElse(null);
        return student;
    }
}
