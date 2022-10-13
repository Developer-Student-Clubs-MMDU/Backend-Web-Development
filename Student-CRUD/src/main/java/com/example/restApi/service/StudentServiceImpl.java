package com.example.restApi.service;

import com.example.restApi.student.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    // Creating the list of students
    List<Student> list;

    public StudentServiceImpl(){
        list = new ArrayList<>();
        list.add(new Student("1","ujjwal","kumar","8787878787","uk47@gmail.com"));
        list.add(new Student("2","john","wick","8787878686","john@gmail.com"));
    }

    // Getting the list of all Students
    @Override
    public List<Student> getStudents() {
        return list;
    }

    // READ
    // Getting the information of single Student
    @Override
    public Student getStudent(String id) {
        Student student = getStudents().stream()
                .filter(t->id.equals(t.getId()))
                .findFirst()
                .orElse(null);
        return student;
    }

    // CREATE
    // Adding new Student to the list
    @Override
    public Student addStudent(Student student) {
        list.add(student);
        return student;
    }

    // UPDATE
    // Updating the Student information by its ID
    @Override
    public void updateStudent(String id, Student student) {
        for (int i =0; i<getStudents().size(); i++){
            Student s = getStudents().get(i);
            if (s.getId().equals(id)){
                getStudents().set(i, student);
            }
        }
    }

    // DELETE
    // Deleting the Student by its ID
    @Override
    public void deleteStudent(String id) {
        getStudents().removeIf(t->t.getId().equals(id));
    }
}
