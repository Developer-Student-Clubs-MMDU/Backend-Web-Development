package com.example.StudentApi.service;

import com.example.StudentApi.entity.Student;

import java.util.List;

public interface StudentService {

    public List<Student> getStudents();

    public Student getStudent(String id);
}
