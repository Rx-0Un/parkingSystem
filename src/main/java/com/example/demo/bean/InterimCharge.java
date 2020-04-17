package com.example.demo.bean;

import com.example.demo.rule.BasicRule;
import com.example.demo.util.DateUtil;

import java.util.Date;

public class InterimCharge extends BasicCharge {
    float money;
    String rule_type;
    Date use_date;

    public InterimCharge(String car_type, float money, String rule_type, Date use_date) {
        super(car_type, rule_type);
        this.money = money;
        this.rule_type = rule_type;
        this.use_date = use_date;

    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
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

}
