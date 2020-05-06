package com.example.demo.test;

import com.example.demo.entity.TbRuleCustomInterim;
import com.example.demo.entity.TbRulePerson;
import com.example.demo.mapper.TbRuleCustomInterimMapper;
import com.example.demo.mapper.TbRulePersonMapper;
import com.example.demo.util.DateUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class RuleCustomInterimMapperTest extends BaseMapperTest {
    @Test
    public void selectRowByCarTypeTest() {

    }

    @Test
    public void addRowByCarTypeTest() {
        SqlSession sqlSession = getSqlSession();
        try {
            TbRuleCustomInterimMapper tbRuleCustomInterimMapper = sqlSession.getMapper(TbRuleCustomInterimMapper.class);
            int count = tbRuleCustomInterimMapper.addRowByCarType("全天", 5, "小车", "");
            System.out.println(count);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void updateRowByCarTypeTest() {

    }

    @Test
    public void selectAllTest() {
        SqlSession sqlSession = getSqlSession();
        try {
            TbRuleCustomInterimMapper tbRuleCustomInterimMapper = sqlSession.getMapper(TbRuleCustomInterimMapper.class);
            List<TbRuleCustomInterim> list = tbRuleCustomInterimMapper.selectAll(10, 1);
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).toString());
            }
        } finally {
            sqlSession.close();
        }
    }


    @Test
    public void selectTest() {
        SqlSession sqlSession = getSqlSession();
        try {
            TbRuleCustomInterimMapper tbRuleCustomInterimMapper = sqlSession.getMapper(TbRuleCustomInterimMapper.class);
            List<TbRuleCustomInterim> list = tbRuleCustomInterimMapper.selectRowByPage("全天", "小车", "2020-4-15", "80", 10, 0);
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).toString());
            }
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void selectAllExistTest() {
        SqlSession sqlSession = getSqlSession();
        try {
            TbRuleCustomInterimMapper tbRuleCustomInterimMapper = sqlSession.getMapper(TbRuleCustomInterimMapper.class);
            int count = tbRuleCustomInterimMapper.selectAllExist("2020-4-28");
            System.out.println(count);
        } finally {
            sqlSession.close();
        }
    }
}
