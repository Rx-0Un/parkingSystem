package com.example.demo.test;

import com.example.demo.entity.TbOrder;
import com.example.demo.entity.TbParkingRecord;
import com.example.demo.mapper.TbOrderMapper;
import com.example.demo.mapper.TbParkingLotMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class OrderMapperTest extends BaseMapperTest {
    @Test
    public void addRowByInfoTest() {
        SqlSession sqlSession = getSqlSession();
        try {
            TbOrderMapper TbOrderMapper = sqlSession.getMapper(TbOrderMapper.class);
            TbOrder tbOrder = new TbOrder();
            tbOrder.setOrderAmount(new Float(500));
            tbOrder.setOrderPayer("tony1");
            tbOrder.setOrderReceiver("临时车主");
            tbOrder.setOrderState("已完成");
            tbOrder.setOrderPayType("现金");
            tbOrder.setOrderPurchaseType("停车场支付");
            int count = TbOrderMapper.addRowByInfo(tbOrder);
            System.out.println(count);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void selectDutyOrder() {
        SqlSession sqlSession = getSqlSession();
        try {
            TbOrderMapper TbOrderMapper = sqlSession.getMapper(TbOrderMapper.class);
            Float count = TbOrderMapper.selectTotalCount("2020-04-22 13:06:32");
            System.out.println(count);

        } finally {
            sqlSession.close();
        }
    }
    @Test
    public void selectOrderByUserName() {
        SqlSession sqlSession = getSqlSession();
        try {
            TbOrderMapper TbOrderMapper = sqlSession.getMapper(TbOrderMapper.class);
            List<TbOrder>list= TbOrderMapper.selectOrderByUserName("tony");
            System.out.println(list);

        } finally {
            sqlSession.close();
        }
    }
}
