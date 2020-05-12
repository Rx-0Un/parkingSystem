package com.example.demo.service;

import com.example.demo.entity.TbParkingLot;
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

    public List<TbParkingRecord> selectEnter(String date, int pageNum, int page) {
        return tbParkingRecordMapper.selectEnter(date, pageNum, page);
    }

    public List<TbParkingRecord> selectOuter(String date, int pageNum, int page) {
        return tbParkingRecordMapper.selectOuter(date, pageNum, page);
    }

    public List<TbParkingRecord> selectAll(int pageNum, int page) {
        return tbParkingRecordMapper.selectAll(pageNum, page);
    }

    public List<TbParkingRecord> selectAllByEnter(int pageNum, int page) {
        return tbParkingRecordMapper.selectAllByEnter(pageNum, page);
    }

    public List<TbParkingRecord> selectDutyAll(String starting_time, int pageNum, int page) {
        return tbParkingRecordMapper.selectDutyAll(starting_time, pageNum, page);
    }

    public List<TbParkingRecord> selectAllByOuter(int pageNum, int page) {
        return tbParkingRecordMapper.selectAllByOuter(pageNum, page);
    }

    public List<TbParkingRecord> selectCar() {
        return tbParkingRecordMapper.selectCar();
    }

    public int updateByOuter(String order_car_number, String starting_time, Date date, int order_id) {
        return tbParkingRecordMapper.updateByOuter(order_car_number, starting_time, date, order_id);
    }

    public int selectCountEnter(String starting_time) {
        return tbParkingRecordMapper.selectCountEnter(starting_time);
    }

    public int selectCountOuter(String starting_time) {
        return tbParkingRecordMapper.selectCountOuter(starting_time);
    }

    public int selectOccupyNum(){
        return tbParkingRecordMapper.selectOccupyNum();
    }


    public Date selectEnterTimeByCarPlate(String  order_car_number){
        return tbParkingRecordMapper.selectEnterTimeByCarPlate(order_car_number);
    }

    @Autowired
    public void setTbParkingRecordMapper(TbParkingRecordMapper tbParkingRecordMapper) {
        this.tbParkingRecordMapper = tbParkingRecordMapper;
    }
}