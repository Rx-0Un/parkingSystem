package com.example.demo.mapper;

import com.example.demo.entity.TbUserCoupon;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbUserCouponMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_coupon
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    int insert(TbUserCoupon record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_coupon
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    int insertSelective(TbUserCoupon record);
}