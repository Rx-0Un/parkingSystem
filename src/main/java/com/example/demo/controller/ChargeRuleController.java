package com.example.demo.controller;

import com.example.demo.bean.*;
import com.example.demo.entity.TbRuleCommonBasic;
import com.example.demo.entity.TbRuleCustomInterim;
import com.example.demo.rule.BasicRule;
import com.example.demo.rule.CustomRule;
import com.example.demo.rule.PersonRule;
import com.example.demo.service.*;
import com.example.demo.util.DateUtil;
import com.example.demo.rule.Rule;
import com.example.demo.util.RuleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Controller("ChargeRuleController")
@RequestMapping(value = "/index-charge-rule")
public class ChargeRuleController {
    RuleCommonBasicService ruleCommonBasicService;
    RulePersonService rulePersonService;
    RuleFixedParkingService ruleFixedParkingService;
    RuleCustomInterimService ruleCustomInterimService;
    RuleCustomService ruleCustomService;

    @RequestMapping(value = "/selectRowById")
    public String selectRowById(Model model, String id) {

        model.addAttribute("basic", ruleCommonBasicService.selectRowById(id));
        return "index-charge-rule::basic";
    }

    @RequestMapping(value = "/addPersonRule", method = RequestMethod.POST)
    public String addPersonRule(Model model, @RequestBody Map<String, String> map) {
        String person_car_type = map.get("person_car_type");
        String person_car_money = map.get("person_car_money");
        System.out.println(person_car_type);
        System.out.println(person_car_money);
        rulePersonService.addRowByCarType(person_car_type, Float.parseFloat(person_car_money));
        model.addAttribute("PersonResult", rulePersonService.selectAll());
        return "index-charge-rule::PersonResult";
    }

    @RequestMapping(value = "/addFixedRule", method = RequestMethod.POST)
    public String addFixedRule(Model model, @RequestBody Map<String, String> map) {
        String fixed_car_type = map.get("fixed_car_type");
        String fixed_charge_cycle = map.get("fixed_charge_cycle");
        String fixed_money = map.get("fixed_money");
        String fixed_description = map.get("fixed_description");
//        System.out.println(fixed_car_type);
//        System.out.println(fixed_charge_cycle);
//        System.out.println(fixed_money);
//        System.out.println(fixed_description);
        ruleFixedParkingService.addRowByCarType(fixed_car_type, fixed_charge_cycle, Float.parseFloat(fixed_money), fixed_description);
        model.addAttribute("FixedResult", ruleFixedParkingService.selectAll());
        return "index-charge-rule::FixedResult";
    }

    @RequestMapping(value = "/addInterimRule", method = RequestMethod.POST)
    public String addInterimRule(Model model, @RequestBody Map<String, String> map) {
        String interim_car_type = map.get("interim_car_type");
        String interim_rule_type = map.get("interim_rule_type");
        String interim_money = map.get("interim_money");
        String interim_use_date = map.get("interim_use_date");
        System.out.println(interim_car_type);
        System.out.println(interim_rule_type);
        System.out.println(interim_money);
        System.out.println(interim_use_date);
        ruleCustomInterimService.addRowByCarType(interim_rule_type, Float.parseFloat(interim_money),
                interim_car_type, DateUtil.processStringToDate(interim_use_date));

        int total = (ruleCustomInterimService.selectAll(0, 0).size() / 10) + 1;
        model.addAttribute("InterimResult", ruleCustomInterimService.selectAll(10, 0));
        model.addAttribute("InterimNowPage", 1);
        model.addAttribute("InterimTotalPage", total);
        return "index-charge-rule::InterimResult";
    }

    @RequestMapping(value = "/selectInterimRule", method = RequestMethod.POST)
    public String selectInterimRule(Model model, @RequestBody Map<String, String> map) {
        String interim_search_car_type = map.get("interim_search_car_type");
        String interim_search_rule_type = map.get("interim_search_rule_type");
        String interim_search_money = map.get("interim_search_money");
        String interim_search_date = map.get("interim_search_date");
        String interim_search_number = map.get("interim_search_number");
        int page = Integer.parseInt(map.get("page"));
        System.out.println(interim_search_date);
        int pageNum = Integer.parseInt(interim_search_number);
        List<TbRuleCustomInterim> list = ruleCustomInterimService.
                selectRowByPage(interim_search_rule_type, interim_search_car_type,
                        interim_search_date, interim_search_money,
                        pageNum, (page - 1) * pageNum);
        model.addAttribute("InterimResult", list);
        model.addAttribute("InterimNowPage", page);
        model.addAttribute("InterimTotalPage", (list.size() / pageNum) + 1);
        return "index-charge-rule::InterimResult";
    }

    @RequestMapping(value = "/addCustomRule", method = RequestMethod.POST)
    public String addCustomRule(Model model, @RequestBody Map<String, String> map) {
        String custom_car_type = map.get("custom_car_type");
        String custom_rule_type = map.get("custom_rule_type");
        String custom_money = map.get("custom_money");
        String custom_description = map.get("custom_description");
//        System.out.println(custom_car_type);
//        System.out.println(custom_rule_type);
//        System.out.println(custom_money);
//        System.out.println(custom_description);
        ruleCustomService.addRowByCarType(custom_car_type, custom_rule_type, Float.parseFloat(custom_money), custom_description);
        model.addAttribute("CustomResult", ruleCustomService.selectAll());
        return "index-charge-rule::CustomResult";
    }

    @RequestMapping(value = "/selectCustomRule", method = RequestMethod.POST)
    public String selectCustomRule(Model model, @RequestBody Map<String, String> map) {
        String custom_search_car_type = map.get("custom_search_car_type");
        String custom_search_rule_type = map.get("custom_search_rule_type");
        model.addAttribute("CustomResult", ruleCustomService.selectByFurzzyStr(custom_search_car_type, custom_search_rule_type));
        return "index-charge-rule::CustomResult";
    }

    @Autowired
    public void setRuleCommonBasicService(RuleCommonBasicService ruleCommonBasicService) {
        this.ruleCommonBasicService = ruleCommonBasicService;
    }

    @Autowired
    public void setRulePersonService(RulePersonService rulePersonService) {
        this.rulePersonService = rulePersonService;
    }

    @Autowired
    public void setRuleFixedParkingService(RuleFixedParkingService ruleFixedParkingService) {
        this.ruleFixedParkingService = ruleFixedParkingService;
    }

    @Autowired
    public void setRuleCustomInterimService(RuleCustomInterimService ruleCustomInterimService) {
        this.ruleCustomInterimService = ruleCustomInterimService;
    }

    @Autowired
    public void setRuleCustomService(RuleCustomService ruleCustomService) {
        this.ruleCustomService = ruleCustomService;
    }
}
