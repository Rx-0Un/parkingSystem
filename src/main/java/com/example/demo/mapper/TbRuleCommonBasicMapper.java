package com.example.demo.mapper;

import com.example.demo.entity.TbRuleCommonBasic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TbRuleCommonBasicMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_rule_common_basic
     *
     * @mbg.generated Wed Apr 08 08:16:45 GMT+08:00 2020
     */
    int insert(TbRuleCommonBasic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_rule_common_basic
     *
     * @mbg.generated Wed Apr 08 08:16:45 GMT+08:00 2020
     */
    int insertSelective(TbRuleCommonBasic record);


    List<TbRuleCommonBasic> selectAll();

    TbRuleCommonBasic selectRowById(@Param("id") String id);
}