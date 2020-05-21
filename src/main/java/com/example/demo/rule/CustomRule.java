package com.example.demo.rule;

import com.example.demo.bean.CommonCharge;
import com.example.demo.bean.CustomCharge;
import com.example.demo.bean.ResultBean;
import com.example.demo.util.DateUtil;
import org.springframework.scheduling.annotation.Async;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomRule extends BasicRule {
    public Date FIST_HOUR_TIME;//开始一个小时的时间
    CustomCharge customCharge;//规则
    List<InterimRule> interimRuleList = new ArrayList<>();
    float dayTotal = 0;
    Date dayDate = new Date();
    FixedRule fixedRule;

    public CustomRule(Date STARTING_TIME, Date ENDING_TIME, CustomCharge customCharge, List<InterimRule> interimRuleList, FixedRule fixedRule) {
        super(STARTING_TIME, ENDING_TIME);
        this.customCharge = customCharge;
        this.interimRuleList = interimRuleList;
        this.fixedRule = fixedRule;
        if (DateUtil.countHour(this.STARTING_TIME, this.ENDING_TIME) == 0) {
            countTotal(this.STARTING_TIME);
        } else {
            initDailyMap(this.STARTING_TIME);
        }
    }

    public CustomRule(Date STARTING_TIME, Date ENDING_TIME, CustomCharge customCharge, FixedRule fixedRule) {
        super(STARTING_TIME, ENDING_TIME);
        this.customCharge = customCharge;
        this.fixedRule = fixedRule;
        if (DateUtil.countHour(this.STARTING_TIME, this.ENDING_TIME) == 0) {
            countTotal(this.STARTING_TIME);
        } else {
            initDailyMap(this.STARTING_TIME);
        }
    }

    public CustomRule(Date STARTING_TIME, Date ENDING_TIME, CustomCharge customCharge, List<InterimRule> interimRuleList) {
        super(STARTING_TIME, ENDING_TIME);
        this.customCharge = customCharge;
        this.interimRuleList = interimRuleList;
        this.FIST_HOUR_TIME = STARTING_TIME;
        if (DateUtil.countHour(this.STARTING_TIME, this.ENDING_TIME) == 0 && searchInterimRule(this.STARTING_TIME) != null) {
            countTotal(this.STARTING_TIME);
        } else {
            initDailyMap(this.STARTING_TIME);
        }
    }

    public CustomRule(Date STARTING_TIME, Date ENDING_TIME, CustomCharge customCharge) {
        super(STARTING_TIME, ENDING_TIME);
        this.customCharge = customCharge;
        this.FIST_HOUR_TIME = STARTING_TIME;
        if (DateUtil.countHour(this.STARTING_TIME, this.ENDING_TIME) == 0) {
            countTotal(this.STARTING_TIME);
        } else {
            initDailyMap(this.STARTING_TIME);
        }
    }

    @Async
    public void initDailyMap(Date date) {
        if (fixedRule != null) {
            fixedRule.initMap(STARTING_TIME, ENDING_TIME);
            dailyMap.addAll(fixedRule.getDailyMap());
            monthMap.addAll(fixedRule.getMonthMap());
            yearLMap.addAll(fixedRule.getYearLMap());
            date = fixedRule.ENDING_TIME;
        }
        Date date1 = DateUtil.getNextDayDate(date);
        Date date2 = DateUtil.getTimeByDate(date1, 0);
        dayDate = date2;
        while (date.getTime() < ENDING_TIME.getTime()) {
            InterimRule interimRule = searchInterimRule(date);
            if (interimRuleList.size() > 0 && interimRule != null) {
                interimRule.initMap(date, STARTING_TIME, ENDING_TIME, total);
                total += interimRule.total;
                dailyMap.addAll(interimRule.getDailyMap());
                monthMap.addAll(interimRule.getMonthMap());
                date = DateUtil.addDay(date, this.ENDING_TIME);
            } else {
                if (date.getTime() >= dayDate.getTime()) {
                    dayTotal = 0;
                    dayDate = DateUtil.getTimeByDate(DateUtil.getNextDayDate(date), 0);
                }
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
    public void countTotal(Date date) {
        float addTotal = this.total;
        if (DateUtil.isPeak(date)) {
            if (date.getTime() == FIST_HOUR_TIME.getTime()) {
                total += addTotal(customCharge.getPeakFirstHour());
            } else {
                total += addTotal(customCharge.getPeakHour());
            }
        } else {
            total += addTotal(customCharge.getPlainHour());
        }
        addTotal = total - addTotal;
        dayTotal += addTotal;
        dailyMap.add(new ResultBean(date, addDate1(date), total, this.customCharge.getNow_rule(), String.valueOf(addTotal)));
        initMonthMap(new ResultBean(this.total, DateUtil.dateFormatMonth(date), "", this.customCharge.getNow_rule(), String.valueOf(addTotal)));
        initYearMap(new ResultBean(this.total, "", DateUtil.dateFormatYear(date), this.customCharge.getNow_rule(), String.valueOf(addTotal)));
    }

    public float addTotal(float add) {
        if (dayTotal + add <= customCharge.getTopMoney()) {
            return add;
        }
        return customCharge.getTopMoney() - dayTotal;
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
    }

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
