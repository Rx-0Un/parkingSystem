package com.example.demo.controller;

import com.example.demo.bean.BasicCharge;
import com.example.demo.bean.CommonCharge;
import com.example.demo.bean.PersonCharge;
import com.example.demo.bean.ResultBean;
import com.example.demo.rule.BasicRule;
import com.example.demo.rule.CustomRule;
import com.example.demo.rule.PersonRule;
import com.example.demo.rule.Rule;
import com.example.demo.service.RuleCommonBasicService;
import com.example.demo.service.RulePersonService;
import com.example.demo.util.DateUtil;
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

@Controller("RuleController")
public class RuleController {
    RuleCommonBasicService ruleCommonBasicService;
    RulePersonService rulePersonService;
    //规则类用于测试
    BasicRule basicRule;
    BasicCharge basicCharge;

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
        String searchNum = map.get("searchNum");//用于处理分页
        String zone_type = map.get("zone_type");
        model.addAttribute("time_type", time_type);//用于处理视图
        processCharge(rule, car_type, zone_type);
        processData(Date);
        processRule(interim_rule, plate_type);

        model.addAttribute("TestResult", processTimeType(time_type));
        return "index-charge-rule::TestResult";
    }

    public List<ResultBean> processTimeType(String time_type) {
        switch (time_type) {
            case RuleUtil.TYPE_RESULT_LIST_ONE:
                return basicRule.getDailyMap();
            case RuleUtil.TYPE_RESULT_LIST_TWO:
                return basicRule.getMonthMap();
            case RuleUtil.TYPE_RESULT_LIST_THREE:
                return basicRule.getYearLMap();
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
        switch (basicCharge.getNow_rule()) {
            case "私人规则":
                basicRule = new PersonRule(starting_time, ending_time, (PersonCharge) basicCharge);
                break;
            case "基本规则":
                basicRule = new Rule(starting_time, ending_time, (CommonCharge) basicCharge);
            case "自定义规则":

        }

        if (interim_rule.equals("true")) {

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
                    basicCharge = new CommonCharge("小车", "一个小时制度", ruleCommonBasicService.selectRowById("1").getMoney(),
                            ruleCommonBasicService.selectRowById("2").getMoney(), ruleCommonBasicService.selectRowById("3").getMoney(),
                            ruleCommonBasicService.selectRowById("4").getMoney(), ruleCommonBasicService.selectRowById("5").getMoney());
                    break;
                case "二类地区":
                    basicCharge = new CommonCharge("小车", "一个小时制度", ruleCommonBasicService.selectRowById("6").getMoney(),
                            ruleCommonBasicService.selectRowById("7").getMoney(), ruleCommonBasicService.selectRowById("8").getMoney(),
                            ruleCommonBasicService.selectRowById("9").getMoney(), ruleCommonBasicService.selectRowById("10").getMoney());
                    break;
                case "三类地区":
                    basicCharge = new CommonCharge("小车", "全天制", ruleCommonBasicService.selectRowById("11").getMoney());
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
                basicCharge = new PersonCharge("小车", "私人规则", rulePersonService.selectRowByCarType("小车"));
                break;
            case "大车":
                basicCharge = new PersonCharge("小车", "私人规则", rulePersonService.selectRowByCarType("大车"));
                break;
            case "超大型车":
                basicCharge = new PersonCharge("小车", "私人规则", rulePersonService.selectRowByCarType("超大型车"));
                break;
            case "其他车":
                basicCharge = new PersonCharge("小车", "私人规则", rulePersonService.selectRowByCarType("其他车"));
                break;
        }
    }

    @Autowired
    public void setRuleCommonBasicService(RuleCommonBasicService ruleCommonBasicService) {
        this.ruleCommonBasicService = ruleCommonBasicService;
    }

    @Autowired
    public void setRulePersonService(RulePersonService rulePersonService) {
        this.rulePersonService = rulePersonService;
    }
}
