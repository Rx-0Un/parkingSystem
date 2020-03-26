package com.example.demo.service;

import com.example.demo.entity.TbStaffTask;
import com.example.demo.mapper.TbStaffTaskMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("StaffTaskService")
public class StaffTaskService {

    TbStaffTaskMapper tbStaffTaskMapper;

    public int selectCountByStaffId(String staff_id) {
        return tbStaffTaskMapper.selectCountByStaffId(staff_id);
    }


    public List<TbStaffTask> selectUnfinishedTask(String staff_id,int pageNum,int page) {
        return tbStaffTaskMapper.selectUnfinishedTask(staff_id,pageNum,page);
    }

    public List<TbStaffTask> selectAll() {
        return tbStaffTaskMapper.selectAll();
    }

    public List<TbStaffTask> selectTaskByFuzzStr(String search,String searchData,String searchDate1,int pageNum,int page){
        return tbStaffTaskMapper.selectTaskByFuzzStr(search,searchData,searchDate1,pageNum,page);
    }


    public List<TbStaffTask> selectAllPage(int pageNum,int page){return  tbStaffTaskMapper.selectAllPage(pageNum,page);};

    public int addTask(String staffId, String starting_time, String description)
    {
        return tbStaffTaskMapper.addTask(staffId, starting_time,description);
    }

    @Autowired
    public void setTbStaffTaskMapper(TbStaffTaskMapper tbStaffTaskMapper) {
        this.tbStaffTaskMapper = tbStaffTaskMapper;
    }
}
