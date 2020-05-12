package com.example.demo.mapper;

import com.example.demo.entity.TbRuleCustom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TbRuleCustomMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_rule_custom
     *
     * @mbg.generated Wed Apr 08 08:16:45 GMT+08:00 2020
     */
    int insert(TbRuleCustom record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_rule_custom
     *
     * @mbg.generated Wed Apr 08 08:16:45 GMT+08:00 2020
     */
    int insertSelective(TbRuleCustom record);

    List<TbRuleCustom> selectAll();

    int addRowByCarType(@Param(value = "car_type") String car_type, @Param(value = "rule_type") String rule_type, @Param(value = "money") float money, @Param(value = "description") String description);

    int updateRowByCarType(@Param(value = "car_type") String car_type, @Param(value = "rule_type") String rule_type, @Param(value = "money") float money, @Param(value = "description") String description);

    int selectRowByCarType(@Param(value = "car_type") String car_type, @Param(value = "rule_type") String rule_type);

    float selectMoneyByInfo(String rule_type, String car_type);

    List<TbRuleCustom> selectByFurzzyStr(@Param(value = "car_type") String car_type, @Param(value = "rule_type") String rule_type);
}