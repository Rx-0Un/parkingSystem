package com.example.demo.controller.app;

import com.example.demo.bean.LoginDate;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/App")
public class AppLoginController {

    UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public LoginDate doLogin(String phone, String password) {
        LoginDate loginDate = new LoginDate();
        int count = userService.selectByUserPhone(phone);
        //判断用户是否存在
        if (count != 1) {
            //登录失败返回0
            loginDate.setLoginCode(0);
            loginDate.setLoginDetail("用户不存在");
            return loginDate;
        }
        if (count == 1) {
            switch (userService.selectForLogin(phone, password)){
                case 0:
                    loginDate.setLoginCode(0);
                    loginDate.setLoginDetail("密码错误");
                    return loginDate;
                case 1:
                    loginDate.setLoginCode(1);
                    loginDate.setLoginDetail("登录成功");
                    return loginDate;
            }
        }
        return loginDate;
    }
}
