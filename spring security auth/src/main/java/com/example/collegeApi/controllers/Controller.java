package com.example.collegeApi.controllers;

import com.example.collegeApi.entities.College;
import com.example.collegeApi.services.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;
@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    CollegeService collegeService;

    @RequestMapping(value = "/colleges")
    public String getAllColleges(Model model){
        List<College> colleges = collegeService.getAllColleges();

        model.addAttribute("colleges", colleges);

        return "colleges";
    }


    @RequestMapping(value = ("/colleges/{collegeId}"))
    public String getCollege(Model model, @PathVariable String collegeId){
        College college = collegeService.getCollege(Long.parseLong(collegeId));
        model.addAttribute("colleges", college);
        return "colleges";
    }

}
