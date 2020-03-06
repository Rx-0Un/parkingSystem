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

    /**
     * 登录验证
     * @param name
     * @param pwd
     * @param model
     * @return
     */
    @RequestMapping(value = "/login", method = POST)
    public String login(@RequestParam("name") String name, @RequestParam("pwd") String pwd, Model model) {
        int Count = 0;
        if (staffService.SelectByStaffName(name)==0){
            model.addAttribute("loginInfo","用户不存在,请联系管理员");
            return "authentication-login";
        }
        if (staffService.SelectByStaffName(name)!=0) {
            Count = staffService.SelectForLogin(name, pwd);
            if (Count == 1) {
                model.addAttribute("loginInfo", "登录成功");
                return "index3";
            }
            else {
                model.addAttribute("loginInfo", "密码错误");
                return "authentication-login";
            }
        }
        model.addAttribute("loginInfo", "用户名或者密码错误");
        return "index";
    }

    @Autowired
    public void setStaffService(StaffService staffService) {
        this.staffService = staffService;
    }
}
