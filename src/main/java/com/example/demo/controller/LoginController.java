package com.example.demo.controller;


import com.example.demo.entity.TbStaffTask;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


/**
 * 职员操作相关
 */
@Controller("LoginController")
public class LoginController {


    private StaffService staffService;

    private StaffTaskService staffTaskService;
    UserService userService;
    OrderService orderService;
    ParkingLotSettingService parkingLotSettingService;
    ParkingRecordService parkingRecordService;
    CarService carService;

    /**
     * 登录验证
     *
     * @param name
     * @param pwd
     * @param model
     * @return
     */
    @RequestMapping(value = "/login", method = POST)
    public String login(@RequestParam("name") String name, @RequestParam("pwd") String pwd, Model model, HttpSession session) {
        int Count = 0;
        if (staffService.selectByStaffName(name) == 0) {
            model.addAttribute("loginInfo", "用户不存在,请联系管理员");
            return "authentication-login";
        }
        if (staffService.selectByStaffName(name) != 0) {
            Count = staffService.selectForLogin(name, pwd);
            if (Count == 1) {
                doLogin(name, model, session);
                model.addAttribute("staffName", name);
                session.setAttribute("staffName", name);
                session.setAttribute("staffId", staffService.selectIdByName(name));
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
     * 登录成功后将信息放入model或者session中
     */
    public void doLogin(String name, Model model, HttpSession session) {
        int StaffId = staffService.selectIdByName(name);
        int CurrentTaskNum = staffTaskService.selectCountByStaffId(StaffId + "");
        //第一步获取个人提示卡中的内容
        session.setAttribute("staffId", StaffId);
        session.setAttribute("staffName", name);
        model.addAttribute("currentTask", CurrentTaskNum);
        //获取未完成任务内容
        List<TbStaffTask> list = staffTaskService.selectUnfinishedTask("" + StaffId, 10, 0);
        int totalPage = staffTaskService.selectUnfinishedTask("" + StaffId, 0, 0).size();
        model.addAttribute("nowPage", 1);
        model.addAttribute("totalPage", (totalPage / 10) - 1);
//        System.out.println(list.get(0).toString());
        model.addAttribute("AllPeople", staffService.selectAll().size() + userService.selectAll().size());
        model.addAttribute("newUser", userService.selectAll().size());
        model.addAttribute("AllUser", userService.selectAll().size());
        model.addAttribute("AllStaff", staffService.selectAll().size());
        model.addAttribute("AllOrder", orderService.selectAll(0, 0).size());
        model.addAttribute("AllSpace", parkingLotSettingService.selectRowById(2).get(0).getSpaceNumber());
        model.addAttribute("AllCar", carService.selectAll(0, 0).size());
        model.addAttribute("NowSpace", parkingLotSettingService.selectRowById(2).get(0).getSpaceNumber() - parkingRecordService.selectOccupyNum());
        model.addAttribute("AllTask", list);
    }

    @RequestMapping(value = "/mainPager")
    public String gotoMainPager(Model model, HttpSession session) {
        try {
            String staffName = String.valueOf(session.getAttribute("staffName"));
            String staffId = String.valueOf(session.getAttribute("staffId"));
            doLogin(staffName, model, session);
        } catch (Exception e) {
            model.addAttribute("loginInfo", "尚未登录");
            return "authentication-login";
        }
        return "index3";
    }

    @RequestMapping(value = "/page")
    public String page(Model model, @RequestParam(value = "page") String page, HttpSession session) {
        String staffId = session.getAttribute("staffId").toString();
        System.out.println(page + "!" + staffId);
        List<TbStaffTask> list = staffTaskService.selectUnfinishedTask("" + staffId, 10, (Integer.parseInt(page) - 1) * 10);
        model.addAttribute("AllTask", list);
        model.addAttribute("totalPage", list.size());
        model.addAttribute("nowPage", Integer.parseInt(page));
        session.setAttribute("staffId", Integer.valueOf(staffId));
        return "index3::result";
    }

    @Autowired
    public void setStaffService(StaffService staffService) {
        this.staffService = staffService;
    }

    @Autowired
    public void setStaffTaskService(StaffTaskService staffTaskService) {
        this.staffTaskService = staffTaskService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setParkingLotSettingService(ParkingLotSettingService parkingLotSettingService) {
        this.parkingLotSettingService = parkingLotSettingService;
    }

    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    @Autowired
    public void setParkingRecordService(ParkingRecordService parkingRecordService) {
        this.parkingRecordService = parkingRecordService;
    }
}
