package com.example.demo.test;

import com.example.demo.entity.TbUser;
import com.example.demo.mapper.TbUserMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class UserMapperTest extends BaseMapperTest {
    @Test
    public void testUserSelectAll() {
        SqlSession sqlSession = getSqlSession();
        try {
            List<TbUser> Users = sqlSession.selectList("com.example.demo.mapper.TbUserMapper.selectAll");
            for (TbUser user : Users) {
                System.out.println(user.getUserName());
            }
        } finally {
            sqlSession.close();
        }
    }
    @Test
    public void testSelectForLogin() {
        String name = "tom";
        String pwd = "123456";
        int count = 0;
        SqlSession sqlSession = getSqlSession();
        try {
            TbUserMapper tbUserMapper = sqlSession.getMapper(TbUserMapper.class);
            count = tbUserMapper.selectForLogin(name,pwd);
            if (count==0){
                System.out.println(name+"不存在");
            }
            else {
                System.out.println("success");
            }
        } finally {
            sqlSession.close();
        }
    }
}