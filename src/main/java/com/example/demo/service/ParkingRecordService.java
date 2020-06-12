package com.example.demo.service;

import com.example.demo.entity.TbCar;
import com.example.demo.entity.TbParkingLot;
import com.example.demo.entity.TbParkingRecord;
import com.example.demo.mapper.TbParkingLotMapper;
import com.example.demo.mapper.TbParkingRecordMapper;
import com.example.demo.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ParkingRecordService {
    TbParkingRecordMapper tbParkingRecordMapper;
    TbParkingLotMapper tbParkingLotMapper;

    public int addRowByInfo(String car_plate_num, Date enter_time) {
        return tbParkingRecordMapper.addRowByInfo(car_plate_num, DateUtil.processDateToString(enter_time));
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
        return tbParkingRecordMapper.updateByOuter(order_car_number, starting_time, DateUtil.processDateToString(date), order_id);
    }

    public int selectCountEnter(String starting_time) {
        return tbParkingRecordMapper.selectCountEnter(starting_time);
    }

    public int selectCountOuter(String starting_time) {
        return tbParkingRecordMapper.selectCountOuter(starting_time);
    }

    public int selectOccupyNum() {
        return tbParkingRecordMapper.selectOccupyNum();
    }

    public int selectNumByDate(Date date) {
        return tbParkingRecordMapper.selectNumByDate(DateUtil.processDateToString(date));
    }

    public TbParkingRecord selectEnterTimeByCarPlate(String order_car_number) {
        return tbParkingRecordMapper.selectEnterTimeByCarPlate(order_car_number);
    }

    public List<TbParkingRecord> selectRowByCarNum(String car_number) {
        return tbParkingRecordMapper.selectRowByCarNum(car_number);
    }

    public List<Integer> selectTenDayDate() {
        List<Integer> list = new ArrayList<>();
        for (int i = 9; i > -1; i--) {
            list.add(tbParkingRecordMapper.selectTenDayDate(i).size());
        }
        return list;
    }

    public List<Integer> select24hourDate() {
        String date = DateUtil.processDateToString(new Date());
        int total = tbParkingLotMapper.selectRowById(2).get(0).getSpaceNumber();
        List<Integer> list = new ArrayList<>();
        for (int i = 23; i > -1; i--) {
            int count = tbParkingRecordMapper.select24hourDate(i, date);
            list.add(total - count);
        }

        return list;
    }

    public int selectExitCarByDate(Date starting_time, Date ending_time) {
        return tbParkingRecordMapper.selectExitCarByDate(DateUtil.processDateToString(starting_time), DateUtil.processDateToString(ending_time));
    }

    public int selectEnterCarByDate(Date starting_time, Date ending_time) {
        return tbParkingRecordMapper.selectEnterCarByDate(DateUtil.processDateToString(starting_time), DateUtil.processDateToString(ending_time));
    }

    public List<TbParkingRecord> selectAllAndOrder() {
        return tbParkingRecordMapper.selectAllAndOrder();
    }


    public float selectCountByDate(Date starting_time, Date ending_time) {
        List<TbParkingRecord> list = tbParkingRecordMapper.selectCountByDate(DateUtil.processDateToString(starting_time), DateUtil.processDateToString(ending_time));
        float sum = 0;
        for (TbParkingRecord tbParkingRecord : list) {
            sum += tbParkingRecord.getTbOrder().getOrderAmount();
        }
        return sum;
    }

    public int selectAllByDate(Date starting_time, Date ending_time) {
        return tbParkingRecordMapper.selectAllByDate(DateUtil.processDateToString(starting_time), DateUtil.processDateToString(ending_time));
    }

    public List<TbParkingRecord> selectInterimCarByDate(Date starting_time, Date ending_time) {
        return tbParkingRecordMapper.selectInterimCarByDate(DateUtil.processDateToString(starting_time), DateUtil.processDateToString(ending_time));
    }

    public List<TbParkingRecord> selectAllByInfo(String starting_date, String search_car_plate_type, String search_car_type, String search_pay_type, String search_Keyword_title, String search_Keyword, int pageNum, int page) {
        return tbParkingRecordMapper.selectAllByInfo(starting_date, search_car_plate_type, search_car_type, search_pay_type, search_Keyword_title, search_Keyword, pageNum, page);
    }

    public List<TbParkingRecord> selectAllByStr(String record_search__starting_time, String record_search__ending_time, String record_search_keyword_title,String record_search__keyword, Integer pageNum, Integer page) {
        return tbParkingRecordMapper.selectAllByStr(record_search__starting_time, record_search__ending_time, record_search_keyword_title,record_search__keyword, pageNum, page);
    }

    @Autowired
    public void setTbParkingRecordMapper(TbParkingRecordMapper tbParkingRecordMapper) {
        this.tbParkingRecordMapper = tbParkingRecordMapper;
    }

    @Autowired
    public void setTbParkingLotMapper(TbParkingLotMapper tbParkingLotMapper) {
        this.tbParkingLotMapper = tbParkingLotMapper;
    }
}
