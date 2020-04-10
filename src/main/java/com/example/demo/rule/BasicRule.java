package com.example.demo.rule;

import com.example.demo.bean.ResultBean;

import java.util.ArrayList;
import java.util.List;

public class BasicRule {
    List<ResultBean> dailyMap = new ArrayList<>();//用于统计一天每个小时的收入
    List<ResultBean> monthMap = new ArrayList<>();//用于统计一个月每天的收入
    List<ResultBean> yearLMap = new ArrayList<>();//用于统计一年每个月的收入
    public float total = 0;//总金额

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public List<ResultBean> getDailyMap() {
        return dailyMap;
    }

    public void setDailyMap(List<ResultBean> dailyMap) {
        this.dailyMap = dailyMap;
    }

    public List<ResultBean> getMonthMap() {
        return monthMap;
    }

    public void setMonthMap(List<ResultBean> monthMap) {
        this.monthMap = monthMap;
    }

    public List<ResultBean> getYearLMap() {
        return yearLMap;
    }

    public void setYearLMap(List<ResultBean> yearLMap) {
        this.yearLMap = yearLMap;
    }
}
