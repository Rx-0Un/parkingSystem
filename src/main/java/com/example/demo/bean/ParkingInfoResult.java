package com.example.demo.bean;

import java.util.List;

public class ParkingInfoResult {
    String parkingName;
    String parkingInfo;
    String parkingSpaceNum;
    List<BasicCharge> list1;//公共规则
    List<BasicCharge> list2;//私人规则
    List<BasicCharge> list3;//自定义规则

    public String getParkingName() {
        return parkingName;
    }

    public void setParkingName(String parkingName) {
        this.parkingName = parkingName;
    }

    public String getParkingInfo() {
        return parkingInfo;
    }

    public void setParkingInfo(String parkingInfo) {
        this.parkingInfo = parkingInfo;
    }

    public String getParkingSpaceNum() {
        return parkingSpaceNum;
    }

    public void setParkingSpaceNum(String parkingSpaceNum) {
        this.parkingSpaceNum = parkingSpaceNum;
    }

    public List<BasicCharge> getList1() {
        return list1;
    }

    public void setList1(List<BasicCharge> list1) {
        this.list1 = list1;
    }

    public List<BasicCharge> getList2() {
        return list2;
    }

    public void setList2(List<BasicCharge> list2) {
        this.list2 = list2;
    }

    public List<BasicCharge> getList3() {
        return list3;
    }

    public void setList3(List<BasicCharge> list3) {
        this.list3 = list3;
    }
}
