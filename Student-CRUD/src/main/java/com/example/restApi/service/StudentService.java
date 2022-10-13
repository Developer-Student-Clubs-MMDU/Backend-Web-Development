package com.example.restApi.service;

import com.example.restApi.student.Student;

import java.util.List;

public interface StudentService {

    public List<Student> getStudents();
    public Student getStudent(String id);

    public Student addStudent(Student student);

    public void updateStudent(String id, Student student);

    public void deleteStudent(String id);
}
