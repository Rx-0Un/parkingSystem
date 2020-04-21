package com.example.demo.service;

import com.example.demo.entity.TbParkingRecord;
import com.example.demo.mapper.TbParkingRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ParkingRecordService {
    TbParkingRecordMapper tbParkingRecordMapper;

    public int addRowByInfo(String car_plate_num, Date enter_time) {
        return tbParkingRecordMapper.addRowByInfo(car_plate_num, enter_time);
    }

    public List<TbParkingRecord> selectEnter(Date date, int pageNum, int page) {
        return tbParkingRecordMapper.selectEnter(date, pageNum, page);
    }

    public List<TbParkingRecord> selectOuter(Date date, int pageNum, int page) {
        return tbParkingRecordMapper.selectOuter(date, pageNum, page);
    }

    public List<TbParkingRecord> selectAll(int pageNum, int page) {
        return tbParkingRecordMapper.selectAll(pageNum, page);
    }

    public List<TbParkingRecord> selectCar() {
        return tbParkingRecordMapper.selectCar();
    }

    @Autowired
    public void setTbParkingRecordMapper(TbParkingRecordMapper tbParkingRecordMapper) {
        this.tbParkingRecordMapper = tbParkingRecordMapper;
    }
}
