package com.example.demo.controller;

import com.example.demo.bean.*;
import com.example.demo.entity.TbRuleCommonBasic;
import com.example.demo.entity.TbRuleCustomInterim;
import com.example.demo.rule.*;
import com.example.demo.service.*;
import com.example.demo.util.DateUtil;
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
    List<InterimRule> interimRuleList;
    //规则类用于测试
    Rule rule;
    PersonRule personRule;
    CustomRule customRule;
    BasicCharge basicCharge = new BasicCharge("", "");

    //开始时间结束时间
    Date ending_time = new Date();
    Date starting_time = new Date();

    @RequestMapping(value = "/parkingTest", method = RequestMethod.POST)
    public String parkingTest(Model model, @RequestBody Map<String, String> map) {
        int rule = Integer.parseInt(map.get("rule"));
        String interim_rule = map.get("interim_rule");
        String plate_type = map.get("plate_type");
        String car_type = map.get("car_type");
        String Date = map.get("Date");
        String time_type = map.get("time_type");
        String zone_type = map.get("zone_type");

        int searchNum = Integer.parseInt(map.get("searchNum").trim());//用于处理分页
        int page = Integer.parseInt(map.get("page")) - 1;


        model.addAttribute("time_type", time_type);//用于处理视图
        processCharge(rule, car_type, zone_type);
        processData(Date);
        processInterimRule(this.starting_time, car_type);
        processRule(interim_rule, plate_type);
        List<ResultBean> list = processTimeType(rule, time_type);
        if (searchNum * (page + 1) < list.size()) {
            model.addAttribute("TestResult", list.subList(page * searchNum, (page + 1) * searchNum));
        }
        else {
            model.addAttribute("TestResult", list.subList(page * searchNum, list.size()));
        }
        model.addAttribute("TestTotalPage", (list.size() / searchNum) + 1);
        model.addAttribute("TestNowPage", page + 1);
        return "index-charge-rule::TestResult";
    }

    public void processInterimRule(Date date, String car_type) {
        TbRuleCustomInterim tbRuleCustomInterim;
        while (date.getTime() < ending_time.getTime()) {
//            interimRuleList.add(new I)
            date = DateUtil.getNextDayDate(date);
        }
    }


    public List<ResultBean> processTimeType(int rule, String time_type) {
        if (rule == 1) {
            switch (time_type) {
                case RuleUtil.TYPE_RESULT_LIST_ONE:
                    return this.rule.getDailyMap();
                case RuleUtil.TYPE_RESULT_LIST_TWO:
                    return this.rule.getMonthMap();
                case RuleUtil.TYPE_RESULT_LIST_THREE:
                    return this.rule.getYearLMap();
            }
        }
        if (rule == 3) {
            switch (time_type) {
                case RuleUtil.TYPE_RESULT_LIST_ONE:
                    return this.personRule.getDailyMap();
                case RuleUtil.TYPE_RESULT_LIST_TWO:
                    return this.personRule.getMonthMap();
                case RuleUtil.TYPE_RESULT_LIST_THREE:
                    return this.personRule.getYearLMap();
            }
        }

        return null;
    }

    //    public List<ResultBean> processData(int rule, String interim_rule, String plate_type, String car_type, String Date, String time_type) {
//        //字符串转化成时间
//        Date ending_time = new Date();
//        Date starting_time = new Date();
//        Date = Date.trim();
//        for (int i = 0; i < Date.length(); i++) {
//            if (Date.charAt(i) == '到') {
//                starting_time = DateUtil.process(Date.substring(0, i - 1));
//                ending_time = DateUtil.process(Date.substring(i + 1, Date.length()));
//            }
//        }
//
//
//        BasicCharge basicCharge;
//        switch (car_type) {
//            case "小车":
//            case "大车":
//            case "超大型车":
//            case "其他车":
//        }
//
//        return processRule(rule, starting_time, ending_time, time_type, new CommonCharge("小车", "", 15, 3, 1, 5, 1));
//    }
//
//    public List<ResultBean> processRule(int rule, Date starting_time, Date ending_time, String time_type, CommonCharge charge) {
//        List<ResultBean> list;
//        BasicRule basicRule = null;
//        switch (rule) {
//            case RuleUtil.RULE_BASIC:
//                basicRule = new Rule(starting_time, ending_time, charge);
//                list = basicRule.getDailyMap();
//                for (ResultBean resultBean : list) {
//                    System.out.println("Time = " + resultBean.getDate() + ", Total = " + resultBean.getTotal());
//                }
//                break;
//            case RuleUtil.RULE_CUSTOM:
//            case RuleUtil.RULE_PERSON:
//        }
//        switch (time_type) {
//            case RuleUtil.TYPE_RESULT_LIST_ONE:
//                return basicRule.getDailyMap();
//            case RuleUtil.TYPE_RESULT_LIST_TWO:
//                return basicRule.getMonthMap();
//            case RuleUtil.TYPE_RESULT_LIST_THREE:
//                return basicRule.getYearLMap();
//        }
//        return null;
//    }
    public void processRule(String interim_rule, String plate_type) {
        if (interim_rule.equals("false")) {
            switch (basicCharge.getNow_rule()) {
                case "私人规则":
                    personRule = new PersonRule(starting_time, ending_time, (PersonCharge) basicCharge);
                    break;
                case "基本规则":
                    rule = new Rule(starting_time, ending_time, (CommonCharge) basicCharge);
                case "自定义规则":

            }
        } else {
            switch (basicCharge.getNow_rule()) {
                case "私人规则":
                    personRule = new PersonRule(starting_time, ending_time, (PersonCharge) basicCharge);
                    break;
                case "基本规则":
                    rule = new Rule(starting_time, ending_time, (CommonCharge) basicCharge);
                case "自定义规则":

            }
        }

    }

    /**
     * 字符串转化成时间
     *
     * @param Date
     */
    public void processData(String Date) {
        Date = Date.trim();
        for (int i = 0; i < Date.length(); i++) {
            if (Date.charAt(i) == '到') {
                starting_time = DateUtil.process(Date.substring(0, i - 1));
                ending_time = DateUtil.process(Date.substring(i + 1, Date.length()));
            }
        }
    }

    /**
     * 处理收费类
     *
     * @param rule
     * @param car_type
     * @param zone_type
     */
    public void processCharge(int rule, String car_type, String zone_type) {
        switch (rule) {
            case 1:
                processCommonCharge(car_type, zone_type);
                break;
            case 2:
                processCustomCharge(car_type);
                break;
            case 3:
                processPersonCharge(car_type);
                break;
        }
    }

    public void processCommonCharge(String car_type, String zone_type) {
        if (car_type.equals("小车")) {
            switch (zone_type) {
                case "一类地区":
                    basicCharge = new CommonCharge("小车", "基本规则", ruleCommonBasicService.selectRowById("1").getMoney(),
                            ruleCommonBasicService.selectRowById("2").getMoney(), ruleCommonBasicService.selectRowById("3").getMoney(),
                            ruleCommonBasicService.selectRowById("4").getMoney(), ruleCommonBasicService.selectRowById("5").getMoney());
                    break;
                case "二类地区":
                    basicCharge = new CommonCharge("小车", "基本规则", ruleCommonBasicService.selectRowById("6").getMoney(),
                            ruleCommonBasicService.selectRowById("7").getMoney(), ruleCommonBasicService.selectRowById("8").getMoney(),
                            ruleCommonBasicService.selectRowById("9").getMoney(), ruleCommonBasicService.selectRowById("10").getMoney());
                    break;
                case "三类地区":
                    basicCharge = new CommonCharge("小车", "基本规则", ruleCommonBasicService.selectRowById("11").getMoney());
                    break;

            }
        }
        if (car_type.equals("大型车")) {
            switch (zone_type) {
                case "一类地区":
                    basicCharge = new CommonCharge("大型车", "基本规则", ruleCommonBasicService.selectRowById("12").getMoney(),
                            ruleCommonBasicService.selectRowById("13").getMoney(), ruleCommonBasicService.selectRowById("14").getMoney(),
                            ruleCommonBasicService.selectRowById("15").getMoney(), ruleCommonBasicService.selectRowById("16").getMoney());
                    break;
                case "二类地区":
                    basicCharge = new CommonCharge("大型车", "基本规则", ruleCommonBasicService.selectRowById("17").getMoney(),
                            ruleCommonBasicService.selectRowById("18").getMoney(), ruleCommonBasicService.selectRowById("19").getMoney(),
                            ruleCommonBasicService.selectRowById("20").getMoney(), ruleCommonBasicService.selectRowById("21").getMoney());
                    break;
                case "三类地区":
                    basicCharge = new CommonCharge("大型车", "基本规则", ruleCommonBasicService.selectRowById("22").getMoney());
                    break;

            }
        }
        if (car_type.equals("超大型车")) {
            switch (zone_type) {
                case "一类地区":
                    basicCharge = new CommonCharge("超大型车", "基本规则", ruleCommonBasicService.selectRowById("23").getMoney(),
                            ruleCommonBasicService.selectRowById("24").getMoney(), ruleCommonBasicService.selectRowById("25").getMoney(),
                            ruleCommonBasicService.selectRowById("26").getMoney(), ruleCommonBasicService.selectRowById("27").getMoney());
                    break;
                case "二类地区":
                    basicCharge = new CommonCharge("超大型车", "基本规则", ruleCommonBasicService.selectRowById("28").getMoney(),
                            ruleCommonBasicService.selectRowById("29").getMoney(), ruleCommonBasicService.selectRowById("30").getMoney(),
                            ruleCommonBasicService.selectRowById("31").getMoney(), ruleCommonBasicService.selectRowById("32").getMoney());
                    break;
                case "三类地区":
                    basicCharge = new CommonCharge("超大型车", "基本规则", ruleCommonBasicService.selectRowById("33").getMoney());
                    break;

            }
        }
        if (car_type.equals("其他车")) {
            switch (zone_type) {
                case "一类地区":
                    basicCharge = new CommonCharge("其他车", "基本规则", ruleCommonBasicService.selectRowById("34").getMoney());
                    break;
                case "二类地区":
                    basicCharge = new CommonCharge("其他车", "基本规则", ruleCommonBasicService.selectRowById("35").getMoney());
                    break;
                case "三类地区":
                    basicCharge = new CommonCharge("其他车", "基本规则", ruleCommonBasicService.selectRowById("36").getMoney());
                    break;
            }
        }
    }

    public void processCustomCharge(String car_type) {

    }

    public void processPersonCharge(String car_type) {
        switch (car_type) {
            case "小车":
                basicCharge = new PersonCharge("小车", "私人规则", rulePersonService.selectMoneyByCarType("小车"));
                break;
            case "大车":
                basicCharge = new PersonCharge("小车", "私人规则", rulePersonService.selectMoneyByCarType("大车"));
                break;
            case "超大型车":
                basicCharge = new PersonCharge("小车", "私人规则", rulePersonService.selectMoneyByCarType("超大型车"));
                break;
            case "其他车":
                basicCharge = new PersonCharge("小车", "私人规则", rulePersonService.selectMoneyByCarType("其他车"));
                break;
        }
    }

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
