package com.example.demo.rule;

import com.example.demo.bean.CommonCharge;
import com.example.demo.bean.ResultBean;
import com.example.demo.util.DateUtil;
import org.springframework.scheduling.annotation.Async;

import java.util.*;

public class Rule extends BasicRule {
    public Date FIST_HOUR_TIME;//开始一个小时的时间
    CommonCharge basicCharge;//基本规则
    List<InterimRule> interimRuleList = new ArrayList<>();

    public Rule(Date STARTING_TIME, Date ENDING_TIME, CommonCharge basicCharge, List<InterimRule> interimRuleList) {
        super(STARTING_TIME, ENDING_TIME);
        this.FIST_HOUR_TIME = STARTING_TIME;
        this.basicCharge = basicCharge;
        this.interimRuleList = interimRuleList;
        initDailyMap(this.STARTING_TIME);
    }

    public Rule(Date STARTING_TIME, Date ENDING_TIME, CommonCharge basicCharge) {
        super(STARTING_TIME, ENDING_TIME);
        this.FIST_HOUR_TIME = STARTING_TIME;
        this.basicCharge = basicCharge;
        if (DateUtil.countHour(this.STARTING_TIME, this.ENDING_TIME) == 0 && searchInterimRule(this.STARTING_TIME) != null) {
            countTotal(this.STARTING_TIME);
        } else {
            initDailyMap(this.STARTING_TIME);
        }
    }

    @Async
    public void initDailyMap(Date date) {
        while (date.getTime() < ENDING_TIME.getTime()) {
            //判断当前时间内是否有临时规则
            if (searchInterimRule(date) != null) {
                InterimRule interimRule = searchInterimRule(date);
                interimRule.initMap(date, STARTING_TIME, ENDING_TIME, total);
                total += interimRule.total;
                dailyMap.addAll(interimRule.getDailyMap());
                monthMap.addAll(interimRule.getMonthMap());
                date = DateUtil.addDay(date, this.ENDING_TIME);
            } else {
                countTotal(date);
                date = addDate1(date);
            }
        }
    }

    /**
     * 根据当前时间进行一次计费统计
     *
     * @param date
     */
    @Async
    public void countTotal(Date date) {
        float addTotal = this.total;
        if (DateUtil.isWeekday(date)) {
            if (date.getTime() == FIST_HOUR_TIME.getTime()) {
                total += basicCharge.firstHour;
            } else {
                total += basicCharge.plainHour;
            }
        } else {
            if (DateUtil.isPeak(date)) {
                if (date.getTime() == FIST_HOUR_TIME.getTime()) {
                    total += basicCharge.workFirstHour;
                } else {
                    total += basicCharge.workPeakHour;
                }
            } else {
                total += basicCharge.workPlainHour;
            }
        }
        addTotal = total - addTotal;
        dailyMap.add(new ResultBean(date, addDate1(date), total, this.basicCharge.getNow_rule(), String.valueOf(addTotal)));
        initMonthMap(new ResultBean(this.total, DateUtil.dateFormatMonth(date), "", this.basicCharge.getNow_rule(), String.valueOf(addTotal)));
        initYearMap(new ResultBean(this.total, "", DateUtil.dateFormatYear(date), this.basicCharge.getNow_rule(), String.valueOf(addTotal)));
    }

    @Async
    public Date addDate1(Date date) {
        if (DateUtil.getNextHourDate(date).getTime() > this.ENDING_TIME.getTime()) {
            return this.ENDING_TIME;
        }
        return DateUtil.getNextHourDate(date);
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


