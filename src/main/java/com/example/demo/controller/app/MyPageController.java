package com.example.demo.controller.app;

import com.example.demo.bean.app.MyPageBean;
import com.example.demo.entity.TbUser;
import com.example.demo.service.CouponService;
import com.example.demo.service.ParkingSpaceService;
import com.example.demo.service.UserAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/App/myPage")
public class MyPageController {
    UserAmountService userAmountService;
    ParkingSpaceService parkingSpaceService;
    CouponService couponService;


    @RequestMapping(value = "/selectRowByPhone")
    public MyPageBean selectRowByPhone(String phone) {
        TbUser tbUser = userAmountService.selectRowByPhone(phone).get(0);
        String userId = String.valueOf(tbUser.getUserId());
        float myBalance = userAmountService.selectAmountByInfo(userId);
        int mySpace = parkingSpaceService.selectCarSpaceNumberByUserId(userId);
        int myCoupon = 0;
        MyPageBean myPageBean = new MyPageBean(myBalance, mySpace, myCoupon, tbUser);
        return myPageBean;
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
