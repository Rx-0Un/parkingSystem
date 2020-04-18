package com.example.demo.test;

import com.example.demo.entity.TbParkingLot;
import com.example.demo.entity.TbParkingRecord;
import com.example.demo.mapper.TbParkingLotMapper;
import com.example.demo.mapper.TbParkingRecordMapper;
import com.example.demo.rule.InterimRule;
import com.example.demo.util.DateUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class ParkingRecordMapperTest extends BaseMapperTest {
    @Test
    public void selectRowById() {
        SqlSession sqlSession = getSqlSession();
        try {
            TbParkingRecordMapper tbParkingRecordMapper = sqlSession.getMapper(TbParkingRecordMapper.class);
            Date date =DateUtil.process("2020-04-16 17:53:07");
            List<TbParkingRecord> list = tbParkingRecordMapper.selectEnter(date, 0, 0);
            for (int i=0;i<list.size();i++) {
                System.out.println(list.get(i).toString());
            }
        } finally {
            sqlSession.close();
        }
    }
    @Test
    public void selectAllTest() {
        SqlSession sqlSession = getSqlSession();
        try {
            TbParkingRecordMapper tbParkingRecordMapper = sqlSession.getMapper(TbParkingRecordMapper.class);
            List<TbParkingRecord> list = tbParkingRecordMapper.selectAll(0,0);
            for (int i=0;i<list.size();i++) {
                System.out.println(list.get(i).toString());
            }
        } finally {
            sqlSession.close();
        }
    }
}
