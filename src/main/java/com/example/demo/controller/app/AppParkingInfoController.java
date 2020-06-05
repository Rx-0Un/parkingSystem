package com.example.demo.controller.app;

import com.example.demo.bean.*;
import com.example.demo.entity.TbParkingLot;
import com.example.demo.rule.InterimRule;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/App/ParkingInfo")
public class AppParkingInfoController {
    ParkingLotSettingService parkingLotSettingService;
    ParkingRecordService parkingRecordService;
    BasicCharge basicCharge;
    RuleCommonBasicService ruleCommonBasicService;
    RulePersonService rulePersonService;
    RuleCustomService ruleCustomService;

    @RequestMapping(value = "/selectParkingLotInfo")
    public ParkingInfoResult selectParkingInfo() {
        TbParkingLot tbParkingLot = parkingLotSettingService.selectRowById(2).get(0);
        String rule = processRuleType(tbParkingLot.getRuleSetting().substring(0, 3));
        BasicCharge charge1 = processCharge(rule, "小车", tbParkingLot.getParkingLotLevel());
        BasicCharge charge2 = processCharge(rule, "大型车", tbParkingLot.getParkingLotLevel());
        BasicCharge charge3 = processCharge(rule, "超大型车", tbParkingLot.getParkingLotLevel());
        BasicCharge charge4 = processCharge(rule, "其他", tbParkingLot.getParkingLotLevel());
        int occupy = parkingRecordService.selectOccupyNum();
        String parkingInfo = processParkingInfo(tbParkingLot.getSpaceNumber(), occupy);

        List<BasicCharge> list = new ArrayList<>();
        list.add(charge1);
        list.add(charge2);
        list.add(charge3);
        list.add(charge4);
        ParkingInfoResult parkingInfoResult = new ParkingInfoResult();
        switch (charge1.getNow_rule()) {
            case "基本规则":
                parkingInfoResult.setList1(list);
                break;
            case "自定义规则":
                parkingInfoResult.setList2(list);
                break;
            case "私人规则":
                parkingInfoResult.setList3(list);
                break;
        }
        parkingInfoResult.setParkingName(tbParkingLot.getParkingLotName());
        parkingInfoResult.setParkingSpaceNum(String.valueOf(tbParkingLot.getSpaceNumber() - occupy));
        parkingInfoResult.setParkingInfo(parkingInfo);
        return parkingInfoResult;
    }

    public String processParkingInfo(int parkingSpaceNum, int occupy) {
        float count = (parkingSpaceNum - occupy) / parkingSpaceNum;
//        if (count > 0.8) {
//            return "车场位置空旷";
//        }
//        if (count < 0.1) {
//            return "车位紧张";
//        }
//        if (count < 0.8 && count > 0.1) {
//            return "车场位置充足";
//        }
//        return null;
        return "车场位置空旷";
    }


    public String processRuleType(String rule) {
        switch (rule) {
            case "100":
                return "基本规则";
            case "010":
                return "自定义规则";
            case "001":
                return "私人规则";
        }
        return "";
    }

    public BasicCharge processCharge(String rule, String car_type, String zone_type) {
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
        return basicCharge;
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

    @Autowired
    public void setParkingLotSettingService(ParkingLotSettingService parkingLotSettingService) {
        this.parkingLotSettingService = parkingLotSettingService;
    }

    @Autowired
    public void setParkingRecordService(ParkingRecordService parkingRecordService) {
        this.parkingRecordService = parkingRecordService;
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
    public void setRuleCustomService(RuleCustomService ruleCustomService) {
        this.ruleCustomService = ruleCustomService;
    }

}
