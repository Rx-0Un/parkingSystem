package com.example.demo.service;

import com.example.demo.entity.TbOrder;
import com.example.demo.mapper.TbOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderService {
    TbOrderMapper tbOrderMapper;

    public int addRowByInfo(TbOrder tbOrder) {
        return tbOrderMapper.addRowByInfo(tbOrder);
    }

    public float selectTotalCount(String starting_time) {
        return tbOrderMapper.selectTotalCount(starting_time);
    }

    public int selectRowCount(String starting_time) {
        return tbOrderMapper.selectRowCount(starting_time);
    }

    public List<TbOrder> selectAll(int pagNum, int page) {
        return tbOrderMapper.selectAll(pagNum, page);
    }

    public List<TbOrder> selectOrderByUserId(String userId) {
        return tbOrderMapper.selectOrderByUserId(userId);
    }

    public List<TbOrder> selectOrderByStr(String orderPurchaseType, String orderPayType, String orderState, String keyword_title, String keyword, int pageNum, int page) {
        return tbOrderMapper.selectOrderByStr(orderPurchaseType, orderPayType, orderState, keyword_title, keyword, pageNum, page);
    }

    public Integer selectCashOrder() {
        return tbOrderMapper.selectCashOrder();
    }

    public Integer selectPhoneOrder() {
        return tbOrderMapper.selectPhoneOrder();
    }

    @Autowired
    public void setTbOrderMapper(TbOrderMapper tbOrderMapper) {
        this.tbOrderMapper = tbOrderMapper;
    }
}
