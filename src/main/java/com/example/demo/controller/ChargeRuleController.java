package com.example.demo.controller;

import com.example.demo.bean.*;
import com.example.demo.entity.TbParkingSpace;
import com.example.demo.entity.TbRuleCommonBasic;
import com.example.demo.entity.TbRuleCustomInterim;
import com.example.demo.rule.*;
import com.example.demo.service.*;
import com.example.demo.util.DateUtil;
import com.example.demo.util.RuleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
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
    ParkingSpaceService parkingSpaceService;
    //规则类用于测试
    Rule rule;
    PersonRule personRule;
    CustomRule customRule;
    FixedRule fixedRule;
    List<InterimRule> interimRuleList = new ArrayList<>();
    BasicCharge basicCharge = new BasicCharge("", "");
    InterimCharge interimCharge;

    //开始时间结束时间
    Date ending_time = new Date();
    Date starting_time = new Date();

    @Async
    @RequestMapping(value = "/parkingTest", method = RequestMethod.POST)
    public String parkingTest(Model model, @RequestBody Map<String, String> map) {
        String rule = map.get("rule");
        String interim_rule = map.get("interim_rule");
        String fix_rule = map.get("fix_rule");
        String Date = map.get("Date");
        String time_type = map.get("time_type");
        String zone_type = map.get("zone_type");
        String plate_type = map.get("plate_type");
        String car_type = map.get("car_type");
        int searchNum = Integer.parseInt(map.get("searchNum").trim());//用于处理分页
        int page = Integer.parseInt(map.get("page")) - 1;
        if ("true".equals(fix_rule)) {
            for (int i = 0; i < plate_type.length(); i++) {
                if (plate_type.charAt(i) == ':') {
                    car_type = plate_type.substring(i + 1, plate_type.length());
                    plate_type = plate_type.substring(0, i - 1);
                }
            }
        }
        model.addAttribute("time_type", time_type);//用于处理视图
        processCharge(rule, car_type, zone_type);//根据条件从数据库获取收费规则
        processData(Date);//获得开始时间和结束时间
        processInterimRule(starting_time, ending_time, car_type);//加载临时规则
        if ("true".equals(fix_rule)) {
            processFixRule(ending_time, plate_type, car_type);
        }
        processRule(interim_rule, fix_rule);//加载规则
        List<ResultBean> list = processTimeType(rule, time_type);
        if (searchNum * (page + 1) < list.size()) {
            model.addAttribute("TestResult", list.subList(page * searchNum, (page + 1) * searchNum));
        } else {
            model.addAttribute("TestResult", list.subList(page * searchNum, list.size()));
        }
        model.addAttribute("TestTotalPage", (list.size() / searchNum) + 1);
        model.addAttribute("TestNowPage", page + 1);
        return "index-charge-rule::TestResult";
    }

    @Async
    public void processFixRule(Date ending_time, String plate_type, String car_type) {
        TbParkingSpace tbParkingSpace = parkingSpaceService.selectExpireDateByCar(plate_type).get(0);
        Date date = tbParkingSpace.getExpireDate();
        fixedRule = new FixedRule(date, new BasicCharge(car_type, "固定车位规则"));
    }


    @Async
    public void processInterimRule(Date starting_time, Date ending_time, String car_type) {
        this.interimRuleList = new ArrayList<>();
        while (starting_time.getTime() < ending_time.getTime()) {
            String d = DateUtil.getNowDate(starting_time);
            if (searchInterimList(starting_time) == -1) {
                if (ruleCustomInterimService.selectRuleExist(d) != 0) {
                    if (ruleCustomInterimService.selectAllExist(d) == 1) {
                        this.interimCharge = new InterimCharge(car_type, "临时规则", "全天制度",
                                DateUtil.process(d),
                                ruleCustomInterimService.selectMoneyByInfo(d, "全天", "小车") == null ? 0
                                        : ruleCustomInterimService.selectMoneyByInfo(d, "全天", "小车"));
                    } else {
                        this.interimCharge = new InterimCharge(car_type, "临时规则",
                                "小时制度", DateUtil.process(d),
                                ruleCustomInterimService.selectMoneyByPeakFirst(d, "小车"),
                                ruleCustomInterimService.selectMoneyByPeak(d, "小车"),
                                ruleCustomInterimService.selectMoneyByPlain(d, "小车"),
                                ruleCustomInterimService.selectMoneyByAll(d, "小车")
                        );
                    }
                    addInterimRuleList(DateUtil.getNowDate(d));
                }
            }
            starting_time = DateUtil.addDay(starting_time, ending_time);
        }
    }

    @Async
    public void addInterimRuleList(Date date) {
        if (searchInterimList(date) == -1) {
            this.interimRuleList.add(new InterimRule(this.interimCharge, date));
        }
    }

    @Async
    public int searchInterimList(Date date) {
        for (int i = 0; i < interimRuleList.size(); i++) {
            if (interimRuleList.get(i).getUse_date().getTime() == date.getTime()) {
                return i;
            }
        }
        return -1;
    }

    @Async
    public List<ResultBean> processTimeType(String rule, String time_type) {
        if (rule.equals("基本规则")) {
            switch (time_type) {
                case RuleUtil.TYPE_RESULT_LIST_ONE:
                    return this.rule.getDailyMap();
                case RuleUtil.TYPE_RESULT_LIST_TWO:
                    return this.rule.getMonthMap();
                case RuleUtil.TYPE_RESULT_LIST_THREE:
                    return this.rule.getYearLMap();
            }
        }
        if (rule.equals("自定义规则")) {
            switch (time_type) {
                case RuleUtil.TYPE_RESULT_LIST_ONE:
                    return this.customRule.getDailyMap();
                case RuleUtil.TYPE_RESULT_LIST_TWO:
                    return this.customRule.getMonthMap();
                case RuleUtil.TYPE_RESULT_LIST_THREE:
                    return this.customRule.getYearLMap();
            }
        }
        if (rule.equals("私人规则")) {
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


    public void processRule(String interim_rule, String fix_rule) {
        if (interim_rule.equals("true") && fix_rule.equals("true")) {
            switch (basicCharge.getNow_rule()) {
                case "私人规则":
                    personRule = new PersonRule(starting_time, ending_time, (PersonCharge) basicCharge, interimRuleList, fixedRule);
                    break;
                case "基本规则":
                    rule = new Rule(starting_time, ending_time, (CommonCharge) basicCharge, interimRuleList, fixedRule);
                    break;
                case "自定义规则":
//                    customRule = new CustomRule(starting_time, ending_time, (CustomCharge) basicCharge, interimRuleList, fixedRule);
                    break;
            }
        }
        if (interim_rule.equals("true") && fix_rule.equals("false")) {
            switch (basicCharge.getNow_rule()) {
                case "私人规则":
                    personRule = new PersonRule(starting_time, ending_time, (PersonCharge) basicCharge, interimRuleList);
                    break;
                case "基本规则":
                    rule = new Rule(starting_time, ending_time, (CommonCharge) basicCharge, interimRuleList);
                    break;
                case "自定义规则":
                    customRule = new CustomRule(starting_time, ending_time, (CustomCharge) basicCharge, interimRuleList);
                    break;
            }
        }
        if (interim_rule.equals("false") && fix_rule.equals("true")) {
            switch (basicCharge.getNow_rule()) {
                case "私人规则":
                    personRule = new PersonRule(starting_time, ending_time, (PersonCharge) basicCharge, fixedRule);
                    break;
                case "基本规则":

                    rule = new Rule(starting_time, ending_time, (CommonCharge) basicCharge, fixedRule);
                    break;
                case "自定义规则":
//                    customRule = new CustomRule(starting_time, ending_time, (CustomCharge) basicCharge, fixedRule);
                    break;
            }
        }
        if (interim_rule.equals("false") && fix_rule.equals("false")) {
            switch (basicCharge.getNow_rule()) {
                case "私人规则":
                    personRule = new PersonRule(starting_time, ending_time, (PersonCharge) basicCharge);
                    break;
                case "基本规则":
                    rule = new Rule(starting_time, ending_time, (CommonCharge) basicCharge);
                    break;
                case "自定义规则":
                    customRule = new CustomRule(starting_time, ending_time, (CustomCharge) basicCharge);
                    break;
            }
        }
    }

    /**
     * 字符串转化成时间
     *
     * @param Date
     */
    @Async
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
    @Async
    public void processCharge(String rule, String car_type, String zone_type) {
        switch (rule) {
            case "基本规则":
                processCommonCharge(car_type, zone_type);
                break;
            case "自定义规则":
                processCustomCharge(car_type);
                break;
            case "私人规则":
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
        switch (car_type) {
            case "小车":
                this.basicCharge = new CustomCharge("小车", "自定义规则",
                        ruleCustomService.selectMoneyByInfo("高峰前一个小时", "小车"),
                        ruleCustomService.selectMoneyByInfo("高峰普通时段", "小车"),
                        ruleCustomService.selectMoneyByInfo("非高峰时段", "小车"),
                        ruleCustomService.selectMoneyByInfo("全天最高上限", "小车"));
                break;
            case "大型车":
                this.basicCharge = new CustomCharge("大型车", "自定义规则",
                        ruleCustomService.selectMoneyByInfo("高峰前一个小时", "大型车"),
                        ruleCustomService.selectMoneyByInfo("高峰普通时段", "大型车"),
                        ruleCustomService.selectMoneyByInfo("非高峰时段", "大型车"),
                        ruleCustomService.selectMoneyByInfo("全天最高上限", "大型车"));
                break;
            case "超大型车":
                this.basicCharge = new CustomCharge("超大型车", "自定义规则",
                        ruleCustomService.selectMoneyByInfo("高峰前一个小时", "超大型车"),
                        ruleCustomService.selectMoneyByInfo("高峰普通时段", "超大型车"),
                        ruleCustomService.selectMoneyByInfo("非高峰时段", "超大型车"),
                        ruleCustomService.selectMoneyByInfo("全天最高上限", "超大型车"));
                break;
            case "其他车":
                this.basicCharge = new CustomCharge("其他车", "自定义规则",
                        ruleCustomService.selectMoneyByInfo("高峰前一个小时", "其他车"),
                        ruleCustomService.selectMoneyByInfo("高峰普通时段", "其他车"),
                        ruleCustomService.selectMoneyByInfo("非高峰时段", "其他车"),
                        ruleCustomService.selectMoneyByInfo("全天最高上限", "其他车"));
                break;
        }
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
                interim_car_type, interim_use_date);

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

    @Autowired
    public void setParkingSpaceService(ParkingSpaceService parkingSpaceService) {
        this.parkingSpaceService = parkingSpaceService;
    }
}
