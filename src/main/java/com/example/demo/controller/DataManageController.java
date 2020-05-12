package com.example.demo.controller;

import com.example.demo.entity.TbUser;
import com.example.demo.service.CarService;
import com.example.demo.service.ParkingLotSettingService;
import com.example.demo.service.ParkingSpaceService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/index-data-manage")
public class DataManageController {
    CarService carService;
    UserService userService;
    ParkingLotSettingService parkingLotSettingService;
    ParkingSpaceService parkingSpaceService;

    @RequestMapping(value = "/addCarByInfo", method = RequestMethod.POST)
    public String addCarByInfo(Model model, @RequestBody Map<String, String> map) {
        String car_type = map.get("car_type");
        String car_plate_number_head = map.get("car_plate_number_head");
        String car_plate_number = map.get("car_plate_number");
        String car_color = map.get("car_color");
        String car_type_model = map.get("car_type_model");
        String car_user = map.get("car_user");
        System.out.println(car_type);
        System.out.println(car_plate_number_head);
        System.out.println(car_plate_number);
        System.out.println(car_color);
        System.out.println(car_type_model);
        System.out.println(car_user);
        carService.addRowByInfo(car_type, car_plate_number_head + car_plate_number, car_color, car_type_model, Integer.parseInt(car_user.trim()));
        model.addAttribute("CarResult", carService.selectAll(0, 0));
        return "index-data-manage::CarResult";
    }

    @RequestMapping(value = "/selectUser")
    public String selectUser(Model model, @RequestBody Map<String, String> map) {
        String select = map.get("select");
        String Keyword = map.get("Keyword");
        model.addAttribute("UserSearch", userService.selectUser(select, Keyword));
        return "index-data-manage::UserSearch";
    }

    @RequestMapping(value = "/addParkingSpaceBefore")
    public String addParkingSpaceBefore(Model model) {
        int spaceNum = parkingLotSettingService.selectRowById(2).get(0).getSpaceNumber();
        int fixParkingSpace = parkingSpaceService.selectAll(0, 0) == null ? 0 : parkingSpaceService.selectAll(0, 0).size();
        int interimParkingSpace = spaceNum - fixParkingSpace;
        model.addAttribute("spaceNum", spaceNum);
        model.addAttribute("interimParkingSpace", interimParkingSpace);
        model.addAttribute("fixParkingSpace", fixParkingSpace);
        return "index-data-manage::SpaceNumberResult";
    }
    @RequestMapping(value = "/addParkingSpace")
    public String addParkingSpace(Model model,@RequestBody Map<String, String> map) {

        return "index-data-manage::SpaceNumberResult";
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    @Autowired
    public void setParkingLotSettingService(ParkingLotSettingService parkingLotSettingService) {
        this.parkingLotSettingService = parkingLotSettingService;
    }

    @Autowired
    public void setParkingSpaceService(ParkingSpaceService parkingSpaceService) {
        this.parkingSpaceService = parkingSpaceService;
    }
}
