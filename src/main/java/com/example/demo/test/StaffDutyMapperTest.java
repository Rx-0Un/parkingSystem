package com.example.demo.test;

import com.example.demo.entity.TbStaffDuty;
import com.example.demo.mapper.TbOrderMapper;
import com.example.demo.mapper.TbStaffDutyMapper;
import com.example.demo.mapper.TbStaffTaskMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class StaffDutyMapperTest extends BaseMapperTest {
    @Test
    public void selectDutyExistTest() {
        SqlSession sqlSession = getSqlSession();
        try {
            TbStaffDutyMapper tbStaffDutyMapper = sqlSession.getMapper(TbStaffDutyMapper.class);
            int count = tbStaffDutyMapper.selectDutyExist();
            System.out.println(count);
        } finally {
            sqlSession.close();
        }
    }
    @Test
    public void addRowByInfoTest() {
        SqlSession sqlSession = getSqlSession();
        try {
            TbStaffDutyMapper tbStaffDutyMapper = sqlSession.getMapper(TbStaffDutyMapper.class);
            int count = tbStaffDutyMapper.addRowByInfo(1,"2020-03-18 23:53:27",1);
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
            TbStaffDutyMapper tbStaffDutyMapper = sqlSession.getMapper(TbStaffDutyMapper.class);
            int count = tbStaffDutyMapper.updateRowByInfo("2020-03-18 23:53:27");
            System.out.println(count);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
    @Test
    public void selectLastRowTest() {
        SqlSession sqlSession = getSqlSession();
        try {
            TbStaffDutyMapper tbStaffDutyMapper = sqlSession.getMapper(TbStaffDutyMapper.class);
            TbStaffDuty tbStaffDuty = tbStaffDutyMapper.selectLastRow();
            System.out.println(tbStaffDuty.toString());
        } finally {
            sqlSession.close();
        }
    }
}
