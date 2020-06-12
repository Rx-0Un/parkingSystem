package com.example.demo.controller;

import com.example.demo.entity.TbCar;
import com.example.demo.entity.TbOrder;
import com.example.demo.entity.TbUser;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/index-data-manage")
public class DataManageController {
    CarService carService;
    UserService userService;
    ParkingRecordService parkingRecordService;
    ParkingLotSettingService parkingLotSettingService;
    ParkingSpaceService parkingSpaceService;
    RuleFixedParkingService ruleFixedParkingService;
    OrderService orderService;

    @RequestMapping(value = "/selectCarPage", method = RequestMethod.POST)
    public String selectCarPage(Model model, @RequestBody Map<String, String> map) {
        String search_keyword = map.get("search_keyword");
        String search_car_type = map.get("search_car_type");
        int pageNum = Integer.valueOf(map.get("pageNum"));
        int page = Integer.valueOf(map.get("page"));
        model.addAttribute("CarResult", carService.selectCarByStr(search_keyword, search_car_type, pageNum, (page - 1) * pageNum));
        model.addAttribute("CarNowPage", page);
        model.addAttribute("CarTotalPage", (carService.selectCarByStr(search_keyword, search_car_type, pageNum, page).size() / pageNum) + 1);
        return "index-data-manage::CarResult";
    }


    @RequestMapping(value = "/addCarByInfo", method = RequestMethod.POST)
    public String addCarByInfo(Model model, @RequestBody Map<String, String> map) {
        String car_type = map.get("car_type");
        String car_plate_number_head = map.get("car_plate_number_head");
        String car_plate_number = map.get("car_plate_number");
        String car_color = map.get("car_color");
        String car_type_model = map.get("car_type_model");
        String car_user = map.get("car_user");
        carService.addRowByInfo(car_type, car_plate_number_head + car_plate_number, car_color, car_type_model, Integer.parseInt(car_user.trim()));
        model.addAttribute("CarResult", carService.selectAll(0, 0));
        return "index-data-manage::CarResult";
    }

    @RequestMapping(value = "/selectUser")
    public String selectUser(Model model, @RequestBody Map<String, String> map) {
        String select = map.get("select");
        String Keyword = map.get("Keyword");
        model.addAttribute("UserSearch", userService.selectUser(select, Keyword));
        return "index-data-manage::UserSearch";
    }

    @RequestMapping(value = "/addParkingSpaceBefore")
    public String addParkingSpaceBefore(Model model) {
        int spaceNum = parkingLotSettingService.selectRowById(2).get(0).getSpaceNumber();
        int fixParkingSpace = parkingSpaceService.selectAll(0, 0) == null ? 0 : parkingSpaceService.selectAll(0, 0).size();
        int interimParkingSpace = spaceNum - fixParkingSpace;
        model.addAttribute("spaceNum", spaceNum);
        model.addAttribute("interimParkingSpace", interimParkingSpace);
        model.addAttribute("fixParkingSpace", fixParkingSpace);
        return "index-data-manage::SpaceNumberResult";
    }

    @RequestMapping(value = "/addParkingSpace")
    public String addParkingSpace(Model model, @RequestBody Map<String, String> map) {
        String parkingSpaceArea = map.get("parkingSpaceArea");
        parkingSpaceService.addParkingSpace(parkingSpaceArea);
        model.addAttribute("ParkingSpaceResult", parkingSpaceService.selectAll(10, 0));
        return "index-data-manage::ParkingSpaceResult";
    }

    @RequestMapping(value = "/selectParkingSpacePage")
    public String selectParkingSpacePage(Model model, @RequestBody Map<String, String> map) {
        String parking_space_searchDate = map.get("parking_space_searchDate");
        String parking_space_key_word = map.get("parking_space_key_word");
        String parking_space_key_word_title = map.get("parking_space_key_word_title");
        int pageNum = Integer.valueOf(map.get("parking_space_searchNum"));
        int page = Integer.valueOf(map.get("page"));
        model.addAttribute("ParkingSpaceResult", parkingSpaceService.selectAllByFurryStr(parking_space_key_word_title, parking_space_key_word, parking_space_searchDate, pageNum, (page - 1) * pageNum));
        List list = parkingSpaceService.selectAllByFurryStr(parking_space_key_word_title, parking_space_key_word, parking_space_searchDate, 0, 0);
        model.addAttribute("ParkingSpaceTotalPage", list.size() / 10);
        model.addAttribute("ParkingSpaceNowPage", page);
        return "index-data-manage::ParkingSpaceResult";
    }

    @RequestMapping(value = "/selectUserCar")
    public String selectUserCar(Model model, @RequestBody Map<String, String> map) {
        String userId = map.get("userId");
        List<TbCar> list = carService.selectCarByUserId(userId);
        model.addAttribute("UserCar", list);
        model.addAttribute("ParkingSpace", parkingSpaceService.selectNotOccupy());
//            model.addAttribute("FixedRule", ruleFixedParkingService.selectRuleByCarType(list.get(0).getCarTypeModel()));
        model.addAttribute("FixedRule", ruleFixedParkingService.selectAll());
        return "index-data-manage::UserCarResult";
    }

    @RequestMapping(value = "/addOrder")
    public String addOrder(Model model, @RequestBody Map<String, String> map) {
        String parkingSpace = map.get("parkingSpace");
        String OrderMoneyText = map.get("OrderMoneyText");
        String carPlateNumberValue = map.get("carPlateNumberValue");
        String userName = map.get("userName");
        String staffName = map.get("staffName");
        float money = 0;
        String cycle = new String();
        for (int i = 0; i < OrderMoneyText.length(); i++) {
            if (carPlateNumberValue.indexOf(i) == '/') {
                money = Integer.parseInt(OrderMoneyText.substring(0, i - 1));
                cycle = carPlateNumberValue.substring(i + 1, OrderMoneyText.length());
            }
        }
        TbOrder tbOrder = new TbOrder();
        tbOrder.setOrderAmount(money);
        tbOrder.setOrderReceiver(userName);
        tbOrder.setOrderPayer(staffName);
        tbOrder.setOrderState("未完成");
        tbOrder.setOrderPayType("手机支付");
        tbOrder.setOrderPurchaseType("固定车位购买");
        if (orderService.addRowByInfo(tbOrder) != 0) {
            parkingSpaceService.updateParkingSpace(carPlateNumberValue, cycle, parkingSpace);
            carService.updateCarType(carPlateNumberValue);
        }
        model.addAttribute("ParkingSpaceResult", parkingSpaceService.selectAll(10, 0));
        return "index-data-manage::ParkingSpaceResult";
    }

    @RequestMapping(value = "/selectRecordPage")
    public String selectRecordPage(Model model, @RequestBody Map<String, String> map) {
        String record_search__starting_time = map.get("record_search__starting_time");
        String record_search__ending_time = map.get("record_search__ending_time");
        String record_search_keyword_title = map.get("record_search_keyword_title");
        String record_search__keyword = map.get("record_search__keyword");
        Integer page = Integer.valueOf(map.get("page"));
        Integer record_search_pageNum = Integer.valueOf(map.get("record_search_pageNum"));
        model.addAttribute("RecordResult", parkingRecordService.selectAllByStr(record_search__starting_time, record_search__ending_time, record_search_keyword_title,record_search__keyword, record_search_pageNum, page));
        model.addAttribute("RecordNowPage", page);
        model.addAttribute("RecordTotalPage", (parkingRecordService.selectAllByStr(record_search__starting_time, record_search__ending_time, record_search_keyword_title,record_search__keyword, record_search_pageNum, page).size() / 10) + record_search_pageNum);
        return "index-data-manage::RecordResult";
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    @Autowired
    public void setParkingLotSettingService(ParkingLotSettingService parkingLotSettingService) {
        this.parkingLotSettingService = parkingLotSettingService;
    }

    @Autowired
    public void setParkingSpaceService(ParkingSpaceService parkingSpaceService) {
        this.parkingSpaceService = parkingSpaceService;
    }

    @Autowired
    public void setRuleFixedParkingService(RuleFixedParkingService ruleFixedParkingService) {
        this.ruleFixedParkingService = ruleFixedParkingService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setParkingRecordService(ParkingRecordService parkingRecordService) {
        this.parkingRecordService = parkingRecordService;
    }
}
