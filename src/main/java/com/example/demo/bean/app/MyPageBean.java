package com.example.demo.bean.app;

public class MyPageBean {
    float myBalance;
    float mySpace;
    float myCoupon;

    public MyPageBean(float myBalance, float mySpace, float myCoupon) {
        this.myBalance = myBalance;
        this.mySpace = mySpace;
        this.myCoupon = myCoupon;
    }

    public float getMyBalance() {
        return myBalance;
    }

    public void setMyBalance(float myBalance) {
        this.myBalance = myBalance;
    }

    public float getMySpace() {
        return mySpace;
    }

    public void setMySpace(float mySpace) {
        this.mySpace = mySpace;
    }

    public float getMyCoupon() {
        return myCoupon;
    }

    public void setMyCoupon(float myCoupon) {
        this.myCoupon = myCoupon;
    }
}
