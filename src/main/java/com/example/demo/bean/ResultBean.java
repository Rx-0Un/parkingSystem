package com.example.demo.bean;

import com.example.demo.util.DateUtil;

import java.util.Date;

public class ResultBean {
    Date date;
    Date date1;
    float total;

    public ResultBean(Date date, Date date1, float total) {
        this.date = date;
        this.date1 = date1;
        this.total = total;
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
