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

    public int selectRowByInfo(String rule_type, String car_type, String use_date) {
        return tbRuleCustomInterimMapper.selectRowByInfo(rule_type, car_type, use_date);
    }

    public List<TbRuleCustomInterim> selectRowByPage(String rule_type, String car_type, String use_date, String money, int pageNum, int page) {
        if (rule_type.equals("全体") && car_type.equals("全体") && use_date.isEmpty() && money.isEmpty()) {
            return tbRuleCustomInterimMapper.selectAll(pageNum, page);
        }
        return tbRuleCustomInterimMapper.selectRowByPage(rule_type, car_type, use_date, money, pageNum, page);
    }

    public int addRowByCarType(String rule_type, float money, String car_type, String use_date) {
        if (selectRowByInfo(rule_type, car_type, use_date) != 1) {
            return tbRuleCustomInterimMapper.addRowByCarType(rule_type, money, car_type, use_date);
        }
        return tbRuleCustomInterimMapper.updateRowByCarType(rule_type, money, car_type, use_date);
    }

    public TbRuleCustomInterim selectRowByDate(Date date, String car_type) throws NullPointerException {
        return tbRuleCustomInterimMapper.selectRowByDate(date, car_type);
    }

    /**
     * 查看是否全天收费规则是否存在
     *
     * @param date
     * @return
     */
    public int selectAllExist(String date) {
        return tbRuleCustomInterimMapper.selectAllExist(date);
    }

    /**
     * 判断当天是否有临时记录
     *
     * @param date
     * @return
     */
    public int selectRuleExist(String date) {
        return tbRuleCustomInterimMapper.selectRuleExist(date);
    }

    public Float selectMoneyByPeakFirst(String date, String car_type) {
        return tbRuleCustomInterimMapper.selectMoneyByPeakFirst(date, car_type);
    }

    public Float selectMoneyByPeak(String date, String car_type) {
        return tbRuleCustomInterimMapper.selectMoneyByPeak(date, car_type);
    }

    public Float selectMoneyByPlain(String date, String car_type) {
        return tbRuleCustomInterimMapper.selectMoneyByPlain(date, car_type);
    }

    public Float selectMoneyByAll(String date, String car_type) {
        return tbRuleCustomInterimMapper.selectMoneyByAll(date, car_type);
    }

    public Float selectMoneyByInfo(String date, String rule_type, String car_type) {
        return tbRuleCustomInterimMapper.selectMoneyByInfo(date, rule_type, car_type);
    }

    @Autowired
    public void setTbRuleCustomInterimMapper(TbRuleCustomInterimMapper tbRuleCustomInterimMapper) {
        this.tbRuleCustomInterimMapper = tbRuleCustomInterimMapper;
    }
}
