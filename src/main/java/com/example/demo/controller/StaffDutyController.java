package com.example.demo.controller;

import com.example.demo.service.StaffDutyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/index-chart-statistics")
public class StaffDutyController {
    StaffDutyService staffDutyService;
    @RequestMapping(value = "/selectDetail",method = RequestMethod.GET)
    public String selectDetail(Model model,HttpServletRequest request){
        String staffDutyId = request.getParameter("staffDutyId");
        return "index-chart-statistics-detail";
    }
    @Autowired
    public void setStaffDutyService(StaffDutyService staffDutyService) {
        this.staffDutyService = staffDutyService;
    }
}
