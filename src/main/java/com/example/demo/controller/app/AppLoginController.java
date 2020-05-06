package com.example.demo.controller.app;

import com.example.demo.bean.app.LoginDate;
import com.example.demo.bean.app.RegisterDate;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/App")
public class AppLoginController {

    UserService userService;

    @RequestMapping(value = "/login")
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
            switch (userService.selectForLogin(phone, password)) {
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

    @RequestMapping(value = "/register")
    public RegisterDate addNewUser(String phone, String password) {
        RegisterDate registerDate = new RegisterDate();
        int count = userService.selectByUserPhone(phone);
        //判断用户是否存在
        if (count == 1) {
            //注册失败返回0
            registerDate.setRegisterCode(1);
            registerDate.setRegisterDetail("用户已经存在");
        }
        else  {
            userService.addUserByInfo(phone, password);
            registerDate.setRegisterCode(0);
            registerDate.setRegisterDetail("成功");
        }
        return registerDate;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
