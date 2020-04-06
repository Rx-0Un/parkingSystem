package com.example.demo.controller;

import com.example.demo.entity.TbStaffTask;
import com.example.demo.service.StaffService;
import com.example.demo.service.StaffTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
    StaffTaskService staffTaskService;

    @GetMapping("/index")
    public String test() {
//        return "index3";
        return "authentication-login";
    }

    @RequestMapping(value = "/index-staff")
    public String getAll(Model model) {
        model.addAttribute("allStaff", staffService.selectAll());
        return "index-staff";
    }

    @RequestMapping(value = "/index-duty-statistics")
    public String dutyManage(Model model) {
        int pageNum = 10;
        model.addAttribute("allStaff", staffService.selectAllIdAndName());
        List<TbStaffTask> list = staffTaskService.selectAllPage(pageNum, 0);
        //总页面数等于任务记录总数对pageNum取余数
        int totalPage = staffTaskService.selectAll().size() / pageNum;
        model.addAttribute("allTask", list);
        model.addAttribute("nowPage", 1);
        model.addAttribute("totalPage", totalPage);
        return "index-duty-statistics";
    }

    @RequestMapping(value = "/index-manage-parking")
    public String parkingManage(Model model){

        return "index-manage-parking";
    }

    @Autowired
    public void setStaffTaskService(StaffTaskService staffTaskService) {
        this.staffTaskService = staffTaskService;
    }

    @Autowired
    public void setStaffService(StaffService staffService) {
        this.staffService = staffService;
    }
}
