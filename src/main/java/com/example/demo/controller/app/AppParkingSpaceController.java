package com.example.demo.controller.app;

import com.example.demo.bean.app.OrderData;
import com.example.demo.bean.app.ParkingSpaceData;
import com.example.demo.service.OrderService;
import com.example.demo.service.ParkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/App/ParkingSpace")
public class AppParkingSpaceController {
    ParkingSpaceService parkingSpaceService;

    @RequestMapping("/selectNotOccupy")
    public ParkingSpaceData selectNotOccupy() {
        return new ParkingSpaceData(parkingSpaceService.selectNotOccupy());
    }

    @RequestMapping("/selectParkingSpaceByUserId")
    public ParkingSpaceData selectOrderByUserId(String userId) {
        return new ParkingSpaceData(parkingSpaceService.selectAllByUserId(userId));
    }

    @Autowired
    public void setParkingSpaceService(ParkingSpaceService parkingSpaceService) {
        this.parkingSpaceService = parkingSpaceService;
    }
}