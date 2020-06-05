package com.example.demo.test;

import com.example.demo.entity.TbOrder;
import com.example.demo.entity.TbParkingLot;
import com.example.demo.entity.TbParkingRecord;
import com.example.demo.entity.TbStaffDuty;
import com.example.demo.mapper.TbOrderMapper;
import com.example.demo.mapper.TbParkingLotMapper;
import com.example.demo.mapper.TbParkingRecordMapper;
import com.example.demo.mapper.TbStaffDutyMapper;
import com.example.demo.rule.InterimRule;
import com.example.demo.util.DateUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ParkingRecordMapperTest extends BaseMapperTest {
    @Test
    public void selectEnterTest() {
        SqlSession sqlSession = getSqlSession();
        try {
            TbParkingRecordMapper tbParkingRecordMapper = sqlSession.getMapper(TbParkingRecordMapper.class);
//            Date date =DateUtil.process("2020-04-16 17:53:07");
            String date = sqlSession.getMapper(TbStaffDutyMapper.class).selectStartingTime();
            List<TbParkingRecord> list = tbParkingRecordMapper.selectEnter(date, 10, 0);
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).toString());
            }
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void selectOuterTest() {
        SqlSession sqlSession = getSqlSession();
        try {
            TbParkingRecordMapper tbParkingRecordMapper = sqlSession.getMapper(TbParkingRecordMapper.class);
//            Date date =DateUtil.process("2020-04-16 17:53:07");
            String date = sqlSession.getMapper(TbStaffDutyMapper.class).selectStartingTime();
            List<TbParkingRecord> list = tbParkingRecordMapper.selectOuter(date, 0, 0);
            for (int i = 0; i < list.size(); i++) {
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
            List<TbParkingRecord> list = tbParkingRecordMapper.selectAll(0, 0);
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).toString());
            }
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void selectCarTest() {
        SqlSession sqlSession = getSqlSession();
        try {
            TbParkingRecordMapper tbParkingRecordMapper = sqlSession.getMapper(TbParkingRecordMapper.class);
            List<TbParkingRecord> list = tbParkingRecordMapper.selectCar();
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).toString());
            }
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void updateByOuterTest() {

    }

    @Test
    public void selectDutyAllTest() {
        SqlSession sqlSession = getSqlSession();
        try {
            TbParkingRecordMapper tbParkingRecordMapper = sqlSession.getMapper(TbParkingRecordMapper.class);
            List<TbParkingRecord> list = tbParkingRecordMapper.selectDutyAll("2020-04-22 13:06:32", 0, 0);
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).toString());
            }
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void selectCountEnterTest() {
        SqlSession sqlSession = getSqlSession();
        try {
            TbParkingRecordMapper tbParkingRecordMapper = sqlSession.getMapper(TbParkingRecordMapper.class);
            int count = tbParkingRecordMapper.selectCountEnter("2020-04-23 22:18:36");
            System.out.println(count);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void selectCountOuterTest() {
        SqlSession sqlSession = getSqlSession();
        try {
            TbParkingRecordMapper tbParkingRecordMapper = sqlSession.getMapper(TbParkingRecordMapper.class);
            int count = tbParkingRecordMapper.selectCountOuter("2020-04-22 13:06:32");
            System.out.println(count);
        } finally {
            sqlSession.close();
        }
    }
    @Test
    public void selectTenDayDateTest() {
        SqlSession sqlSession = getSqlSession();
        try {
            TbParkingRecordMapper tbParkingRecordMapper = sqlSession.getMapper(TbParkingRecordMapper.class);
            List<Integer> list = new ArrayList<>();
            for (int i = 9; i > -1; i--) {
                list.add(tbParkingRecordMapper.selectTenDayDate(i).size());
            }
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).toString());
            }
        } finally {
            sqlSession.close();
        }
    }
    @Test
    public void Test() {
        SqlSession sqlSession = getSqlSession();
        try {
            TbParkingRecordMapper tbParkingRecordMapper = sqlSession.getMapper(TbParkingRecordMapper.class);
//            TbParkingRecord tbParkingRecord= tbParkingRecordMapper.selectRowByCarNum("京as56d4");
//            System.out.println(tbParkingRecord.getEnterTime());
        } finally {
            sqlSession.close();
        }
    }
    @Test
    public void select24hourDateTest() {
        SqlSession sqlSession = getSqlSession();
        try {
            TbParkingRecordMapper tbParkingRecordMapper = sqlSession.getMapper(TbParkingRecordMapper.class);
            int count = tbParkingRecordMapper.select24hourDate(0,"2020-06-04 11:58:07");
            System.out.println(count);
        } finally {
            sqlSession.close();
        }
    }
}
