package com.example.demo.test;

import com.example.demo.entity.TbRuleCommonBasic;
import com.example.demo.entity.TbStaff;
import com.example.demo.mapper.TbRuleCommonBasicMapper;
import com.example.demo.mapper.TbStaffMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class RuleCommonBasicMapperTest extends BaseMapperTest {
    @Test
    public void selectAllTest() {
        SqlSession sqlSession = getSqlSession();
        try {
            TbRuleCommonBasicMapper tbRuleCommonBasicMapper = sqlSession.getMapper(TbRuleCommonBasicMapper.class);
            List<TbRuleCommonBasic> list = tbRuleCommonBasicMapper.selectAll();
            System.out.println(list.size());
        } finally {
            sqlSession.close();
        }
    }
    @Test
    public void selectRowByIdTest() {
        SqlSession sqlSession = getSqlSession();
        try {
            TbRuleCommonBasicMapper tbRuleCommonBasicMapper = sqlSession.getMapper(TbRuleCommonBasicMapper.class);
            TbRuleCommonBasic tbRuleCommonBasic = tbRuleCommonBasicMapper.selectRowById("1");
            System.out.println(tbRuleCommonBasic.toString());
        } finally {
            sqlSession.close();
        }
    }
}
