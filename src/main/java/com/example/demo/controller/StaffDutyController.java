package com.example.demo.controller;

import com.example.demo.bean.ChartBean;
import com.example.demo.entity.TbParkingRecord;
import com.example.demo.entity.TbStaffDuty;
import com.example.demo.service.*;
import com.example.demo.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/index-chart-statistics")
public class StaffDutyController {
    StaffDutyService staffDutyService;
    StaffService staffService;
    ParkingLotSettingService parkingLotSettingService;
    ParkingRecordService parkingRecordService;
    ParkingSpaceService parkingSpaceService;
    CarService carService;
    UserService userService;

    //    @RequestMapping(value = "/selectDetail", method = RequestMethod.GET)
//    public String selectDetail(Model model, HttpServletRequest request) {
//        String staffDutyId = request.getParameter("staffDutyId");
//        List<TbStaffDuty> list = staffDutyService.selectAll(0, 0);
//        TbStaffDuty tbStaffDuty = new TbStaffDuty();
//        for (TbStaffDuty tbStaffDuty1 : list) {
//            if (tbStaffDuty1.getStaffDutyId() == Integer.parseInt(staffDutyId)) {
//                tbStaffDuty = tbStaffDuty1;
//            }
//        }
//
//        Date starting_time = tbStaffDuty.getStartingTime();
//        Date ending_time = tbStaffDuty.getEndingTime();
//
//        model.addAttribute("startTime", DateUtil.processDateToString(starting_time));
//        model.addAttribute("endTime", DateUtil.processDateToString(ending_time));
//        model.addAttribute("staff", userService.selectRowByUserId(String.valueOf(tbStaffDuty.getStaffId())).getUserName());
//        model.addAttribute("lastTime", DateUtil.dateDiff(starting_time, ending_time));
//
//
//
//
//        model.addAttribute("ExitCar", parkingRecordService.selectExitCarByDate(starting_time, ending_time));
//        model.addAttribute("Outer", parkingRecordService.selectEnterCarByDate(starting_time, ending_time));
//        model.addAttribute("Order", parkingRecordService.selectAllByDate(starting_time, ending_time));
//        model.addAttribute("Total", parkingRecordService.selectCountByDate(starting_time, ending_time));
//        List<TbParkingRecord> list1 = parkingRecordService.selectInterimCarByDate(starting_time, ending_time);
//        int interim = 0;
//        int fix = 0;
//        List<ChartBean> list2 = new ArrayList<>();
//        for (TbParkingRecord tbParkingRecord : list1) {
//            String carType = carService.selectRowByCarNum(tbParkingRecord.getCarPlateNum()).getCarType();
//            if (carType.equals("临时车")) {
//                interim++;
//            } else {
//                fix++;
//            }
//            Date enter = tbParkingRecord.getEnterTime();
//            Date exit = tbParkingRecord.getExitTime();
//            if (enter.getTime() > starting_time.getTime() && enter.getTime() < ending_time.getTime()) {
//                list2.add(new ChartBean(enter.getTime(), +1));
//            }
//            if (exit.getTime() > starting_time.getTime() && exit.getTime() < ending_time.getTime()) {
//                list2.add(new ChartBean(exit.getTime(), -1));
//            }
//        }
//        for (int i = 0; i < list2.size() - 1; i++) {
//            for (int j = 0; j < list2.size() - 1 - i; j++) {
//                //比较两个整数的大小
//                if (list2.get(j).getDate() > list2.get(j + 1).getDate()) {
//                    ChartBean chartBean = list2.get(j);
//
//                    list2.set(j, list2.get(j + 1));
//                    list2.set(j + 1, chartBean);
//                }
//            }
//        }
//        List<Integer> list3 = new ArrayList<>();
//        List<Long> list4 = new ArrayList<>();
//        int total = parkingLotSettingService.selectRowById(2).get(0).getSpaceNumber() - parkingRecordService.selectNumByDate(starting_time);
//        for (ChartBean chartBean : list2) {
//            list3.add(total + chartBean.getI());
//            list4.add(chartBean.getDate());
//        }
//        model.addAttribute("interim", interim);
//        model.addAttribute("fix", fix);
//        model.addAttribute("chartY", list3);
//        model.addAttribute("chartX", list4);
//        return "index-chart-statistics-detail";
//    }
    @RequestMapping(value = "/selectDetail", method = RequestMethod.GET)
    public String selectDetail(Model model, HttpServletRequest request) {
        String staffDutyId = request.getParameter("staffDutyId");
        List<TbStaffDuty> list = staffDutyService.selectAll(0, 0);
        TbStaffDuty tbStaffDuty = new TbStaffDuty();
        for (TbStaffDuty tbStaffDuty1 : list) {
            if (tbStaffDuty1.getStaffDutyId() == Integer.parseInt(staffDutyId)) {
                tbStaffDuty = tbStaffDuty1;
            }
        }
        Date starting_time = tbStaffDuty.getStartingTime();
        Date ending_time = tbStaffDuty.getEndingTime();
        model.addAttribute("startTime", DateUtil.processDateToString(starting_time));
        model.addAttribute("endTime", DateUtil.processDateToString(ending_time));
        model.addAttribute("staff", userService.selectRowByUserId(String.valueOf(tbStaffDuty.getStaffId())).getUserName());
        model.addAttribute("lastTime", DateUtil.dateDiff(starting_time, ending_time));
        List<TbParkingRecord> tbParkingRecordList = parkingRecordService.selectAllAndOrder();
        int exitCar = 0;
        int Outer = 0;
        float sum = 0;
        int interim = 0;
        int fix = 0;
        List<ChartBean> list2 = new ArrayList<>();
        for (TbParkingRecord tbParkingRecord : tbParkingRecordList) {
            if (tbParkingRecord.getExitTime().getTime() > starting_time.getTime() && tbParkingRecord.getExitTime().getTime() < ending_time.getTime()) {
                exitCar++;
                sum += tbParkingRecord.getTbOrder().getOrderAmount();
            }
            if (tbParkingRecord.getEnterTime().getTime() > starting_time.getTime() && tbParkingRecord.getEnterTime().getTime() < ending_time.getTime()) {
                Outer++;
            }
            if (tbParkingRecord.getTbCar().getCarTypeModel().equals("临时车")) {
                interim++;
            } else {
                fix++;
            }
            Date enter = tbParkingRecord.getEnterTime();
            Date exit = tbParkingRecord.getExitTime();
            if (enter.getTime() > starting_time.getTime() && enter.getTime() < ending_time.getTime()) {
                list2.add(new ChartBean(enter.getTime(), +1));
            }
            if (exit.getTime() > starting_time.getTime() && exit.getTime() < ending_time.getTime()) {
                list2.add(new ChartBean(exit.getTime(), -1));
            }
        }
        model.addAttribute("ExitCar", exitCar);
        model.addAttribute("Outer", Outer);
        model.addAttribute("Order", exitCar);
        model.addAttribute("Total", sum);
        model.addAttribute("interim", interim);
        model.addAttribute("fix", fix);
        for (int i = 0; i < list2.size() - 1; i++) {
            for (int j = 0; j < list2.size() - 1 - i; j++) {
                //比较两个整数的大小
                if (list2.get(j).getDate() > list2.get(j + 1).getDate()) {
                    ChartBean chartBean = list2.get(j);

                    list2.set(j, list2.get(j + 1));
                    list2.set(j + 1, chartBean);
                }
            }
        }
        List<Integer> list3 = new ArrayList<>();
        List<Long> list4 = new ArrayList<>();
        int total = parkingLotSettingService.selectRowById(2).get(0).getSpaceNumber() - parkingRecordService.selectNumByDate(starting_time);
        for (ChartBean chartBean : list2) {
            list3.add(total + chartBean.getI());
            list4.add(chartBean.getDate());
        }
        model.addAttribute("chartY", list3);
        model.addAttribute("chartX", list4);
        return "index-chart-statistics-detail";
    }

    @Autowired
    public void setStaffDutyService(StaffDutyService staffDutyService) {
        this.staffDutyService = staffDutyService;
    }

    @Autowired
    public void setStaffService(StaffService staffService) {
        this.staffService = staffService;
    }

    @Autowired
    public void setParkingRecordService(ParkingRecordService parkingRecordService) {
        this.parkingRecordService = parkingRecordService;
    }

    @Autowired
    public void setParkingLotSettingService(ParkingLotSettingService parkingLotSettingService) {
        this.parkingLotSettingService = parkingLotSettingService;
    }

    @Autowired
    public void setParkingSpaceService(ParkingSpaceService parkingSpaceService) {
        this.parkingSpaceService = parkingSpaceService;
    }

    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
