package com.example.demo.service;

import com.example.demo.entity.TbParkingLot;
import com.example.demo.mapper.TbParkingLotMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingLotSettingService {
    TbParkingLotMapper tbParkingLotMapper;

    public int addRowByInfo(String parking_lot_name, int parking_space_number, String zone_type, String pay_setting, String print_setting, String rule_setting) {
        if (selectRowById(2) == null) {
            return tbParkingLotMapper.addRowByInfo(parking_lot_name, parking_space_number, zone_type, pay_setting, print_setting, rule_setting);
        }
        return tbParkingLotMapper.updateRowByInfo(parking_lot_name, parking_space_number, zone_type, pay_setting, print_setting, rule_setting);
    }

    public List<TbParkingLot> selectRowById(int id) {
        return tbParkingLotMapper.selectRowById(id);
    }

    @Autowired
    public void setTbParkingLotMapper(TbParkingLotMapper tbParkingLotMapper) {
        this.tbParkingLotMapper = tbParkingLotMapper;
    }
}
