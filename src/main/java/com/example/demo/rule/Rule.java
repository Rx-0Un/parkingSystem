package com.example.demo.rule;

import com.example.demo.bean.CommonCharge;
import com.example.demo.bean.ResultBean;
import com.example.demo.util.DateUtil;
import com.sun.org.apache.bcel.internal.generic.ARETURN;

import java.util.*;

public class Rule extends BasicRule {
    public Date STARTING_TIME;//开始时间
    public Date ENDING_TIME;//结束时间
    public Date FIST_HOUR_TIME;//开始一个小时的时间
    CommonCharge basicCharge;//基本规则

    public Rule(Date STARTING_TIME, Date ENDING_TIME, CommonCharge basicCharge) {
        this.STARTING_TIME = STARTING_TIME;
        this.FIST_HOUR_TIME = DateUtil.getNextHourDate(STARTING_TIME);
        this.ENDING_TIME = ENDING_TIME;
        this.basicCharge = basicCharge;
        if (DateUtil.countHour(this.STARTING_TIME, this.ENDING_TIME) == 0) {
            countTotal(this.STARTING_TIME);
        } else {
            initDailyMap(this.STARTING_TIME);
        }
    }

    private void initDailyMap(Date date) {
        if (date.getTime() <= ENDING_TIME.getTime()) {
            countTotal(date);
            initDailyMap(DateUtil.getNextHourDate(date));
        }
    }

    /**
     * 根据当前时间进行一次计费统计
     *
     * @param date
     */
    public void countTotal(Date date) {
        if (DateUtil.isWeekday(date)) {
            if (date.getTime() == FIST_HOUR_TIME.getTime()) {
                total += basicCharge.firstHour;
                dailyMap.add(new ResultBean(date, addDate1(date), total));
            } else {
                total += basicCharge.plainHour;
                dailyMap.add(new ResultBean(date, addDate1(date), total));
            }
        } else {
            if (DateUtil.isPeak(date)) {
                if (date.getTime() == FIST_HOUR_TIME.getTime()) {
                    total += basicCharge.workFirstHour;
                    dailyMap.add(new ResultBean(date, addDate1(date), total));
                } else {
                    total += basicCharge.workPeakHour;
                    dailyMap.add(new ResultBean(date, addDate1(date), total));
                }
            } else {
                total += basicCharge.workPlainHour;
                dailyMap.add(new ResultBean(date, addDate1(date), total));
            }
        }
    }

    public Date addDate1(Date date) {
        if (DateUtil.getNextHourDate(date).getTime() > this.ENDING_TIME.getTime()) {
            return this.ENDING_TIME;
        }
        return DateUtil.getNextHourDate(date);
    }
}


