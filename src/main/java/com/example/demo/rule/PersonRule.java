package com.example.demo.rule;

import com.example.demo.bean.PersonCharge;
import com.example.demo.bean.ResultBean;
import com.example.demo.util.DateUtil;
import org.springframework.scheduling.annotation.Async;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonRule extends BasicRule {
    PersonCharge personCharge;
    List<InterimRule> interimRuleList = new ArrayList<>();

    public PersonRule(Date STARTING_TIME, Date ENDING_TIME, PersonCharge personCharge, List<InterimRule> interimRuleList) {
        super(STARTING_TIME, ENDING_TIME);
        this.personCharge = personCharge;
        this.interimRuleList = interimRuleList;
        initMap(this.STARTING_TIME);
    }

    public PersonRule(Date STARTING_TIME, Date ENDING_TIME, PersonCharge personCharge) {
        super(STARTING_TIME, ENDING_TIME);
        this.personCharge = personCharge;
        initMap(this.STARTING_TIME);
    }

    public void initMap(Date date) {
        InterimRule interimRule = null;
        while (date.getTime() < this.ENDING_TIME.getTime()) {
            if (searchInterimRule(date) != null) {
                interimRule = searchInterimRule(date);
                interimRule.initMap(date, STARTING_TIME, ENDING_TIME, total);
                total = interimRule.total;
                dailyMap.addAll(interimRule.getDailyMap());
                monthMap.addAll(interimRule.getMonthMap());
                date = DateUtil.addDay(date, this.ENDING_TIME);
            } else {
                countTotal(date, DateUtil.addDay(date, this.ENDING_TIME));
                date = DateUtil.addDay(date, this.ENDING_TIME);
            }

        }
    }

    public void countTotal(Date date, Date nextDay) {
        total += personCharge.getMoney();
        while (date.getTime() < nextDay.getTime()) {
            if (date.getTime() != this.STARTING_TIME.getTime()) {
                dailyMap.add(new ResultBean(date, DateUtil.getNextHourDate(date), total, personCharge.getNow_rule(), String.valueOf(personCharge.getMoney())));
            }
            date = DateUtil.getNextHourDate(date);
        }
        initMonthMap(new ResultBean(total, DateUtil.dateFormatMonth(date), "", personCharge.getNow_rule(), String.valueOf(personCharge.getMoney())));
        initYearMap(new ResultBean(total, "", DateUtil.dateFormatYear(date), personCharge.getNow_rule(), String.valueOf(personCharge.getMoney())));
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

    @Async
    public InterimRule searchInterimRule(Date date) {
        date = DateUtil.getTimeByDate(date, 0);
        if (interimRuleList.size() > 0) {
            for (InterimRule interimRule : interimRuleList) {
                if (interimRule.use_date.getTime() == date.getTime()) {
                    return interimRule;
                }
            }
        }
        return null;
    }
}
