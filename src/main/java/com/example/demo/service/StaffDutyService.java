package com.example.demo.service;

import com.example.demo.entity.TbStaffDuty;
import com.example.demo.mapper.TbStaffDutyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffDutyService {
    TbStaffDutyMapper tbStaffDutyMapper;

    public int addRowByInfo(int staff_id, String date, int staff_task_id) {
        if (tbStaffDutyMapper.selectDutyExist() == 1) {
            return 0;
        }
        return tbStaffDutyMapper.addRowByInfo(staff_id, date, staff_task_id);
    }

    public int updateRowByInf0(String date) {
        if (tbStaffDutyMapper.selectDutyExist() == 0) {
            return 0;
        }
        return tbStaffDutyMapper.updateRowByInfo(date);
    }

    public String selectStartingTime() {
        return tbStaffDutyMapper.selectStartingTime();
    }

    public TbStaffDuty selectLastRow(){
        return tbStaffDutyMapper.selectLastRow();
    }

    public List<TbStaffDuty> selectAll(int pageNum,int page){
        return tbStaffDutyMapper.selectAll(pageNum,page);
    }
    @Autowired
    public void setTbStaffDutyMapper(TbStaffDutyMapper tbStaffDutyMapper) {
        this.tbStaffDutyMapper = tbStaffDutyMapper;
    }
}
