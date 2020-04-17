package com.example.demo.service;

import com.example.demo.entity.TbRuleCustom;
import com.example.demo.entity.TbRuleFixedParking;
import com.example.demo.mapper.TbRuleCustomInterimMapper;
import com.example.demo.mapper.TbRuleCustomMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("RuleCustomService")
public class RuleCustomService {
    TbRuleCustomMapper tbRuleCustomMapper;

    public List<TbRuleCustom> selectAll() {
        return tbRuleCustomMapper.selectAll();
    }

    public int addRowByCarType(String car_type, String rule_type, float money, String description) {
        if (tbRuleCustomMapper.selectRowByCarType(car_type, rule_type) == 1) {
            return tbRuleCustomMapper.updateRowByCarType(car_type, rule_type, money, description);
        }
        return tbRuleCustomMapper.addRowByCarType(car_type, rule_type, money, description);
    }

    public List<TbRuleCustom> selectByFurzzyStr(String car_type, String rule_type) {
        if (car_type.equals("全体") && rule_type.equals("全体")) {
            return tbRuleCustomMapper.selectAll();
        }
        return tbRuleCustomMapper.selectByFurzzyStr(car_type, rule_type);
    }

    @Autowired
    public void setTbRuleCustomMapper(TbRuleCustomMapper tbRuleCustomMapper) {
        this.tbRuleCustomMapper = tbRuleCustomMapper;
    }
}
