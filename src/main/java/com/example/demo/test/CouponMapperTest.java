package com.example.demo.test;

import com.example.demo.entity.TbCoupon;
import com.example.demo.entity.TbOrder;
import com.example.demo.mapper.TbCouponMapper;
import com.example.demo.mapper.TbOrderMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class CouponMapperTest extends BaseMapperTest {
    @Test
    public void selectDutyOrder() {
        SqlSession sqlSession = getSqlSession();
        try {
            TbCouponMapper tbCouponMapper = sqlSession.getMapper(TbCouponMapper.class);
            List<TbCoupon> list = tbCouponMapper.selectCouponByStr("", "2020-05-01 01:43", "", 10, 0);
            for (int i = 0; i <list.size(); i++) {
                System.out.println(list.get(i).toString());
            }
        } finally {
            sqlSession.close();
        }
    }
}
