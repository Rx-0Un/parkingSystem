package com.example.demo.rule;

import com.example.demo.bean.InterimCharge;
import com.example.demo.bean.ResultBean;
import com.example.demo.util.DateUtil;
import com.example.demo.util.RuleUtil;
import org.springframework.scheduling.annotation.Async;

import java.util.Date;

public class InterimRule extends BasicRule {
    InterimCharge interimCharge;
    Date use_date;
    private Date nowDate;
    private Date endDate;
    private Date startDate;
    private Float AllTotal;

    public InterimRule(InterimCharge interimCharge, Date use_date) {
        super(DateUtil.getTimeByDate(use_date, 0), DateUtil.getTimeByDate(use_date, 24));
        this.interimCharge = interimCharge;
        this.use_date = use_date;
    }

    /**
     * 三个值为外部传入的时间
     *
     * @param nowDate
     * @param endDate
     * @param startDate
     */
    @Async
    public void initMap(Date nowDate, Date startDate, Date endDate, float total) {
        this.AllTotal = total;
        this.nowDate = nowDate;
        this.endDate = endDate;
        this.startDate = startDate;
        initMap(nowDate);
    }

    @Async
    public void initMap(Date date) {
        if (date.getTime() < this.ENDING_TIME.getTime() && date.getTime() < endDate.getTime()) {
            if ("小时制度".equals(interimCharge.getRule_type())) {
                countHoursTotal(date);
                initMap(addDate(date));
            } else {
                countTotal(date);
            }
        }
    }

    /**
     * 小时制度收费计算
     *
     * @param date
     */
    @Async
    public void countHoursTotal(Date date) {
        float addTotal = total;
        if (DateUtil.isPeak(date)) {
            if (date.getTime() == startDate.getTime()) {
                total += interimCharge.getPeakFirstHour();
            } else {
                total += interimCharge.getPeakHour();
            }
        } else {
            total += interimCharge.getPlainHour();
        }
        if (total>interimCharge.getTopMoney()){
            total=interimCharge.getTopMoney();
        }
        addTotal = total - addTotal;
        dailyMap.add(new ResultBean(date, addDate1(date), AllTotal+total, DateUtil.getNowDate(date) + this.interimCharge.getNow_rule(), String.valueOf(addTotal)));
        initMonthMap(new ResultBean(AllTotal+total, DateUtil.dateFormatMonth(date), "",
                DateUtil.getNowDate(date) + this.interimCharge.getNow_rule(),
                String.valueOf(interimCharge.getAllDay())));
        initYearMap(new ResultBean(AllTotal+total, "", DateUtil.dateFormatYear(date),
                DateUtil.getNowDate(date) + this.interimCharge.getNow_rule(),
                String.valueOf(interimCharge.getAllDay())));
    }

    @Async
    public Date addDate1(Date date) {
        if (DateUtil.getNextHourDate(date).getTime() > this.endDate.getTime()) {
            return this.ENDING_TIME.getTime() > this.endDate.getTime() ? this.endDate : this.ENDING_TIME;
        }
        if (DateUtil.getNextHourDate(date).getTime() > this.ENDING_TIME.getTime()) {
            return this.ENDING_TIME.getTime() > this.endDate.getTime() ? this.endDate : this.ENDING_TIME;
        }
        return DateUtil.getNextHourDate(date);
    }

    @Async
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

    /**
     * 全天制度收费计算
     */
    @Async
    public void countTotal(Date date) {
        this.total += interimCharge.getAllDay();
        this.monthMap.add(new ResultBean(total, DateUtil.dateFormatMonth(date), "", DateUtil.getNowDate(interimCharge.getUse_date()) + this.interimCharge.getNow_rule(), "" + interimCharge.getAllDay()));
        while (date.getTime() < this.endDate.getTime() && date.getTime() < this.ENDING_TIME.getTime()) {
            if (dailyMap.size() == 0) {
                this.dailyMap.add(new ResultBean(date, addDate(date), total, DateUtil.getNowDate(date) + this.interimCharge.getNow_rule(), "" + interimCharge.getAllDay()));
            } else {
                this.dailyMap.add(new ResultBean(date, addDate(date), total, "", ""));
            }
            date = DateUtil.getNextHourDate(date);
        }
    }

    @Async
    public Date addDate(Date date) {
        if (DateUtil.getNextHourDate(date).getTime() > this.ENDING_TIME.getTime()
                || DateUtil.getNextHourDate(date).getTime() > this.endDate.getTime()) {
            return this.ENDING_TIME;
        }
        return DateUtil.getNextHourDate(date);
    }

    @Async
    public Date getUse_date() {
        return use_date;
    }
}
