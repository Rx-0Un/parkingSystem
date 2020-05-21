package com.example.demo.controller;

import com.example.demo.entity.TbParkingRecord;
import com.example.demo.entity.TbStaffDuty;
import com.example.demo.service.CarService;
import com.example.demo.service.ParkingRecordService;
import com.example.demo.service.StaffDutyService;
import com.example.demo.service.StaffService;
import com.example.demo.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/index-chart-statistics")
public class StaffDutyController {
    StaffDutyService staffDutyService;
    StaffService staffService;
    ParkingRecordService parkingRecordService;
    CarService carService;

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
        model.addAttribute("staff", staffService.selectNameById(Integer.parseInt(staffDutyId)));
        model.addAttribute("lastTime", DateUtil.dateDiff(starting_time, ending_time));

        model.addAttribute("ExitCar", parkingRecordService.selectExitCarByDate(starting_time, ending_time));
        model.addAttribute("Outer", parkingRecordService.selectEnterCarByDate(starting_time, ending_time));
        model.addAttribute("Order", parkingRecordService.selectAllByDate(starting_time, ending_time));
        model.addAttribute("Total", parkingRecordService.selectCountByDate(starting_time, ending_time));
        List<TbParkingRecord> list1 = parkingRecordService.selectInterimCarByDate(starting_time, ending_time);
        int interim = 0;
        int fix = 0;
        for (TbParkingRecord tbParkingRecord : list1) {
            String carType = carService.selectRowByCarNum(tbParkingRecord.getCarPlateNum()).getCarType();
            if (carType.equals("临时车")) {
                interim++;
            } else {
                fix++;
            }
        }
        model.addAttribute("interim", interim);
        model.addAttribute("fix", fix);


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
    public void setCarService(CarService carService) {
        this.carService = carService;
    }
}
