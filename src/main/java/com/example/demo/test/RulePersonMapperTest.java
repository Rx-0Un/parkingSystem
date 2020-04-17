package com.example.demo.test;

import com.example.demo.entity.TbRuleCommonBasic;
import com.example.demo.entity.TbRulePerson;
import com.example.demo.mapper.TbRuleCommonBasicMapper;
import com.example.demo.mapper.TbRulePersonMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class RulePersonMapperTest extends BaseMapperTest {
    @Test
    public void selectRowByCarTypeTest() {
        SqlSession sqlSession = getSqlSession();
        try {
            TbRulePersonMapper tbRulePersonMapper = sqlSession.getMapper(TbRulePersonMapper.class);
            int count = tbRulePersonMapper.selectRowByCarType("01");
            System.out.println(count);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void addRowByCarTypeTest() {
        SqlSession sqlSession = getSqlSession();
        try {
            TbRulePersonMapper tbRulePersonMapper = sqlSession.getMapper(TbRulePersonMapper.class);
            int count = tbRulePersonMapper.addRowByCarType("01", 5);
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
            TbRulePersonMapper tbRulePersonMapper = sqlSession.getMapper(TbRulePersonMapper.class);
            int count = tbRulePersonMapper.updateRowByCarType("01", 4);
            System.out.println(count);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void selectAllTest() {
        SqlSession sqlSession = getSqlSession();
        try {
            TbRulePersonMapper tbRulePersonMapper = sqlSession.getMapper(TbRulePersonMapper.class);
            List<TbRulePerson> list = tbRulePersonMapper.selectAll();
            for (int i=0;i<list.size();i++){
                System.out.println(list.get(i).toString());
            }
        } finally {
            sqlSession.close();
        }
    }
}
