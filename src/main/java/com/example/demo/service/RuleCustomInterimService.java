package com.example.demo.service;

import com.example.demo.entity.TbRuleCustomInterim;
import com.example.demo.mapper.TbRuleCustomInterimMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("RuleCustomInterimService")
public class RuleCustomInterimService {
    TbRuleCustomInterimMapper tbRuleCustomInterimMapper;

    public List<TbRuleCustomInterim> selectAll(int pageNum, int page) {
        return tbRuleCustomInterimMapper.selectAll(pageNum, page);
    }

    public int selectRowByInfo(String rule_type, String car_type, Date use_date) {
        return tbRuleCustomInterimMapper.selectRowByInfo(rule_type, car_type, use_date);
    }

    public List<TbRuleCustomInterim> selectRowByPage(String rule_type, String car_type, String use_date, String money, int pageNum, int page) {
        if (rule_type.equals("全体") && car_type.equals("全体") && use_date.isEmpty() && money.isEmpty()) {
            return tbRuleCustomInterimMapper.selectAll(pageNum, page);
        }
        return tbRuleCustomInterimMapper.selectRowByPage(rule_type, car_type, use_date, money, pageNum, page);
    }

    public int addRowByCarType(String rule_type, float money, String car_type, Date use_date) {
        if (selectRowByInfo(rule_type, car_type, use_date) != 1) {
            return tbRuleCustomInterimMapper.addRowByCarType(rule_type, money, car_type, use_date);
        }
        return tbRuleCustomInterimMapper.updateRowByCarType(rule_type, money, car_type, use_date);
    }

    @Autowired
    public void setTbRuleCustomInterimMapper(TbRuleCustomInterimMapper tbRuleCustomInterimMapper) {
        this.tbRuleCustomInterimMapper = tbRuleCustomInterimMapper;
    }
}
