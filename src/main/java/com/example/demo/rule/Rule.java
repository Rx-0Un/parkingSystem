package com.example.demo.rule;

import com.example.demo.bean.CommonCharge;
import com.example.demo.bean.ResultBean;
import com.example.demo.util.DateUtil;

import java.util.*;

public class Rule extends BasicRule {
    public Date FIST_HOUR_TIME;//开始一个小时的时间
    CommonCharge basicCharge;//基本规则

    public Rule(Date STARTING_TIME, Date ENDING_TIME, CommonCharge basicCharge) {
        super(STARTING_TIME, ENDING_TIME);
        this.FIST_HOUR_TIME = DateUtil.getNextHourDate(STARTING_TIME);
        this.basicCharge = basicCharge;
        if (DateUtil.countHour(this.STARTING_TIME, this.ENDING_TIME) == 0) {
            countTotal(this.STARTING_TIME);
        } else {
            initDailyMap(this.STARTING_TIME);
        }
    }

    private void initDailyMap(Date date) {
        if (date.getTime() < ENDING_TIME.getTime()) {
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
        initMonthMap(new ResultBean(total, DateUtil.dateFormatMonth(date), ""));

        initYearMap(new ResultBean(total, "", DateUtil.dateFormatYear(date)));
    }

    public Date addDate1(Date date) {
        if (DateUtil.getNextHourDate(date).getTime() > this.ENDING_TIME.getTime()) {
            return this.ENDING_TIME;
        }
        return DateUtil.getNextHourDate(date);
    }

    public void initMonthMap(ResultBean resultBean) {
        if (MonthMapContains(resultBean) == -1) {
            monthMap.add(resultBean);
        } else {
            monthMap.set(MonthMapContains(resultBean), resultBean);
        }
        if (MonthMapContains(resultBean) > 0) {
            resultBean.setTotal(resultBean.getTotal() - monthMap.get(MonthMapContains(resultBean) - 1).getTotal());
        }
    }

    public void initYearMap(ResultBean resultBean) {
        if (YearMapContains(resultBean) == -1) {
            yearLMap.add(resultBean);
        } else {
            yearLMap.set(YearMapContains(resultBean), resultBean);
        }
        if (YearMapContains(resultBean) > 0) {
            resultBean.setTotal(resultBean.getTotal() - yearLMap.get(YearMapContains(resultBean) - 1).getTotal());
        }
    }
}


