package com.example.demo.controller.app;

import com.example.demo.bean.app.CarResult;
import com.example.demo.bean.app.CarData;
import com.example.demo.service.CarService;
import com.example.demo.service.ParkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/App/Car")
public class AppCarController {
    CarService carService;
    ParkingSpaceService parkingSpaceService;

    @RequestMapping("/selectAllCarByUserId")
    public CarData selectAllCarByUserId(String userId) {
        return new CarData(carService.selectAllCarByUserId(userId));
    }

    @RequestMapping("/addCarByInfo")
    public CarResult addCarByInfo(String car_type, String car_plate_number, String car_color, String car_type_model, int userId) {
        int code = carService.addRowByInfo(car_type, car_plate_number, car_color, car_type_model, userId);
        return new CarResult(code);
    }

    @RequestMapping("/deleteCarByInfo")
    public CarResult deleteCarByInfo(String carId) {
        parkingSpaceService.updateParkingSpaceByCarId(carId);
        return new CarResult(carService.deleteCarByCarId(carId));
    }

    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }


    @Autowired
    public void setParkingSpaceService(ParkingSpaceService parkingSpaceService) {
        this.parkingSpaceService = parkingSpaceService;
    }
}
