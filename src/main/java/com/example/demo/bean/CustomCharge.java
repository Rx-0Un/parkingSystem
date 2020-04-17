package com.example.demo.bean;

public class CustomCharge extends BasicCharge {

    float peakFirstHour;
    float peakHour;
    float plainHour;
    float topMoney;

    public CustomCharge(String car_type, String now_rule, float peakFirstHour, float peakHour, float plainHour, float topMoney) {
        super(car_type, now_rule);
        this.peakFirstHour = peakFirstHour;
        this.peakHour = peakHour;
        this.plainHour = plainHour;
        this.topMoney = topMoney;
    }

    @Override
    public String toString() {
        return "CustomCharge{" +
                "peakFirstHour=" + peakFirstHour +
                ", peakHour=" + peakHour +
                ", plainHour=" + plainHour +
                ", topMoney=" + topMoney +
                ", car_type='" + car_type + '\'' +
                ", now_rule='" + now_rule + '\'' +
                '}';
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
