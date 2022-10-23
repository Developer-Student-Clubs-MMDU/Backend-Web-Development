package com.example.collegeApi.services;
import com.example.collegeApi.entities.College;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CollegeServiceImp implements CollegeService{

    List<College> list;

    public CollegeServiceImp(){
        list = new ArrayList<>();
        list.add(new College(1,"MMDU","B.tech","450000","4 years","AC","100000"));
        list.add(new College(2,"MMIT","M.tech","550000","2 years","Non-AC","70000"));
        list.add(new College(3,"MMEC","MCA","400000","2 years","AC","100000"));
    }

    @Override
    public List<College> getAllColleges() {
        return list;
    }

    @Override
    public College getCollege(long collegeId) {
        College c = null;
        for(College college: list){
            if(college.getId()==collegeId){
                c = college;
                break;
            }
        }
        return c;
    }


}
