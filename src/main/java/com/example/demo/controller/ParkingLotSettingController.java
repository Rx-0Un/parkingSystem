package com.example.demo.controller;

import com.example.demo.service.ParkingLotSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping(value = "/index-parking-lot-setting")
public class ParkingLotSettingController {

    ParkingLotSettingService parkingLotSettingService;

    @RequestMapping(value = "/addParkingSetting", method = RequestMethod.POST)
    public String addParkingSetting(Model model, @RequestBody Map<String, String> map) {
        String parking_lot_name = map.get("parking_lot_name");
        String zone_type = map.get("zone_type");
        String parking_space_number = map.get("parking_space_number");
        String charge_open = map.get("charge_open");
        String account_open = map.get("account_open");
        String coupon_open = map.get("coupon_open");
        String fixed_past = map.get("fixed_past");
        String nobody = map.get("nobody");
        String forbid_out = map.get("forbid_out");
        String save_data = map.get("save_data");
        String print_open = map.get("print_open");
        String commonRule = map.get("commonRule");
        String customRule = map.get("customRule");
        String personRule = map.get("personRule");
        String interimRule = map.get("interimRule");
        String pay_setting = processPaySetting(charge_open, account_open, coupon_open, fixed_past, nobody, forbid_out, save_data);
        String print_setting = processStr(print_open);
        String rule_setting = processRuleSetting(commonRule, customRule, personRule, interimRule);
        int space_number=Integer.parseInt(parking_space_number);
        System.out.println(space_number);
        parkingLotSettingService.addRowByInfo(parking_lot_name,space_number,zone_type,pay_setting,print_setting,rule_setting);
        return "index-parking-lot-setting";
    }

    public String processPaySetting(String charge_open, String account_open, String coupon_open, String fixed_past, String nobody, String forbid_out, String save_data) {
        String pay_setting = new String();
        pay_setting += processStr(charge_open);
        pay_setting += processStr(account_open);
        pay_setting += processStr(coupon_open);
        pay_setting += processStr(fixed_past);
        pay_setting += processStr(nobody);
        pay_setting += processStr(forbid_out);
        pay_setting += processStr(save_data);
        return pay_setting;
    }

    public String processStr(String string) {
        if (string.equals("false")) {
            return "0";
        }
        return "1";
    }

    public String processRuleSetting(String commonRule, String customRule, String personRule, String interimRule) {
        String rule_setting = new String();
        rule_setting += processStr(commonRule);
        rule_setting += processStr(customRule);
        rule_setting += processStr(personRule);
        rule_setting += processStr(interimRule);
        return rule_setting;
    }

    @Autowired
    public void setParkingLotSettingService(ParkingLotSettingService parkingLotSettingService) {
        this.parkingLotSettingService = parkingLotSettingService;
    }
}
