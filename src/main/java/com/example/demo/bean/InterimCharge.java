package com.example.demo.bean;

import com.example.demo.rule.BasicRule;
import com.example.demo.util.DateUtil;

import java.util.Date;

public class InterimCharge extends BasicCharge {

    String rule_type;//
    Date use_date;
    float allDay;
    float peakFirstHour;
    float peakHour;
    float plainHour;
    float topMoney;

    public InterimCharge(String car_type, String now_rule,
                         String rule_type, Date use_date,
                         float peakFirstHour, float peakHour,
                         float plainHour, float topMoney) {
        super(car_type, now_rule);
        this.rule_type = rule_type;
        this.use_date = use_date;
        this.peakFirstHour = peakFirstHour;
        this.peakHour = peakHour;
        this.plainHour = plainHour;
        this.topMoney = topMoney;
    }

    public InterimCharge(String car_type, String now_rule, String rule_type, Date use_date, float allDay) {
        super(car_type, now_rule);
        this.rule_type = rule_type;
        this.use_date = use_date;
        this.allDay = allDay;
    }

    public String getRule_type() {
        return rule_type;
    }

    public void setRule_type(String rule_type) {
        this.rule_type = rule_type;
    }

    public Date getUse_date() {
        return use_date;
    }

    public void setUse_date(Date use_date) {
        this.use_date = use_date;
    }

    public float getAllDay() {
        return allDay;
    }

    public void setAllDay(float allDay) {
        this.allDay = allDay;
    }

    public float getPeakFirstHour() {
        return peakFirstHour;
    }

    public void setPeakFirstHour(float peakFirstHour) {
        this.peakFirstHour = peakFirstHour;
    }

    public float getPeakHour() {
        return peakHour;
    }

    public void setPeakHour(float peakHour) {
        this.peakHour = peakHour;
    }

    public float getPlainHour() {
        return plainHour;
    }

    public void setPlainHour(float plainHour) {
        this.plainHour = plainHour;
    }

    public float getTopMoney() {
        return topMoney;
    }

    public void setTopMoney(float topMoney) {
        this.topMoney = topMoney;
    }
}
