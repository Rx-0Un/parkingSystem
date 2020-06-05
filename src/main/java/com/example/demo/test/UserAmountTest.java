package com.example.demo.test;

import com.example.demo.entity.TbUser;
import com.example.demo.mapper.TbRuleFixedParkingMapper;
import com.example.demo.mapper.TbStaffMapper;
import com.example.demo.mapper.TbUserAmountMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class UserAmountTest extends BaseMapperTest {
    @Test
    public void selectRowByIdAll() {
        SqlSession sqlSession = getSqlSession();
        try {
            TbUserAmountMapper tbUserAmountMapper = sqlSession.getMapper(TbUserAmountMapper.class);
            int count = tbUserAmountMapper.selectRowById("1");
            System.out.println(count);
        } finally {
            sqlSession.close();
        }

    }

    @Test
    public void selectMoneyByInfoAll() {
        SqlSession sqlSession = getSqlSession();
        try {
            TbUserAmountMapper tbUserAmountMapper = sqlSession.getMapper(TbUserAmountMapper.class);
            float count = tbUserAmountMapper.selectMoneyByInfo("1");
            System.out.println(count);
        } finally {
            sqlSession.close();
        }

    }
    @Test
    public void addRowByCarTypeTest() {
        SqlSession sqlSession = getSqlSession();
        try {
            TbUserAmountMapper tbUserAmountMapper = sqlSession.getMapper(TbUserAmountMapper.class);
            int count = tbUserAmountMapper.addRowByInfo("2",50);
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
            TbUserAmountMapper tbUserAmountMapper = sqlSession.getMapper(TbUserAmountMapper.class);
            int count = tbUserAmountMapper.updateRowByInfo("22",50);
            System.out.println(count);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
}
