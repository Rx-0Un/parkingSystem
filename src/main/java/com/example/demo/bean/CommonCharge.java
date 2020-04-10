package com.example.demo.bean;

import com.example.demo.rule.BasicRule;

public class CommonCharge extends BasicRule {
    public Boolean isInterim;//是否启用临时规则
    public float workFirstHour;//工作日高峰首个小时的收费额度
    public float workPeakHour; //工作日高峰普通时段收费额度
    public float workPlainHour;//工作日非高峰时段的收费额度
    public float firstHour;    //非工作日首个小时的收费额度
    public float plainHour;    //非工作日普通时段的收费额度

    public CommonCharge(float workFirstHour, float workPeakHour, float workPlainHour, float firstHour, float plainHour) {
        this.workFirstHour = workFirstHour;
        this.workPeakHour = workPeakHour;
        this.workPlainHour = workPlainHour;
        this.firstHour = firstHour;
        this.plainHour = plainHour;
    }

    public float getWorkFirstHour() {
        return workFirstHour;
    }

    public Boolean getInterim() {
        return isInterim;
    }

    public void setInterim(Boolean interim) {
        isInterim = interim;
    }

    public void setWorkFirstHour(float workFirstHour) {
        this.workFirstHour = workFirstHour;
    }

    public float getWorkPeakHour() {
        return workPeakHour;
    }

    public void setWorkPeakHour(float workPeakHour) {
        this.workPeakHour = workPeakHour;
    }

    public float getWorkPlainHour() {
        return workPlainHour;
    }

    public void setWorkPlainHour(float workPlainHour) {
        this.workPlainHour = workPlainHour;
    }

    public float getFirstHour() {
        return firstHour;
    }

    public void setFirstHour(float firstHour) {
        this.firstHour = firstHour;
    }

    public float getPlainHour() {
        return plainHour;
    }

    public void setPlainHour(float plainHour) {
        this.plainHour = plainHour;
    }
}
