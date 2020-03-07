package com.example.demo.test;

import com.example.demo.mapper.TbStaffMapper;
import com.example.demo.mapper.TbStaffTaskMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class StaffTaskTest extends BaseMapperTest {
    @Autowired
    TbStaffTaskMapper tbStaffTaskMapper;

    @Test
    public void selectCountByStaffId() {
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
}
