package com.example.demo.bean;

public class PersonCharge extends BasicCharge {
    private float money;

    public PersonCharge(String car_type, String now_rule, float money) {
        super(car_type, now_rule);
        this.money = money;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "PersonCharge{" +
                "money=" + money +
                ", car_type='" + car_type + '\'' +
                ", now_rule='" + now_rule + '\'' +
                '}';
    }
}
