package com.example.demo.rule;

import com.example.demo.bean.PersonCharge;
import com.example.demo.bean.ResultBean;
import com.example.demo.util.DateUtil;

import java.util.Date;

public class PersonRule extends BasicRule {
    PersonCharge personCharge;

    public PersonRule(Date STARTING_TIME, Date ENDING_TIME, PersonCharge personCharge) {
        super(STARTING_TIME, ENDING_TIME);
        this.personCharge = personCharge;
        initMap(this.STARTING_TIME);
    }

    public void initMap(Date date) {
        if (date.getTime() < this.ENDING_TIME.getTime()) {
            countTotal(date, DateUtil.getNextDayDate(date));
            initMap(DateUtil.getNextDayDate(date));
        }
    }

    public void countTotal(Date date, Date nextDay) {
        total += personCharge.getMoney();
        while (date.getTime() < nextDay.getTime()) {
            dailyMap.add(new ResultBean(date, DateUtil.getNextHourDate(date), total));
            date = DateUtil.getNextHourDate(date);
        }
        initMonthMap(new ResultBean(total, DateUtil.dateFormatMonth(date), ""));

        initYearMap(new ResultBean(total, "", DateUtil.dateFormatYear(date)));
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
