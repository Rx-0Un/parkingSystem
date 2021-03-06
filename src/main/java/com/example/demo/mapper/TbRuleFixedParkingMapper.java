package com.example.demo.mapper;

import com.example.demo.entity.TbRuleFixedParking;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TbRuleFixedParkingMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_rule_fixed_parking
     *
     * @mbg.generated Wed Apr 08 08:40:13 GMT+08:00 2020
     */
    int insert(TbRuleFixedParking record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_rule_fixed_parking
     *
     * @mbg.generated Wed Apr 08 08:40:13 GMT+08:00 2020
     */
    int insertSelective(TbRuleFixedParking record);

    List<TbRuleFixedParking> selectAll();

    int addRowByCarType(@Param(value = "car_type") String car_type,@Param(value = "charge_cycle") String charge_cycle,@Param(value = "money") float money,@Param(value = "description") String description);
    int updateRowByCarType(@Param(value = "car_type") String car_type,@Param(value = "charge_cycle") String charge_cycle,@Param(value = "money") float money,@Param(value = "description") String description);
    int selectRowByCarType(@Param(value = "car_type") String car_type,@Param(value = "charge_cycle") String charge_cycle);
    List<TbRuleFixedParking> selectRuleByCarType(@Param(value = "car_type")String carModel);
}