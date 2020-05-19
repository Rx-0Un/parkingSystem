package com.example.demo.service;

import com.example.demo.entity.TbRuleFixedParking;
import com.example.demo.mapper.TbRuleFixedParkingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("RuleFixedParkingService")
public class RuleFixedParkingService {
    TbRuleFixedParkingMapper tbRuleFixedParkingMapper;

    public List<TbRuleFixedParking> selectAll() {
        return tbRuleFixedParkingMapper.selectAll();
    }

    public int addRowByCarType(String car_type, String charge_cycle, float money, String description) {
        if (tbRuleFixedParkingMapper.selectRowByCarType(car_type, charge_cycle) == 1) {
            return tbRuleFixedParkingMapper.updateRowByCarType(car_type, charge_cycle, money, description);
        }
        return tbRuleFixedParkingMapper.addRowByCarType(car_type, charge_cycle, money, description);
    }

    public List<TbRuleFixedParking> selectRuleByCarType(String carModel) {
        return tbRuleFixedParkingMapper.selectRuleByCarType(carModel);
    }


    @Autowired
    public void setTbRuleFixedParkingMapper(TbRuleFixedParkingMapper tbRuleFixedParkingMapper) {
        this.tbRuleFixedParkingMapper = tbRuleFixedParkingMapper;
    }
}
