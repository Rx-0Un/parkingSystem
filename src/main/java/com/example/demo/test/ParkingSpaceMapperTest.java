package com.example.demo.test;

import com.example.demo.entity.TbParkingRecord;
import com.example.demo.entity.TbParkingSpace;
import com.example.demo.mapper.TbParkingRecordMapper;
import com.example.demo.mapper.TbParkingSpaceMapper;
import com.example.demo.mapper.TbStaffDutyMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class ParkingSpaceMapperTest extends BaseMapperTest{
    @Test
    public void selectAllByFurryStrTest() {
        SqlSession sqlSession = getSqlSession();
        try {
            TbParkingSpaceMapper tbParkingSpaceMapper = sqlSession.getMapper(TbParkingSpaceMapper.class);
//            Date date =DateUtil.process("2020-04-16 17:53:07");
            List<TbParkingSpace> list = tbParkingSpaceMapper.selectAllByFurryStr("全体","4","",0,0);
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).toString());
            }
        } finally {
            sqlSession.close();
        }
    }
}
