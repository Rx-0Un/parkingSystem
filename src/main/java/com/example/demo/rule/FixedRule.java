package com.example.demo.rule;

import com.example.demo.bean.BasicCharge;
import com.example.demo.bean.CommonCharge;
import com.example.demo.bean.ResultBean;
import com.example.demo.util.DateUtil;
import org.springframework.scheduling.annotation.Async;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FixedRule extends BasicRule {
    Date expireDate;
    BasicCharge basicCharge;

    public FixedRule(Date expireDate, BasicCharge basicCharge) {
        super(expireDate, expireDate);
        this.expireDate = expireDate;
        this.basicCharge = basicCharge;
        dailyMap = new ArrayList<>();
        monthMap = new ArrayList<>();
        yearLMap = new ArrayList<>();
    }

    public void initMap(Date STARTING_TIME, Date ENDING_TIME) {
        this.STARTING_TIME = STARTING_TIME;
        this.ENDING_TIME = ENDING_TIME.getTime() > expireDate.getTime() ? expireDate : ENDING_TIME;
        Date date = STARTING_TIME;
        while (date.getTime() < ENDING_TIME.getTime() && date.getTime() < expireDate.getTime()) {
            countTotal(date);
            date = addDate(date);
        }
    }

    public Date addDate(Date date) {
        if (DateUtil.getNextHourDate(date).getTime() > this.ENDING_TIME.getTime()) {
            return this.ENDING_TIME;
        }
        return DateUtil.getNextHourDate(date);
    }

    public void countTotal(Date date) {
        total = 0;
        dailyMap.add(new ResultBean(date, addDate(date), total, this.basicCharge.getNow_rule(), String.valueOf(total)));
        initMonthMap(new ResultBean(this.total, DateUtil.dateFormatMonth(date), "", this.basicCharge.getNow_rule(), String.valueOf(total)));
        initYearMap(new ResultBean(this.total, "", DateUtil.dateFormatYear(date), this.basicCharge.getNow_rule(), String.valueOf(total)));
    }

    @Async
    public void initMonthMap(ResultBean resultBean) {
        if (MonthMapContains(resultBean) == -1) {
            monthMap.add(resultBean);
        } else {
            monthMap.set(MonthMapContains(resultBean), resultBean);
        }
    }

    @Async
    public void initYearMap(ResultBean resultBean) {
        if (YearMapContains(resultBean) == -1) {
            yearLMap.add(resultBean);
        } else {
            yearLMap.set(YearMapContains(resultBean), resultBean);
        }
    }

}
