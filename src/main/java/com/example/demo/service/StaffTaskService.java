package com.example.demo.service;

import com.example.demo.entity.TbStaffTask;
import com.example.demo.mapper.TbStaffTaskMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffTaskService {
    @Autowired
    TbStaffTaskMapper tbStaffTaskMapper;

    public int selectCountByStaffId(String staff_id) {
        return tbStaffTaskMapper.selectCountByStaffId(staff_id);
    }


    public List<TbStaffTask> selectUnfinishedTask(String staff_id) {
        return tbStaffTaskMapper.selectUnfinishedTask(staff_id);
    }

    public List<TbStaffTask> selectAll() {
        return tbStaffTaskMapper.selectAll();
    }

    public int addTask(String staffId, String starting_time, String description)
    {
        return tbStaffTaskMapper.addTask(staffId, starting_time,description);
    }
}
