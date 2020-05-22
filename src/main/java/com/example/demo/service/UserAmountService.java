package com.example.demo.service;

import com.example.demo.entity.TbUser;
import com.example.demo.mapper.TbUserAmountMapper;
import com.example.demo.mapper.TbUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAmountService {
    TbUserAmountMapper tbUserAmountMapper;
    TbUserMapper tbUserMapper;

    public int addRowByInfo(String user_id, int amount) {
        if (tbUserMapper.selectRowById(user_id) == 1) {
            return 0;
        }
        if (tbUserAmountMapper.selectRowById(user_id) == 1) {
            tbUserAmountMapper.updateRowByInfo(user_id, amount);
        } else {
            tbUserAmountMapper.addRowByInfo(user_id, amount);
        }
        return 0;
    }

    public List<TbUser> selectRowByPhone(String phone) {
        return tbUserMapper.selectRowByPhone(phone);
    }

    public float selectAmountByInfo(String user_id) {
        return tbUserAmountMapper.selectMoneyByInfo(user_id);
    }

    public float updateMoneyByCharge(String user_id) {
        return tbUserAmountMapper.selectMoneyByInfo(user_id);
    }

    @Autowired
    public void setTbUserAmountMapper(TbUserAmountMapper tbUserAmountMapper) {
        this.tbUserAmountMapper = tbUserAmountMapper;
    }

    @Autowired
    public void setTbUserMapper(TbUserMapper tbUserMapper) {
        this.tbUserMapper = tbUserMapper;
    }
}
