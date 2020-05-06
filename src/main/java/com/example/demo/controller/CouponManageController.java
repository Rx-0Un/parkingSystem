package com.example.demo.controller;

import com.example.demo.mapper.TbCouponMapper;
import com.example.demo.mapper.TbParkingLotMapper;
import com.example.demo.service.CouponService;
import com.example.demo.service.ParkingLotSettingService;
import com.example.demo.service.ParkingRecordService;
import com.example.demo.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping(value = "/index-manage-parking")
public class CouponManageController {
    CouponService couponService;
    ParkingLotSettingService parkingLotSettingService;

    @RequestMapping(value = "/addNewCoupon")
    public String addNewCoupon(Model model, @RequestBody Map<String, String> map) {
        String time = map.get("time");
        Date starting_time = new Date();
        Date ending_time = new Date();
        for (int i = 0; i < time.length(); i++) {
            if (time.charAt(i) == '到') {
                starting_time = DateUtil.process(time.substring(0, i - 1));
                ending_time = DateUtil.process(time.substring(i + 1, time.length()));
            }
        }
        String coupon_amount = map.get("coupon_amount");
        String coupon_count = map.get("coupon_count");
        String coupon_usage_count = map.get("coupon_usage_count");
        couponService.addRowByInfo(Float.parseFloat(coupon_amount), Integer.parseInt(coupon_count),
                parkingLotSettingService.selectRowById(2).get(0).getParkingLotName(), Integer.parseInt(coupon_usage_count),
                starting_time, ending_time, "");
        model.addAttribute("CouponResult", couponService.selectAll(10, 0));
        return "index-manage-parking";
    }

    @Autowired
    public void setCouponService(CouponService couponService) {
        this.couponService = couponService;
    }


    @Autowired
    public void setParkingLotSettingService(ParkingLotSettingService parkingLotSettingService) {
        this.parkingLotSettingService = parkingLotSettingService;
    }
}
