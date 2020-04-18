package com.example.demo.service;

import com.example.demo.entity.TbRulePerson;
import com.example.demo.entity.TbStaffTask;
import com.example.demo.mapper.TbRulePersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("RulePersonService")
public class RulePersonService {
    TbRulePersonMapper tbRulePersonMapper;

    public int selectRowByCarType(String car_type) {
        return tbRulePersonMapper.selectRowByCarType(car_type);
    }

    public float selectMoneyByCarType(String car_type){
        return tbRulePersonMapper.selectMoneyByCarType(car_type);
    }

    public int addRowByCarType(String car_type, float money) {
        if (selectRowByCarType(car_type) != 1) {
            return tbRulePersonMapper.addRowByCarType(car_type, money);
        }
        return tbRulePersonMapper.updateRowByCarType(car_type, money);
    }

    public List<TbRulePerson> selectAll() {
        return tbRulePersonMapper.selectAll();
    }



    @Autowired
    public void setTbRulePersonMapper(TbRulePersonMapper tbRulePersonMapper) {
        this.tbRulePersonMapper = tbRulePersonMapper;
    }
}
