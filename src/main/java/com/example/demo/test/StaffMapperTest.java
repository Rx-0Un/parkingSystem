package com.example.demo.test;

import com.example.demo.entity.TbStaff;
import com.example.demo.mapper.TbStaffMapper;
import com.example.demo.mapper.TbUserMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class StaffMapperTest extends BaseMapperTest {
    @Test
    public void testSelectForLogin() {
        String name = "tony";
        String pwd = "123456";
        int count = 0;
        SqlSession sqlSession = getSqlSession();
        try {
            TbStaffMapper tbStaffMapper = sqlSession.getMapper(TbStaffMapper.class);
            count = tbStaffMapper.selectForLogin(name, pwd);
            if (count == 0) {
                System.out.println(name + "不存在");
            } else {
                System.out.println("success");
            }
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void selectByUserName() {
        String name = "tony";
        int count = 0;
        SqlSession sqlSession = getSqlSession();
        try {
            TbStaffMapper tbStaffMapper = sqlSession.getMapper(TbStaffMapper.class);
            count = tbStaffMapper.selectByUserName(name);
            if (count == 0) {
                System.out.println(name + "不存在");
            } else {
                System.out.println("存在");
            }
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void selectInfoById() {
        SqlSession sqlSession = getSqlSession();
        try {
            TbStaffMapper tbStaffMapper = sqlSession.getMapper(TbStaffMapper.class);
            List<TbStaff> list = tbStaffMapper.selectAll();
            System.out.println(list.size());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void selectByFuzzyStr() {
        SqlSession sqlSession = getSqlSession();
        try {
            TbStaffMapper tbStaffMapper = sqlSession.getMapper(TbStaffMapper.class);
            List<TbStaff> list = tbStaffMapper.selectByFuzzyStr("11");
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).toString());
            }
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void insertStaffTest() {
        SqlSession sqlSession = getSqlSession();
        try {
            TbStaffMapper tbStaffMapper = sqlSession.getMapper(TbStaffMapper.class);
            int count = tbStaffMapper.insertStaff("tom", "19999999999", "111111");
            System.out.println(count);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
    @Test
    public void deleteStaffByIdTest(){
        SqlSession sqlSession = getSqlSession();
        try {
            TbStaffMapper tbStaffMapper = sqlSession.getMapper(TbStaffMapper.class);
            int count = tbStaffMapper.deleteStaffById(22);
            System.out.println(count);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
    @Test
    public void selectAllIdAndNameTest(){
        SqlSession sqlSession = getSqlSession();
        try {
            TbStaffMapper tbStaffMapper = sqlSession.getMapper(TbStaffMapper.class);
            List<Map<String,Object>> list = tbStaffMapper.selectAllIdAndName();
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).toString());
            }
        } finally {
            sqlSession.close();
        }
    }
}
