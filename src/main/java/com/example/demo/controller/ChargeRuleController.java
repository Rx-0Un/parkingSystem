package com.example.demo.controller;

import com.example.demo.bean.CommonCharge;
import com.example.demo.bean.ResultBean;
import com.example.demo.entity.TbRuleCommonBasic;
import com.example.demo.rule.BasicRule;
import com.example.demo.service.RuleCommonBasicService;
import com.example.demo.util.DateUtil;
import com.example.demo.rule.Rule;
import com.example.demo.util.RuleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Controller("ChargeRuleController")
@RequestMapping(value = "/index-charge-rule")
public class ChargeRuleController {
    RuleCommonBasicService ruleCommonBasicService;

    @RequestMapping(value = "/selectBasicRule")
    public String selectBasicRule(Model model) {
        List<TbRuleCommonBasic> list = ruleCommonBasicService.selectAll();
        for (int i = 0; i < list.size(); i++) {
            int j = i + 1;
            model.addAttribute("price_" + j, list.get(i).getMoney());
        }
        model.getAttribute("price_1");


        model.addAttribute("basic", ruleCommonBasicService.selectAll());
        return "index-charge-rule::basic";
    }

    @RequestMapping(value = "/selectRowById")
    public String selectRowById(Model model, String id) {

        model.addAttribute("basic", ruleCommonBasicService.selectRowById(id));
        return "index-charge-rule::basic";
    }

    @RequestMapping(value = "/parkingTest", method = RequestMethod.POST)
    public String parkingTest(Model model, @RequestBody Map<String, String> map) {
        int rule = Integer.parseInt(map.get("rule"));
        String interim_rule = map.get("interim_rul");
        String plate_type = map.get("plate_type");
        String car_type = map.get("car_type");
        String Date = map.get("Date");
        String time_type = map.get("time_type");
        String searchNum = map.get("searchNum");
        System.out.println(map.toString());
        model.addAttribute("TestResult", processData(rule, interim_rule, plate_type, car_type, Date, time_type));
        return "index-charge-rule::TestResult";
    }

    public List<ResultBean> processData(int rule, String interim_rule, String plate_type, String car_type, String Date, String time_type) {
        //字符串转化成时间
        Date starting_time = new Date();
        Date ending_time = new Date();
        Date = Date.trim();
        for (int i = 0; i < Date.length(); i++) {
            if (Date.charAt(i) == '到') {
                starting_time = DateUtil.process(Date.substring(0, i - 1));
                ending_time = DateUtil.process(Date.substring(i + 1, Date.length()));
            }
        }

        switch (car_type) {
            case "小车":
            case "大车":
            case "超大型车":
            case "其他车":
        }

        return processRule(rule, starting_time, ending_time, time_type, new CommonCharge(15, 3, 1, 5, 1));
    }

    public List<ResultBean> processRule(int rule, Date starting_time, Date ending_time, String time_type, CommonCharge charge) {
        switch (rule) {
            case RuleUtil.RULE_BASIC:
                BasicRule basicRule = new Rule(starting_time, ending_time, charge);
                List<ResultBean> list = basicRule.getDailyMap();
                for (ResultBean resultBean : list) {
                    System.out.println("Time = " + resultBean.getDate() + ", Total = " + resultBean.getTotal());
                }
                return list;
            case RuleUtil.RULE_CUSTOM:
            case RuleUtil.RULE_PERSON:
            default:
                return null;
        }
    }



    @Autowired
    public void setRuleCommonBasicService(RuleCommonBasicService ruleCommonBasicService) {
        this.ruleCommonBasicService = ruleCommonBasicService;
    }
}
