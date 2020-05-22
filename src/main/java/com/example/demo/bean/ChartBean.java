package com.example.demo.bean;

import java.util.Date;

public class ChartBean {
    long date;
    int i;

    public ChartBean(long date, int i) {
        this.date = date;
        this.i = i;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}
