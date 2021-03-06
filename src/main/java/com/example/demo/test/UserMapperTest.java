package com.example.demo.test;

import com.example.demo.entity.TbUser;
import com.example.demo.mapper.TbUserAmountMapper;
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
    @Test
    public void selectRowByPhoneTest() {
        SqlSession sqlSession = getSqlSession();
        try {
            TbUserMapper tbUserMapper= sqlSession.getMapper(TbUserMapper.class);
//            TbUser tbUser = tbUserMapper.selectRowByPhone("17798567311");
//            System.out.println(tbUser.toString());
        } finally {
            sqlSession.close();
        }
    }
    @Test
    public void updateRowByUserIdTest() {
        SqlSession sqlSession = getSqlSession();
        try {
            TbUserMapper tbUserMapper= sqlSession.getMapper(TbUserMapper.class);
            int count= tbUserMapper.updateRowByUserId("22","17798567311","男","tony","三里屯","523224616@qq.com");
            System.out.println(count);
        } finally {
            sqlSession.close();
        }
    }
}