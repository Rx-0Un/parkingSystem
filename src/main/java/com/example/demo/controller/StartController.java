package com.example.demo.controller;

import com.example.demo.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * 此controller用于完成界面跳转
 */
@Controller
public class StartController {
    StaffService staffService;

    @GetMapping("/index")
    public String test() {
        return "authentication-login";
    }

    @RequestMapping(value = "/index-staff")
    public String getAll(Model model) {
        model.addAttribute("allStaff", staffService.selectAll());
        return "index-staff";
    }

    @RequestMapping(value = "/index-duty-statistics")
    public String dutyManage(Model model) {
        model.addAttribute("allStaff", staffService.selectAllIdAndName());
        return "index-duty-statistics";
    }

    @Autowired
    public void setStaffService(StaffService staffService) {
        this.staffService = staffService;
    }
}
