package com.example.demo.controller;

import com.example.demo.bean.*;
import com.example.demo.entity.*;
import com.example.demo.rule.*;
import com.example.demo.service.*;
import com.example.demo.util.DateUtil;
import com.example.demo.util.FileUtil;
import org.apache.ibatis.binding.BindingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/index-manage-parking")
public class ParkingManageController {
    ParkingRecordService parkingRecordService;
    CarService carService;
    OrderService orderService;
    StaffTaskService staffTaskService;
    StaffDutyService staffDutyService;
    StaffService staffService;
    ParkingLotSettingService parkingLotSettingService;
    ParkingSpaceService parkingSpaceService;
    RuleCommonBasicService ruleCommonBasicService;
    RulePersonService rulePersonService;
    RuleCustomService ruleCustomService;
    RuleCustomInterimService ruleCustomInterimService;
    RuleFixedParkingService ruleFixedParkingService;
    //计费
    FixedRule fixedRule;
    Rule rule;
    PersonRule personRule;
    CustomRule customRule;
    BasicCharge basicCharge;
    InterimCharge interimCharge;
    List<InterimRule> interimRuleList;

    @RequestMapping(value = "/addParkingRecordEnter", method = RequestMethod.POST)
    public String addParkingRecordEnter(Model model, @RequestBody Map<String, String> map, HttpSession session) {
        String car_type = map.get("car_type");
        String car_type_model = map.get("car_type_model");
        String car_user = map.get("car_user");
        String car_plate_num_head = map.get("car_plate_num_head");
        String car_plate_num = map.get("car_plate_num");
        String car_color = map.get("car_color");
        System.out.println(map.toString());
        String car_plate_number = car_plate_num_head + car_plate_num;
        carService.addRowByInfo(car_type, car_plate_number, car_color, car_type_model, processCarUser(car_user));
        parkingRecordService.addRowByInfo(car_plate_number, new Date());
        String starting_date = staffDutyService.selectStartingTime();
        model.addAttribute("EnterResult", parkingRecordService.selectEnter(starting_date, 10, 0));
        return "index-manage-parking::EnterResult";
    }

    public int processCarUser(String car_user) {
        if (car_user.equals("临时车主")) {
            return 0;
        }
        return Integer.parseInt(car_user);
    }

    @RequestMapping(value = "/addParkingOrderOuter", method = RequestMethod.POST)
    public String addParkingOrderOuter(Model model, @RequestBody Map<String, String> map, HttpSession session) {
        String order_car_number = map.get("order_car_number");
        String order_pay_type = map.get("order_pay_type");
        String order_payer = map.get("order_payer");
        String order_receiver = map.get("order_receiver");
        String money = map.get("money");
        System.out.println(map.toString());
        TbOrder tbOrder = new TbOrder();
        tbOrder.setOrderAmount(Float.parseFloat(money));
        tbOrder.setOrderPayer(order_payer);
        tbOrder.setOrderReceiver(order_receiver);
        tbOrder.setOrderState("已完成");
        tbOrder.setOrderPayType(order_pay_type);
        tbOrder.setOrderPurchaseType("停车场支付");
        orderService.addRowByInfo(tbOrder);
        String starting_date = staffDutyService.selectStartingTime();
        System.out.println(parkingRecordService.updateByOuter(order_car_number, starting_date, new Date(), tbOrder.getOrderId()));
        model.addAttribute("OuterResult", parkingRecordService.selectOuter(starting_date, 10, 0));
        return "index-manage-parking::OuterResult";
    }

    @RequestMapping(value = "/updateDutyOffWork", method = RequestMethod.GET)
    public String updateDutyOffWork(Model model, HttpSession session) {
        String startTime = (String) session.getAttribute("startTime");
        model.addAttribute("ending_time", DateUtil.getNowDate());
        model.addAttribute("enterCount", parkingRecordService.selectCountEnter(startTime));
        model.addAttribute("outerCount", parkingRecordService.selectCountOuter(startTime));
        model.addAttribute("orderCount", orderService.selectRowCount(startTime));
        try {
            model.addAttribute("TotalCount", orderService.selectTotalCount(startTime));
        } catch (BindingException e) {
            model.addAttribute("TotalCount", 0);
        }

        model.addAttribute("OffWorkResult", parkingRecordService.selectDutyAll(startTime, 10, 0));
        return "index-manage-parking::OffWorkResult";
    }

    @RequestMapping(value = "/commitDutyOffWork", method = RequestMethod.POST)
    public String commitDutyOffWork(Model model, @RequestBody Map<String, String> map, HttpSession session) {
        String startTime = (String) session.getAttribute("startTime");
        String endTime = DateUtil.getNowDate();
        staffDutyService.updateRowByInf0(endTime);
        TbStaffDuty tbStaffDuty = staffDutyService.selectLastRow();
        String enterCount = String.valueOf(parkingRecordService.selectCountEnter(startTime));
        String outerCount = String.valueOf(parkingRecordService.selectCountOuter(startTime));
        String orderCount;
        String TotalCount;
        try {
            orderCount = String.valueOf(orderService.selectRowCount(startTime));
            TotalCount = String.valueOf(orderService.selectTotalCount(startTime));
        } catch (Exception e) {
            orderCount = "0";
            TotalCount = "0";
        }
        Integer staffId = (Integer) session.getAttribute("staffId");//职员编号
        String staffName = (String) session.getAttribute("staffName");//职员姓名
        session.removeAttribute("nowStaff");
        session.removeAttribute("startTime");
        FileUtil.exportExcel(parkingRecordService.selectDutyAll(startTime, 10, 0), enterCount, outerCount, orderCount, TotalCount, new TbStaffDuty(), staffId, staffName);
        return "index-manage-parking";
    }

    @RequestMapping(value = "/addDutyRow", method = RequestMethod.GET)
    public String addDutyRow(Model model, HttpSession session) {
        String date = DateUtil.getNowDate();
        //获取当前操作员信息
        Integer staffId = (Integer) session.getAttribute("staffId");
        Integer staff_task_id = staffTaskService.selectTaskToday(staffId, date);
        if (staff_task_id != null) {
            staffDutyService.addRowByInfo(staffId, date, staff_task_id);
            model.addAttribute("info", "1");
            session.setAttribute("startTime", staffDutyService.selectStartingTime());
            session.setAttribute("staffId", staffId);
            session.setAttribute("nowStaff", staffService.selectNameById(staffId));
        } else {
            model.addAttribute("info", "0");
        }
        return "index-manage-parking";
    }

    @RequestMapping(value = "/selectRecordAndOrderByPage", method = RequestMethod.POST)
    public String selectRecordAndOrderByPage(Model model, @RequestBody Map<String, String> map, HttpSession session) {
        String starting_date = staffDutyService.selectStartingTime();
        String search_car_plate_type = map.get("search_car_plate_type");
        String search_car_type = map.get("search_car_type");
        String search_pay_type = map.get("search_pay_type");
        String search_Keyword_title = map.get("search_Keyword_title");
        int page = Integer.parseInt(map.get("page"));
        int pageNum = Integer.parseInt(map.get("search_number"));
        model.addAttribute("RecordAndOrderResult", parkingRecordService.selectDutyAll(starting_date, pageNum, page));
        return "index-manage-parking::RecordAndOrderResult";
    }

    @Async
    @RequestMapping(value = "/countCharge", method = RequestMethod.POST)
    public String countCharge(Model model, @RequestBody Map<String, String> map, HttpSession session) {
        String order_car_number = map.get("order_car_number");
        Date ending_time = new Date();
        TbParkingRecord tbParkingRecord = parkingRecordService.selectRowByCarNum(order_car_number).get(0);
        Date starting_time = tbParkingRecord.getEnterTime();
        TbParkingLot tbParkingLot = parkingLotSettingService.selectRowById(2).get(0);
        String car_type = tbParkingRecord.getTbCar().getCarTypeModel();
        String rule = processRuleType(tbParkingLot.getRuleSetting().substring(0, 3));
        String interimRule = tbParkingLot.getRuleSetting().substring(3, 4);
        processCharge(rule, car_type, tbParkingLot.getParkingLotLevel());
        if (interimRule.equals("1")) {
            processInterimRule(starting_time, ending_time, car_type);
        }
        processFixRule(ending_time, order_car_number, car_type);
        processRule(starting_time, ending_time, interimRule);//加载规则
        switch (rule) {
            case "基本规则":
                model.addAttribute("Amount", this.rule.total);break;
            case "自定义规则":
                model.addAttribute("Amount", this.customRule.total);break;
            case "私人规则":
                model.addAttribute("Amount", this.personRule.total);break;
        }
        return "index-manage-parking::chargeResult";
    }

    public void processRule(Date starting_time, Date ending_time, String interim_rule) {
        if (interim_rule.equals("1")) {
            switch (basicCharge.getNow_rule()) {
                case "私人规则":
                    personRule = new PersonRule(starting_time, ending_time, (PersonCharge) basicCharge, interimRuleList, fixedRule);
                    break;
                case "基本规则":
                    rule = new Rule(starting_time, ending_time, (CommonCharge) basicCharge, interimRuleList, fixedRule);
                    break;
                case "自定义规则":
                    customRule = new CustomRule(starting_time, ending_time, (CustomCharge) basicCharge, interimRuleList, fixedRule);
                    break;
            }
        }
        else  {
            switch (basicCharge.getNow_rule()) {
                case "私人规则":
                    personRule = new PersonRule(starting_time, ending_time, (PersonCharge) basicCharge, fixedRule);
                    break;
                case "基本规则":

                    rule = new Rule(starting_time, ending_time, (CommonCharge) basicCharge, fixedRule);
                    break;
                case "自定义规则":
                    customRule = new CustomRule(starting_time, ending_time, (CustomCharge) basicCharge, fixedRule);
                    break;
            }
        }
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
    public int searchInterimList(Date date) {
        for (int i = 0; i < interimRuleList.size(); i++) {
            if (interimRuleList.get(i).getUse_date().getTime() == date.getTime()) {
                return i;
            }
        }
        return -1;
    }

    @Async
    public void addInterimRuleList(Date date) {
        if (searchInterimList(date) == -1) {
            this.interimRuleList.add(new InterimRule(this.interimCharge, date));
        }
    }

    @Async
    public void processFixRule(Date ending_time, String plate_type, String car_type) {
        try {
            TbParkingSpace tbParkingSpace = parkingSpaceService.selectExpireDateByCar(plate_type).get(0);
            Date date = tbParkingSpace.getExpireDate();
            fixedRule = new FixedRule(date, new BasicCharge(car_type, "固定车位规则"));
        }catch (Exception e){
            fixedRule=null;
        }
    }

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

    @Autowired
    public void setParkingRecordService(ParkingRecordService parkingRecordService) {
        this.parkingRecordService = parkingRecordService;
    }

    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setStaffTaskService(StaffTaskService staffTaskService) {
        this.staffTaskService = staffTaskService;
    }

    @Autowired
    public void setStaffDutyService(StaffDutyService staffDutyService) {
        this.staffDutyService = staffDutyService;
    }

    @Autowired
    public void setStaffService(StaffService staffService) {
        this.staffService = staffService;
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

    @Autowired
    public void setRuleCustomInterimService(RuleCustomInterimService ruleCustomInterimService) {
        this.ruleCustomInterimService = ruleCustomInterimService;
    }

    @Autowired
    public void setRuleFixedParkingService(RuleFixedParkingService ruleFixedParkingService) {
        this.ruleFixedParkingService = ruleFixedParkingService;
    }

    @Autowired
    public void setParkingLotSettingService(ParkingLotSettingService parkingLotSettingService) {
        this.parkingLotSettingService = parkingLotSettingService;
    }

    @Autowired
    public void setParkingSpaceService(ParkingSpaceService parkingSpaceService) {
        this.parkingSpaceService = parkingSpaceService;
    }
}
