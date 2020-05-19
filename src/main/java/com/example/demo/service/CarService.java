package com.example.demo.service;

import com.example.demo.entity.TbCar;
import com.example.demo.mapper.TbCarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    TbCarMapper tbCarMapper;

    public  int addRowByInfo(String car_type,String car_plate_number,String car_color,String car_type_model,int column_6){
        if (tbCarMapper.selectCarByCarPlate(car_plate_number)==1){
            return 0;
        }
        return tbCarMapper.addRowByInfo(car_type, car_plate_number, car_color, car_type_model, column_6);
    }
    public List<TbCar> selectAll(int pageNum, int page){
        return tbCarMapper.selectAll(pageNum, page);
    }

    public List<TbCar> selectCarByUserId(String userId){
        return tbCarMapper.selectCarByUserId(userId);
    }
    public int updateCarType(String carPlateNumber){
        return tbCarMapper.updateCarType(carPlateNumber);
    }
    @Autowired
    public void setTbCarMapper(TbCarMapper tbCarMapper) {
        this.tbCarMapper = tbCarMapper;
    }
}
