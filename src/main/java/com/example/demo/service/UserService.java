package com.example.demo.service;

import com.example.demo.mapper.TbUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserService {
    TbUserMapper tbUserMapper;

    public int selectByUserPhone(String phone) {
        return tbUserMapper.selectByUserPhone(phone);
    }

    public int selectForLogin(String phone, String password) {
        return tbUserMapper.selectForLogin(phone, password);
    }

    @Autowired
    public void setTbUserMapper(TbUserMapper tbUserMapper) {
        this.tbUserMapper = tbUserMapper;
    }
}
