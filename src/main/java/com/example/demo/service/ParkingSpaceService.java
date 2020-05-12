package com.example.demo.service;

import com.example.demo.entity.TbParkingSpace;
import com.example.demo.mapper.TbParkingSpaceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingSpaceService {
    TbParkingSpaceMapper tbParkingSpaceMapper;

    public List<TbParkingSpace> selectAll(int pageNum, int page) {
        return tbParkingSpaceMapper.selectAll(pageNum, page);
    }

    @Autowired
    public void setTbParkingSpaceMapper(TbParkingSpaceMapper tbParkingSpaceMapper) {
        this.tbParkingSpaceMapper = tbParkingSpaceMapper;
    }
}
