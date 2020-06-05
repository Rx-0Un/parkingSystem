package com.example.demo.controller;

import com.example.demo.entity.TbCoupon;
import com.example.demo.mapper.TbCouponMapper;
import com.example.demo.mapper.TbParkingLotMapper;
import com.example.demo.service.CouponService;
import com.example.demo.service.ParkingLotSettingService;
import com.example.demo.service.ParkingRecordService;
import com.example.demo.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/index-coupon-manage")
public class CouponManageController {
    CouponService couponService;
    ParkingLotSettingService parkingLotSettingService;

    @RequestMapping(value = "/addNewCoupon")
    public String addNewCoupon(Model model, @RequestBody Map<String, String> map) {
        String user_time = map.get("user_time");
        String usage_count = map.get("usage_count");
        String coupon_money = map.get("coupon_money");
        String starting_time = DateUtil.processDateToString(DateUtil.process(map.get("starting_time")));
        String ending_time = DateUtil.processDateToString(DateUtil.process(map.get("ending_time")));
        couponService.addRowByInfo(Float.parseFloat(coupon_money), Integer.parseInt(usage_count),
                parkingLotSettingService.selectRowById(2).get(0).getParkingLotName(), Integer.parseInt(user_time),
                starting_time, ending_time, "");
        List<TbCoupon> list = couponService.selectAll(10, 0);
        model.addAttribute("CouponResult", list);
        model.addAttribute("NowPage", 1);
        model.addAttribute("TotalPage", (list.size() / 10) + 1);
        return "index-coupon-manage::CouponResult";
    }


    @RequestMapping(value = "/selectCouponPage")
    public String selectCouponPage(Model model, @RequestBody Map<String, String> map) {
        String search_ending_time = map.get("search_ending_time");
        String search_starting_time = map.get("search_starting_time");
        String search_coupon_money = map.get("search_coupon_money");
        int pageNum = Integer.valueOf(map.get("pageNum"));
        int page = Integer.valueOf(map.get("page"));
        model.addAttribute("CouponResult", couponService.selectCouponByStr(search_coupon_money, search_starting_time, search_ending_time, pageNum, (page-1)*pageNum));
        List<TbCoupon> list = couponService.selectCouponByStr(search_coupon_money, search_starting_time, search_ending_time, 0, 0);
        model.addAttribute("NowPage", page);
        model.addAttribute("TotalPage", (list.size() / pageNum) + 1);
        return "index-coupon-manage::CouponResult";
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
