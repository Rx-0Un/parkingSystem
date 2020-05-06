package com.example.demo.controller.app;

import com.example.demo.bean.app.CouponDate;
import com.example.demo.entity.TbCoupon;
import com.example.demo.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/App/couponManage")
public class AppCouponController {
    CouponService couponService;

    @RequestMapping(value = "/selectCouponByPage")
    @ResponseBody
    public CouponDate selectCouponByPage(String pageNum, String page) {
        CouponDate couponDate = new CouponDate();
        couponDate.setTbCouponList(couponService.selectAll(Integer.valueOf(pageNum), Integer.valueOf(page)));
        return couponDate;
    }

    @Autowired
    public void setCouponService(CouponService couponService) {
        this.couponService = couponService;
    }
}
