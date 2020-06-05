package com.example.demo.bean.app;

import com.example.demo.entity.TbCar;

import java.util.List;

public class CarData {
    List<TbCar> list;

    public CarData(List<TbCar> list) {
        this.list = list;
    }

    public List<TbCar> getList() {
        return list;
    }

    public void setList(List<TbCar> list) {
        this.list = list;
    }
}
