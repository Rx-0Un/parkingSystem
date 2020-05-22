package com.example.demo.service;

import com.example.demo.entity.TbParkingSpace;
import com.example.demo.mapper.TbParkingSpaceMapper;
import com.example.demo.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ParkingSpaceService {
    TbParkingSpaceMapper tbParkingSpaceMapper;

    public List<TbParkingSpace> selectAll(int pageNum, int page) {
        return tbParkingSpaceMapper.selectAll(pageNum, page);
    }

    public List<TbParkingSpace> selectAllByFurryStr(String parking_space_key_word_title, String parking_space_key_word, String parking_space_searchDate, int pageNum, int page) {
        return tbParkingSpaceMapper.selectAllByFurryStr(parking_space_key_word_title, parking_space_key_word, parking_space_searchDate, pageNum, page);
    }

    public int addParkingSpace(String parkingSpaceArea) {
        return tbParkingSpaceMapper.addParkingSpace(parkingSpaceArea);
    }

    public int updateParkingSpace(String car_id, String expire_date, String parking_space_id) {
        try {
            TbParkingSpace tbParkingSpace = tbParkingSpaceMapper.selectRowById(parking_space_id);
            Date date = new Date();
            if (date.getTime() < tbParkingSpace.getExpireDate().getTime()) {
                return tbParkingSpaceMapper.addateParkingSpace(car_id, expire_date, parking_space_id);
            }
        } catch (java.lang.NullPointerException e) {
            return tbParkingSpaceMapper.addateParkingSpace(car_id, expire_date, parking_space_id);
        }
        return tbParkingSpaceMapper.updateParkingSpace(car_id, expire_date, parking_space_id);
    }

    public int selectCarSpaceNumberByUserId(String userId) {
        return tbParkingSpaceMapper.selectCarSpaceNumberByUserId(userId);
    }

    public List<TbParkingSpace> selectNotOccupy() {
        return tbParkingSpaceMapper.selectNotOccupy();
    }

    public List<TbParkingSpace> selectExpireDateByCar(String car_plate_num) {
        return tbParkingSpaceMapper.selectExpireDateByCar(car_plate_num);
    }

    public List<TbParkingSpace> selectAllByUserId(String userId) {
        return tbParkingSpaceMapper.selectAllByUserId(userId);
    }

    @Autowired
    public void setTbParkingSpaceMapper(TbParkingSpaceMapper tbParkingSpaceMapper) {
        this.tbParkingSpaceMapper = tbParkingSpaceMapper;
    }
}
