package com.example.demo.mapper;

import com.example.demo.entity.TbMonthlyCard;
import org.apache.ibatis.annotations.Mapper;

/**
 * 已经弃用
 */
@Mapper
public interface TbMonthlyCardMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_monthly_card
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    int insert(TbMonthlyCard record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_monthly_card
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    int insertSelective(TbMonthlyCard record);
}