package com.example.demo.controller;

import com.example.demo.entity.TbOrder;
import com.example.demo.entity.TbParkingRecord;
import com.example.demo.entity.TbStaffDuty;
import com.example.demo.service.*;
import com.example.demo.util.DateUtil;
import com.example.demo.util.FileUtil;
import org.apache.ibatis.binding.BindingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/index-manage-parking")
public class ParkingManageController {
    ParkingRecordService parkingRecordService;
    CarService carService;
    OrderService orderService;
    StaffTaskService staffTaskService;
    StaffDutyService staffDutyService;
    StaffService staffService;

    @RequestMapping(value = "/addParkingRecordEnter", method = RequestMethod.POST)
    public String addParkingRecordEnter(Model model, @RequestBody Map<String, String> map, HttpSession session) {
        String car_type = map.get("car_type");
        String car_type_model = map.get("car_type_model");
        String car_user = map.get("car_user");
        String car_plate_num_head = map.get("car_plate_num_head");
        String car_plate_num = map.get("car_plate_num");
        String car_color = map.get("car_color");
        System.out.println(map.toString());
        String car_plate_number = car_plate_num_head + car_plate_num;
        carService.addRowByInfo(car_type, car_plate_number, car_color, car_type_model, processCarUser(car_user));
        parkingRecordService.addRowByInfo(car_plate_number, new Date());
        String starting_date = staffDutyService.selectStartingTime();
        model.addAttribute("EnterResult", parkingRecordService.selectEnter(starting_date, 10, 0));
        return "index-manage-parking::EnterResult";
    }

    public int processCarUser(String car_user) {
        if (car_user.equals("临时车主")) {
            return 0;
        }
        return Integer.parseInt(car_user);
    }

    @RequestMapping(value = "/addParkingOrderOuter", method = RequestMethod.POST)
    public String addParkingOrderOuter(Model model, @RequestBody Map<String, String> map, HttpSession session) {
        String order_car_number = map.get("order_car_number");
        String order_pay_type = map.get("order_pay_type");
        String order_payer = map.get("order_payer");
        String order_receiver = map.get("order_receiver");
        String money = map.get("money");
        System.out.println(map.toString());
        TbOrder tbOrder = new TbOrder();
        tbOrder.setOrderAmount(Float.parseFloat(money));
        tbOrder.setOrderPayer(order_payer);
        tbOrder.setOrderReceiver(order_receiver);
        tbOrder.setOrderState("已完成");
        tbOrder.setOrderPayType(order_pay_type);
        tbOrder.setOrderPurchaseType("停车场支付");
        orderService.addRowByInfo(tbOrder);
        String starting_date = staffDutyService.selectStartingTime();
        System.out.println(parkingRecordService.updateByOuter(order_car_number, starting_date, new Date(), tbOrder.getOrderId()));
        model.addAttribute("OuterResult", parkingRecordService.selectOuter(starting_date, 10, 0));
        return "index-manage-parking::OuterResult";
    }

    @RequestMapping(value = "/updateDutyOffWork", method = RequestMethod.GET)
    public String updateDutyOffWork(Model model, HttpSession session) {
        String startTime = (String) session.getAttribute("startTime");
        model.addAttribute("ending_time", DateUtil.getNowDate());
        model.addAttribute("enterCount", parkingRecordService.selectCountEnter(startTime));
        model.addAttribute("outerCount", parkingRecordService.selectCountOuter(startTime));
        model.addAttribute("orderCount", orderService.selectRowCount(startTime));
        try {
            model.addAttribute("TotalCount", orderService.selectTotalCount(startTime));
        } catch (BindingException e) {
            model.addAttribute("TotalCount", 0);
        }

        model.addAttribute("OffWorkResult", parkingRecordService.selectDutyAll(startTime, 10, 0));
        return "index-manage-parking::OffWorkResult";
    }

    @RequestMapping(value = "/commitDutyOffWork", method = RequestMethod.POST)
    public String commitDutyOffWork(Model model, @RequestBody Map<String, String> map, HttpSession session) {
        String startTime = (String) session.getAttribute("startTime");
        String endTime = DateUtil.getNowDate();
        staffDutyService.updateRowByInf0(endTime);
        TbStaffDuty tbStaffDuty = staffDutyService.selectLastRow();
        String enterCount = String.valueOf(parkingRecordService.selectCountEnter(startTime));
        String outerCount = String.valueOf(parkingRecordService.selectCountOuter(startTime));
        String orderCount = String.valueOf(orderService.selectRowCount(startTime));
        String TotalCount = String.valueOf(orderService.selectTotalCount(startTime));
        Integer staffId = (Integer) session.getAttribute("staffId");//职员编号
        String staffName = (String) session.getAttribute("staffName");//职员姓名
        FileUtil.exportExcel(parkingRecordService.selectDutyAll(startTime, 10, 0), enterCount, outerCount, orderCount, TotalCount, new TbStaffDuty(), staffId, staffName);
        return "index-manage-parking";
    }

    @RequestMapping(value = "/addDutyRow", method = RequestMethod.GET)
    public String addDutyRow(Model model, HttpSession session) {
        String date = DateUtil.getNowDate();
        //获取当前操作员信息
        Integer staffId = (Integer) session.getAttribute("staffId");
        Integer staff_task_id = staffTaskService.selectTaskToday(staffId, date);
        if (staff_task_id != null) {
            staffDutyService.addRowByInfo(staffId, date, staff_task_id);
            model.addAttribute("info", "1");
            session.setAttribute("startTime", staffDutyService.selectStartingTime());
            session.setAttribute("staffId", staffId);
            session.setAttribute("nowStaff", staffService.selectNameById(staffId));
        } else {
            model.addAttribute("info", "0");
        }
        return "index-manage-parking";
    }

    @Autowired
    public void setParkingRecordService(ParkingRecordService parkingRecordService) {
        this.parkingRecordService = parkingRecordService;
    }

    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setStaffTaskService(StaffTaskService staffTaskService) {
        this.staffTaskService = staffTaskService;
    }

    @Autowired
    public void setStaffDutyService(StaffDutyService staffDutyService) {
        this.staffDutyService = staffDutyService;
    }

    @Autowired
    public void setStaffService(StaffService staffService) {
        this.staffService = staffService;
    }
}
