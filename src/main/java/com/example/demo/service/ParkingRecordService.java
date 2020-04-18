package com.example.demo.service;

import com.example.demo.mapper.TbParkingRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ParkingRecordService {
    TbParkingRecordMapper tbParkingRecordMapper;

    public int addRowByInfo(String car_plate_num, Date enter_time) {
        return tbParkingRecordMapper.addRowByInfo(car_plate_num, enter_time);
    }

    @Autowired
    public void setTbParkingRecordMapper(TbParkingRecordMapper tbParkingRecordMapper) {
        this.tbParkingRecordMapper = tbParkingRecordMapper;
    }
}
