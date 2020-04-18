package com.example.demo.controller;

import com.example.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
@RequestMapping(value = "/index-data-manage")
public class DataManageController {
    CarService carService;

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
        model.addAttribute("CarResult",carService.selectAll(0,0));
        return "index-data-manage::CarResult";
    }

    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }
}
