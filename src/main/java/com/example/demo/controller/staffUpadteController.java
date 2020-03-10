package com.example.demo.controller;

import com.example.demo.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class staffUpadteController {
    @Autowired
    StaffService staffService;

    @RequestMapping(value = "/addStaff")
    @ResponseBody
    public String addStaff(Model model, @RequestBody Map<String, String> map){
        System.out.println(map.get("staffName"));
        model.addAttribute("allStaff",staffService.selectAll());
        return "SUCCESS";
    }
}
