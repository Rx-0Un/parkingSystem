package com.example.demo.bean.app;

import com.example.demo.entity.TbUser;

public class MyPageBean {
    float myBalance;
    int mySpace;
    int myCoupon;
    TbUser tbUser;

    public MyPageBean(float myBalance, int mySpace, int myCoupon, TbUser tbUser) {
        this.myBalance = myBalance;
        this.mySpace = mySpace;
        this.myCoupon = myCoupon;
        this.tbUser = tbUser;
    }

    public TbUser getTbUser() {
        return tbUser;
    }

    public void setTbUser(TbUser tbUser) {
        this.tbUser = tbUser;
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

    public void setMySpace(int mySpace) {
        this.mySpace = mySpace;
    }

    public float getMyCoupon() {
        return myCoupon;
    }

    public void setMyCoupon(int myCoupon) {
        this.myCoupon = myCoupon;
    }
}
