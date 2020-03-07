package com.example.demo.controller;


import com.example.demo.service.StaffService;
import com.example.demo.service.StaffTaskService;
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
    private StaffTaskService staffTaskService;

    /**
     * 登录验证
     *
     * @param name
     * @param pwd
     * @param model
     * @return
     */
    @RequestMapping(value = "/login", method = POST)
    public String login(@RequestParam("name") String name, @RequestParam("pwd") String pwd, Model model) {
        int Count = 0;
        if (staffService.selectByStaffName(name) == 0) {
            model.addAttribute("loginInfo", "用户不存在,请联系管理员");
            return "authentication-login";
        }
        if (staffService.selectByStaffName(name) != 0) {
            Count = staffService.selectForLogin(name, pwd);
            if (Count == 1) {
                doLogin(name,model);
                model.addAttribute("staffName", name);
                return "index3";
            } else {
                model.addAttribute("loginInfo", "密码错误");
                return "authentication-login";
            }
        }
        model.addAttribute("loginInfo", "用户名或者密码错误");
        return "index";
    }

    /**
     * 登录成功后将信息放入model中
     */
    public void doLogin(String name,Model model) {
        /**
         * 第一步获取个人提示卡中的内容
         */
        int StaffId = staffService.selectByStaffName(name);
        
    }

    @Autowired
    public void setStaffService(StaffService staffService) {
        this.staffService = staffService;
    }

    @Autowired
    public void setStaffTaskService(StaffTaskService staffTaskService) {
        this.staffTaskService = staffTaskService;
    }
}
