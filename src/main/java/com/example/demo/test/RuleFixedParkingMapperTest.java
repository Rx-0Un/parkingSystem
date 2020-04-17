package com.example.demo.test;

import com.example.demo.entity.TbRuleFixedParking;
import com.example.demo.entity.TbRulePerson;
import com.example.demo.mapper.TbRuleFixedParkingMapper;
import com.example.demo.mapper.TbRulePersonMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class RuleFixedParkingMapperTest extends BaseMapperTest {
    @Test
    public void selectAllTest() {
        SqlSession sqlSession = getSqlSession();
        try {
            TbRuleFixedParkingMapper tbRuleFixedParkingMapper = sqlSession.getMapper(TbRuleFixedParkingMapper.class);
            List<TbRuleFixedParking> list = tbRuleFixedParkingMapper.selectAll();
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).toString());
            }
        } finally {
            sqlSession.close();
        }
    }
    @Test
    public void selectRowByCarTypeTest() {
        SqlSession sqlSession = getSqlSession();
        try {
            TbRuleFixedParkingMapper tbRuleFixedParkingMapper = sqlSession.getMapper(TbRuleFixedParkingMapper.class);
            int count = tbRuleFixedParkingMapper.selectRowByCarType("01", "一个月");
            System.out.println(count);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void addRowByCarTypeTest() {
        SqlSession sqlSession = getSqlSession();
        try {
            TbRuleFixedParkingMapper tbRuleFixedParkingMapper = sqlSession.getMapper(TbRuleFixedParkingMapper.class);
            int count = tbRuleFixedParkingMapper.addRowByCarType("小车", "一个月",1000,"我觉得不行");
            System.out.println(count);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void updateRowByCarTypeTest() {
        SqlSession sqlSession = getSqlSession();
        try {
            TbRuleFixedParkingMapper tbRuleFixedParkingMapper = sqlSession.getMapper(TbRuleFixedParkingMapper.class);
            int count = tbRuleFixedParkingMapper.updateRowByCarType("小车", "一个月",1000,"1000");
            System.out.println(count);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
}
