package com.example.demo.service;

import com.example.demo.entity.TbOrder;
import com.example.demo.mapper.TbOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderService {
    TbOrderMapper tbOrderMapper;

    public int addRowByInfo(TbOrder tbOrder) {
        return tbOrderMapper.addRowByInfo(tbOrder);
    }

//    public float selectTotalCount(String starting_time) {
//        return tbOrderMapper.selectTotalCount(starting_time);
//    }
//
//    public int selectRowCount(String starting_time) {
//        return tbOrderMapper.selectRowCount(starting_time);
//    }

    @Autowired
    public void setTbOrderMapper(TbOrderMapper tbOrderMapper) {
        this.tbOrderMapper = tbOrderMapper;
    }
}
