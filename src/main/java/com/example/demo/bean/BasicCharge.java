package com.example.demo.bean;

public class BasicCharge {
    String car_type;//用于记录车型
    String now_rule;//用于记录当前规则

    public BasicCharge(String car_type, String now_rule) {
        this.car_type = car_type;
        this.now_rule = now_rule;
    }

    public String getCar_type() {
        return car_type;
    }

    public void setCar_type(String car_type) {
        this.car_type = car_type;
    }

    public String getNow_rule() {
        return now_rule;
    }

    public void setNow_rule(String now_rule) {
        this.now_rule = now_rule;
    }
}
