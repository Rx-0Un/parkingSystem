package com.example.demo.controller;

import com.example.demo.entity.TbParkingRecord;
import com.example.demo.service.CarService;
import com.example.demo.service.ParkingRecordService;
import com.example.demo.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/index-manage-parking")
public class ParkingManageController {
    ParkingRecordService parkingRecordService;
    CarService carService;

    @RequestMapping(value = "/addParkingRecordEnter", method = RequestMethod.POST)
    public String addParkingRecordEnter(Model model, @RequestBody Map<String, String> map) {
        String car_type = map.get("car_type");
        String car_type_model = map.get("car_type_model");
        String car_user = map.get("car_user");
        String car_plate_num_head = map.get("car_plate_num_head");
        String car_plate_num = map.get("car_plate_num");
        String car_color = map.get("car_color");
        String date = map.get("date");
        System.out.println(map.toString());
        String car_plate_number = car_plate_num_head + car_plate_num;
        carService.addRowByInfo(car_type, car_plate_number, car_color, car_type_model, processCarUser(car_user));
//        car_plate_number
        parkingRecordService.addRowByInfo(car_plate_number, DateUtil.process(date));
        model.addAttribute("EnterResult", parkingRecordService.selectAll(0,0));
        return "index-manage-parking::EnterResult";
    }

    @RequestMapping(value = "/addParkingRecordOuter", method = RequestMethod.POST)
    public String addParkingRecordOuter(Model model, @RequestBody Map<String, String> map) {
        String car_type = map.get("car_type");
        String car_type_model = map.get("car_type_model");
        String car_user = map.get("car_user");
        String car_plate_num_head = map.get("car_plate_num_head");
        String car_plate_num = map.get("car_plate_num");
        String car_color = map.get("car_color");
        String date = map.get("date");
        System.out.println(map.toString());
        String car_plate_number = car_plate_num_head + car_plate_num;
        carService.addRowByInfo(car_type, car_plate_number, car_color, car_type_model, processCarUser(car_user));
//        car_plate_number
        parkingRecordService.addRowByInfo(car_plate_number, DateUtil.process(date));
        model.addAttribute("EnterResult", parkingRecordService.selectAll(0,0));
        return "index-manage-parking::EnterResult";
    }
    public int processCarUser(String car_user) {
        if (car_user.equals("临时车主")) {
            return 0;
        }
        return Integer.parseInt(car_user);
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
