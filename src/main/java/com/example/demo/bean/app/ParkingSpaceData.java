package com.example.demo.bean.app;

import com.example.demo.entity.TbParkingSpace;

import java.util.List;

public class ParkingSpaceData {
    List<TbParkingSpace> list;

    public ParkingSpaceData(List<TbParkingSpace> list) {
        this.list = list;
    }

    public List<TbParkingSpace> getList() {
        return list;
    }

    public void setList(List<TbParkingSpace> list) {
        this.list = list;
    }
}
