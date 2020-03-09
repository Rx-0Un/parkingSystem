package com.example.demo.controller;

import com.example.demo.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class staffController {

    StaffService staffService;

    @RequestMapping(value = "/index-staff")
    public String getAll(Model model) {
        model.addAttribute("allStaff",staffService.selectAll());
        return "index-staff";
    }

    @RequestMapping(value = "/selectStaffByCondition")
    public String selectStaffByCondition(Model model, @RequestParam(name = "checkbox1") String[] checkbox1){
        System.out.println(checkbox1);
        return "index-staff";
    }

    @Autowired
    public void setStaffService(StaffService staffService) {
        this.staffService = staffService;
    }
}
