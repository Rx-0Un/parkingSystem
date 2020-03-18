package com.example.demo.test;

import com.example.demo.entity.TbStaffTask;
import com.example.demo.mapper.TbStaffMapper;
import com.example.demo.mapper.TbStaffTaskMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StaffTaskMapperTest extends BaseMapperTest {
    @Autowired
    TbStaffTaskMapper tbStaffTaskMapper;

    @Test
    public void selectCountByStaffIdTest() {
        String name = "tony";
        int count = 0;
        SqlSession sqlSession = getSqlSession();
        try {
            tbStaffTaskMapper = sqlSession.getMapper(TbStaffTaskMapper.class);
            count = tbStaffTaskMapper.selectCountByStaffId("1");

            System.out.println(count);

        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void selectAllTest() {
        SqlSession sqlSession = getSqlSession();
        try {
            tbStaffTaskMapper = sqlSession.getMapper(TbStaffTaskMapper.class);
            List<TbStaffTask> list = tbStaffTaskMapper.selectAll();
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).toString());
            }
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void addTaskTest() {
        SqlSession sqlSession = getSqlSession();
        try {
            tbStaffTaskMapper = sqlSession.getMapper(TbStaffTaskMapper.class);
            int count = tbStaffTaskMapper.addTask("1", "2020-03-18","中午呢");
            System.out.println(count);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
}
