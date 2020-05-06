package com.example.demo.service;

import com.example.demo.entity.TbUser;
import com.example.demo.mapper.TbUserMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserService")
public class UserService {
    TbUserMapper tbUserMapper;

    public int selectByUserPhone(String phone) {
        return tbUserMapper.selectByUserPhone(phone);
    }

    public int selectForLogin(String phone, String password) {
        return tbUserMapper.selectForLogin(phone, password);
    }

    public List<TbUser> selectAll() {
        return tbUserMapper.selectAll();
    }

    public int addUserByInfo(String phone, String password) {
        return tbUserMapper.addUserByInfo(phone, password);
    }

    public List<TbUser> selectUser(String select, String Keyword) {
        switch (select) {
            case "全体":
                return tbUserMapper.selectAllByStr(Keyword);
            case "电话":
                return tbUserMapper.selectAllByPhone(Keyword);
            case "编号":
                return tbUserMapper.selectAllById(Keyword);
            case "姓名":
                return tbUserMapper.selectAllByName(Keyword);
        }
        return null;
    }

    @Autowired
    public void setTbUserMapper(TbUserMapper tbUserMapper) {
        this.tbUserMapper = tbUserMapper;
    }
}
