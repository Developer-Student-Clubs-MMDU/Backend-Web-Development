package com.example.collegeApi.services;

import com.example.collegeApi.entities.College;

import java.util.List;

public interface CollegeService {
    List<College> getAllColleges();

    College getCollege(long collegeId);


}
