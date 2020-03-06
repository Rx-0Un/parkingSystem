package com.example.demo.controller;


import com.example.demo.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


/**
 * 职员操作相关
 */
@Controller
public class LoginController {

    private StaffService staffService;

    @RequestMapping(value = "/login", method = POST)
    public String login(@RequestParam("name") String name, @RequestParam("pwd") String pwd, Model model) {

        System.out.println(name + "  " +
                pwd);
        int Count = 0;
        String loginInfo ="登录成功";
        Count = staffService.SelectForLogin(name, pwd);
        if (Count == 1) {
            model.addAttribute("loginInfo","!!!!!!");
            System.out.println(model.getAttribute("loginInfo"));
            return "index";
        }
        return "error";
    }

    @Autowired
    public void setStaffService(StaffService staffService) {
        this.staffService = staffService;
    }
}
