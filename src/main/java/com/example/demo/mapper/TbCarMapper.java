package com.example.demo.mapper;

import com.example.demo.entity.TbCar;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TbCarMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_car
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    int insert(TbCar record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_car
     *
     * @mbg.generated Tue Feb 25 17:41:08 GMT+08:00 2020
     */
    int insertSelective(TbCar record);

    List<TbCar> selectAll(int pageNum,int page);

    int addRowByInfo(String car_type,String car_plate_number,String car_color,String car_type_model,int column_6);
    int delectRowById(int id);
    int updateRowByInfo(int id,String car_type,String car_plate_number,String car_color,String car_type_model,String column_6);
    int selectCarByCarPlate(String car_plate_number);
}