package com.example.demo.test;

import com.example.demo.entity.TbParkingLot;
import com.example.demo.entity.TbRuleCommonBasic;
import com.example.demo.mapper.TbParkingLotMapper;
import com.example.demo.mapper.TbRuleCommonBasicMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class ParkingLotMapperTest extends BaseMapperTest {
    @Test
    public void addRowByInfoTest() {
        SqlSession sqlSession = getSqlSession();
        try {
            TbParkingLotMapper tbParkingLotMapper = sqlSession.getMapper(TbParkingLotMapper.class);
            int count = tbParkingLotMapper.addRowByInfo("停车场", 100, "一级区域", "111111", "1", "1111");
            System.out.println(count);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
    @Test
    public void updateRowByInfoTest() {
        SqlSession sqlSession = getSqlSession();
        try {
            TbParkingLotMapper tbParkingLotMapper = sqlSession.getMapper(TbParkingLotMapper.class);
            int count = tbParkingLotMapper.updateRowByInfo("停车场", 1000, "一级区域", "111111", "1", "1111");
            System.out.println(count);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void selectRowById(){
        SqlSession sqlSession = getSqlSession();
        try {
            TbParkingLotMapper tbParkingLotMapper = sqlSession.getMapper(TbParkingLotMapper.class);
            List<TbParkingLot> list= tbParkingLotMapper.selectRowById(2);
            System.out.println(list.get(0).toString());
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
}
