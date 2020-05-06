package com.example.demo.bean;

import com.example.demo.util.DateUtil;

import java.util.Date;

public class ResultBean {
    Date date;
    Date date1;
    float total;
    String month;
    String year;
    String now_rule;
    String add;

    public ResultBean(Date date, Date date1, float total, String now_rule, String add) {
        this.date = date;
        this.date1 = date1;
        this.total = total;
        this.now_rule = now_rule;
        this.add = add;
    }

    public ResultBean(float total, String month, String year,String now_rule, String add) {
        this.total = total;
        this.month = month;
        this.year = year;
        this.now_rule=now_rule;
        this.add = add;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public String getNow_rule() {
        return now_rule;
    }

    public void setNow_rule(String now_rule) {
        this.now_rule = now_rule;
    }
}
