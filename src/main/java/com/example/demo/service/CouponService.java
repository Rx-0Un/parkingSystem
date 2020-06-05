package com.example.demo.service;

import com.example.demo.entity.TbCoupon;
import com.example.demo.mapper.TbCouponMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CouponService {
    TbCouponMapper tbCouponMapper;

    public int addRowByInfo(float coupon_amount, int coupon_count, String coupon_location, int coupon_usage_count, String starting_time, String ending_time, String coupon_situation) {
        return tbCouponMapper.addRowByInfo(coupon_amount, coupon_count, coupon_location, coupon_usage_count, starting_time, ending_time, coupon_situation);
    }

    public List<TbCoupon> selectAll(int pageNum, int page) {
        return tbCouponMapper.selectAll(pageNum, page);
    }

    public List<TbCoupon> selectCouponByStr(String amount, String starting_time, String ending,int pageNum, int page) {
        return tbCouponMapper.selectCouponByStr(amount, starting_time, ending, pageNum, page);
    }



    @Autowired
    public void setTbCouponMapper(TbCouponMapper tbCouponMapper) {
        this.tbCouponMapper = tbCouponMapper;
    }
}
