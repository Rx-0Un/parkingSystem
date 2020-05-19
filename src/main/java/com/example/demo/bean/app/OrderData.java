package com.example.demo.bean.app;

import com.example.demo.entity.TbOrder;

import java.util.List;

public class OrderData {
    List<TbOrder> list;

    public OrderData(List<TbOrder> list) {
        this.list = list;
    }

    public List<TbOrder> getList() {
        return list;
    }

    public void setList(List<TbOrder> list) {
        this.list = list;
    }
}
