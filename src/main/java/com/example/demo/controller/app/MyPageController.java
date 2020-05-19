package com.example.demo.controller.app;

import com.example.demo.bean.app.MyPageBean;
import com.example.demo.service.CouponService;
import com.example.demo.service.ParkingSpaceService;
import com.example.demo.service.UserAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/App")
public class MyPageController {
    UserAmountService userAmountService;
    ParkingSpaceService parkingSpaceService;
    CouponService couponService;

    @RequestMapping(value = "/myPage")
    public MyPageBean myPage(String user_id) {
        userAmountService.selectAmountByInfo(user_id);

        return null;
    }

    @Autowired
    public void setUserAmountService(UserAmountService userAmountService) {
        this.userAmountService = userAmountService;
    }

    @Autowired
    public void setParkingSpaceService(ParkingSpaceService parkingSpaceService) {
        this.parkingSpaceService = parkingSpaceService;
    }

    @Autowired
    public void setCouponService(CouponService couponService) {
        this.couponService = couponService;
    }
}
