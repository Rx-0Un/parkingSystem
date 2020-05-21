package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
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
    ParkingLotSettingService parkingLotSettingService;
    UserService userService;
    CarService carService;
    ParkingRecordService parkingRecordService;
    StaffDutyService staffDutyService;
    OrderService orderService;
    CouponService couponService;
    ParkingSpaceService parkingSpaceService;

    @GetMapping("/index")
    public String test() {
        return "authentication-login";
    }

    @RequestMapping(value = "/index-staff")
    public String getAll(Model model, HttpSession session) {
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
    public String parkingManage(Model model, HttpSession session) {
        model.addAttribute("UserResult", userService.selectAll());
        String starting_date = staffDutyService.selectStartingTime();
        model.addAttribute("EnterResult", parkingRecordService.selectAllByEnter(10, 0));
        model.addAttribute("ParkingCarResult", parkingRecordService.selectCar());
        model.addAttribute("OuterResult", parkingRecordService.selectAllByOuter(10, 0));
        model.addAttribute("RecordAndOrderResult", parkingRecordService.selectDutyAll(starting_date, 10, 0));
        Integer staffId = (Integer) session.getAttribute("staffId");
        String staffName = (String) session.getAttribute("staffName");
        System.out.println("当前操作职员为" + staffId + "号:" + staffName);
        model.addAttribute("staffId", staffId);
        model.addAttribute("staffName", staffName);
        model.addAttribute("chartResult", parkingRecordService.select24hourDate());
        int totalSpace = parkingLotSettingService.selectRowById(2).get(0).getSpaceNumber();
        int occupyNum = parkingRecordService.selectOccupyNum();
        model.addAttribute("nowSpaceNumber", totalSpace - occupyNum);
        return "index-manage-parking";
    }

    @RequestMapping(value = "/index_parking_lot_setting")
    public String parkingLotSetting(Model model) {
        List<TbParkingLot> list = parkingLotSettingService.selectRowById(2);
        TbParkingLot tbParkingLot = list.get(0);
        System.out.println(list.get(0).toString());
        model.addAttribute("parking_lot_name", list.get(0).getParkingLotName());
        model.addAttribute("zone_type", list.get(0).getParkingLotLevel());
        model.addAttribute("parking_space_number", list.get(0).getSpaceNumber());
        model.addAttribute("charge_open", tbParkingLot.getPaySetting().charAt(0));
        model.addAttribute("account_open", tbParkingLot.getPaySetting().charAt(1));
        model.addAttribute("coupon_open", tbParkingLot.getPaySetting().charAt(2));
        model.addAttribute("fixed_past", tbParkingLot.getPaySetting().charAt(3));
        model.addAttribute("nobody", tbParkingLot.getPaySetting().charAt(4));
        model.addAttribute("forbid_out", tbParkingLot.getPaySetting().charAt(5));
        model.addAttribute("save_data", tbParkingLot.getPaySetting().charAt(6));
        model.addAttribute("print_open", tbParkingLot.getPrintSetting());
        model.addAttribute("commonRule", tbParkingLot.getRuleSetting().charAt(0));
        model.addAttribute("customRule", tbParkingLot.getRuleSetting().charAt(1));
        model.addAttribute("personRule", tbParkingLot.getRuleSetting().charAt(2));
        model.addAttribute("interimRule", tbParkingLot.getRuleSetting().charAt(3));
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
        model.addAttribute("TestTotalPage", 1);
        model.addAttribute("TestNowPage", 1);
        model.addAttribute("FixedCarResult", carService.selectAllFixedCar());
        return "index-charge-rule";
    }

    @RequestMapping(value = "/index-order-search")
    public String indexOrderSearch(Model model) {
        model.addAttribute("OrderResult", orderService.selectAll(10, 0));
        return "index-order-search";
    }

    @RequestMapping(value = "/index-data-manage")
    public String indexCarManage(Model model) {
        model.addAttribute("UserResult", userService.selectAll());
        model.addAttribute("CarResult", carService.selectAll(0, 0));
        model.addAttribute("UserSearch", userService.selectAll());
        model.addAttribute("ParkingSpaceResult", parkingSpaceService.selectAll(10, 0));
        List list = parkingSpaceService.selectAll(0, 0);
        model.addAttribute("ParkingSpaceTotalPage", list.size() / 10);
        model.addAttribute("ParkingSpaceNowPage", 1);
        return "index-data-manage";
    }

    @RequestMapping(value = "/index-app-user-manage")
    public String appUserManage() {
        return "index-app-user-manage";
    }

    @RequestMapping(value = "/index-coupon-manage")
    public String couponManage(Model model) {
        model.addAttribute("CouponResult", couponService.selectAll(10, 0));
        return "index-coupon-manage";
    }

    @RequestMapping(value = "/index-chart-statistics")
    public String chartStatistics(Model model) {
        List<TbStaffDuty> list = staffDutyService.selectAll(16, 0);
//        for (int i=0;i<list.size();i++){
//            model.addAttribute("DutyResult"+i,list.get(i));
//        }
        model.addAttribute("DutyResult", list);
        return "index-chart-statistics";
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

    @Autowired
    public void setParkingLotSettingService(ParkingLotSettingService parkingLotSettingService) {
        this.parkingLotSettingService = parkingLotSettingService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setParkingRecordService(ParkingRecordService parkingRecordService) {
        this.parkingRecordService = parkingRecordService;
    }

    @Autowired
    public void setStaffDutyService(StaffDutyService staffDutyService) {
        this.staffDutyService = staffDutyService;
    }

    @Autowired
    public void setCouponService(CouponService couponService) {
        this.couponService = couponService;
    }

    @Autowired
    public void setParkingSpaceService(ParkingSpaceService parkingSpaceService) {
        this.parkingSpaceService = parkingSpaceService;
    }
}
