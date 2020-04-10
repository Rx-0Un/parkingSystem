package com.example.demo.service;

import com.example.demo.entity.TbRuleCommonBasic;
import com.example.demo.mapper.TbRuleCommonBasicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("RuleCommonBasicService")
public class RuleCommonBasicService {
    TbRuleCommonBasicMapper tbRuleCommonBasicMapper;

    public List<TbRuleCommonBasic> selectAll(){
        return tbRuleCommonBasicMapper.selectAll();
    }


    public TbRuleCommonBasic selectRowById(String id){
        return tbRuleCommonBasicMapper.selectRowById(id);
    }

    @Autowired
    public void setTbRuleCommonBasicMapper(TbRuleCommonBasicMapper tbRuleCommonBasicMapper) {
        this.tbRuleCommonBasicMapper = tbRuleCommonBasicMapper;
    }
}
