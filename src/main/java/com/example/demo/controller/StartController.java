package com.example.demo.controller;

import com.example.demo.entity.TbRuleCommonBasic;
import com.example.demo.entity.TbRuleCustomInterim;
import com.example.demo.entity.TbStaffTask;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 此controller用于完成界面跳转
 */
@Controller
public class StartController {
    StaffService staffService;
    StaffTaskService staffTaskService;
    RuleCommonBasicService ruleCommonBasicService;
    RulePersonService rulePersonService;
    RuleFixedParkingService ruleFixedParkingService;
    RuleCustomInterimService ruleCustomInterimService;
    RuleCustomService ruleCustomService;

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
        model.addAttribute("totalPage", totalPage + 1);
        return "index-duty-statistics";
    }

    @RequestMapping(value = "/index-manage-parking")
    public String parkingManage(Model model) {
        return "index-manage-parking";
    }

    @RequestMapping(value = "/index_parking_lot_setting")
    public String parkingLotSetting() {
        return "index-parking-lot-setting";
    }

    @RequestMapping(value = "/index-charge-rule")
    public String chargeRule(Model model) {
        //加载基本规则表
        List<TbRuleCommonBasic> list = ruleCommonBasicService.selectAll();
        for (int i = 0; i < list.size(); i++) {
            int j = i + 1;
            model.addAttribute("price_" + j, list.get(i).getMoney());
        }
        model.addAttribute("basic", ruleCommonBasicService.selectAll());
        model.addAttribute("PersonResult", rulePersonService.selectAll());
        model.addAttribute("FixedResult", ruleFixedParkingService.selectAll());


        //加载临时规则表
        int total = (ruleCustomInterimService.selectAll(0, 0).size() / 10) + 1;
        model.addAttribute("InterimResult", ruleCustomInterimService.selectAll(10, 0));
        model.addAttribute("InterimNowPage", 1);
        model.addAttribute("InterimTotalPage", total);

        model.addAttribute("CustomResult", ruleCustomService.selectAll());
        return "index-charge-rule";
    }

    @RequestMapping(value = "/index-order-search")
    public String indexOrderSearch() {
        return "index-order-search";
    }

    @RequestMapping(value = "/index-car-manage")
    public String indexCarManage() {
        return "index-car-manage";
    }

    @RequestMapping(value = "/index-app-user-manage")
    public String appUserManage() {
        return "index-app-user-manage";
    }

    @Autowired
    public void setStaffTaskService(StaffTaskService staffTaskService) {
        this.staffTaskService = staffTaskService;
    }

    @Autowired
    public void setStaffService(StaffService staffService) {
        this.staffService = staffService;
    }

    @Autowired
    public void setRuleCommonBasicService(RuleCommonBasicService ruleCommonBasicService) {
        this.ruleCommonBasicService = ruleCommonBasicService;
    }

    @Autowired
    public void setRulePersonService(RulePersonService rulePersonService) {
        this.rulePersonService = rulePersonService;
    }

    @Autowired
    public void setRuleFixedParkingService(RuleFixedParkingService ruleFixedParkingService) {
        this.ruleFixedParkingService = ruleFixedParkingService;
    }

    @Autowired
    public void setRuleCustomInterimService(RuleCustomInterimService ruleCustomInterimService) {
        this.ruleCustomInterimService = ruleCustomInterimService;
    }

    @Autowired
    public void setRuleCustomService(RuleCustomService ruleCustomService) {
        this.ruleCustomService = ruleCustomService;
    }
}
