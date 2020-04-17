package com.example.demo.bean;

import com.example.demo.util.DateUtil;

import java.util.Date;

public class ResultBean {
    Date date;
    Date date1;
    float total;
    String month;
    String year;

    public ResultBean(Date date, Date date1, float total) {
        this.date = date;
        this.date1 = date1;
        this.total = total;
        this.month = DateUtil.dateFormatMonth(date);
        this.year = DateUtil.dateFormatYear(date);
    }

    public ResultBean(float total, String month, String year) {
        this.total = total;
        this.month = month;
        this.year = year;
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
}
