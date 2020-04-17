package com.example.demo.rule;

import com.example.demo.bean.ResultBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BasicRule {
    List<ResultBean> dailyMap = new ArrayList<>();//用于统计一天每个小时的收入
    List<ResultBean> monthMap = new ArrayList<>();//用于统计一个月每天的收入
    List<ResultBean> yearLMap = new ArrayList<>();//用于统计一年每个月的收入
    public float total = 0;//总金额

    public Date STARTING_TIME;//开始时间
    public Date ENDING_TIME;//结束时间

    public BasicRule(Date STARTING_TIME, Date ENDING_TIME) {
        this.STARTING_TIME = STARTING_TIME;
        this.ENDING_TIME = ENDING_TIME;
    }

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


    public int MonthMapContains(ResultBean resultBean) {
        for (int i = 0; i < monthMap.size(); i++) {
            if (resultBean.getMonth().equals(monthMap.get(i).getMonth())) {
                return i;
            }
        }
        return -1;
    }

    public int YearMapContains(ResultBean resultBean) {
        for (int i = 0; i < yearLMap.size(); i++) {
            if (resultBean.getYear().equals(yearLMap.get(i).getYear())) {
                return i;
            }
        }
        return -1;
    }
}
