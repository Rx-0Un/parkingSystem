package com.example.demo.rule;

import com.example.demo.bean.InterimCharge;
import com.example.demo.bean.ResultBean;
import com.example.demo.util.DateUtil;
import com.example.demo.util.RuleUtil;

import java.util.Date;

public class InterimRule extends BasicRule {
    Date starting_date;//收费计时开始时间
    Date ending_date;//收费计时结束时间
    InterimCharge interimCharge;

    /**
     * @param money
     * @param rule_type
     * @param use_date  　收费规则使用日期
     * @param date      date为当前时间
     * @param date1     date1为本次收费计时的第一个小时
     */
    public InterimRule(String car_type, float money, String rule_type, Date use_date, Date date, Date date1) {
        super(DateUtil.getTimeByDate(use_date, 0), DateUtil.getTimeByDate(use_date, 24));
        interimCharge = new InterimCharge(car_type, money, rule_type, use_date);
        initDate(use_date);
        //如果当前的收费类型为1
        //且开始时间和结束时间相隔一个小时
        //且当前时间处于收费刚刚开始的一个小时内　则进行收费
        if (Integer.parseInt(interimCharge.getRule_type()) == 1 &&
                DateUtil.countHour(this.starting_date, this.ending_date) == 0
                && date.getTime() == date1.getTime()) {
            countTotal(this.starting_date);
        } else {
            initMap(date);
        }
    }

    public void initDate(Date date) {
        switch (Integer.parseInt(interimCharge.getRule_type())) {
            case RuleUtil.RULE_INTERIM_CHARGE_TYPE_ONE:
            case RuleUtil.RULE_INTERIM_CHARGE_TYPE_TWO:
            case RuleUtil.RULE_INTERIM_CHARGE_TYPE_THREE:
                starting_date = DateUtil.getLastChargeDate(date);
                ending_date = DateUtil.getNextChargeDate(date);
                break;
            case RuleUtil.RULE_INTERIM_CHARGE_TYPE_FOUR:
                starting_date = DateUtil.getTimeByDate(date, 0);
                ending_date = DateUtil.getTimeByDate(date, 24);
                break;
        }
    }

    public void initMap(Date date) {
        //判断传入的时间是否在规则时间范围内
        if (date.getTime() > starting_date.getTime()
                && date.getTime() < ending_date.getTime()) {
            countTotal(date);
            initMap(DateUtil.getNextHourDate(date));
        }
    }

    public void countTotal(Date date) {
        total += interimCharge.getMoney();
        dailyMap.add(new ResultBean(date, DateUtil.getNextHourDate(date), total));
    }
}
