package com.example.demo.controller;

import com.example.demo.entity.TbStaff;
import com.example.demo.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/index-staff")
public class StaffController {

    StaffService staffService;


    @RequestMapping(value = "/selectStaffByCondition",method = RequestMethod.POST)
    public String selectStaffByCondition(Model model, @RequestBody Map<String,String> map) {
        String select=map.get("select");
        String keyword=map.get("keyword");
        String checkbox1=map.get("check1");
        String checkbox2=map.get("check2");
        String checkbox3=map.get("check3");
        String checkbox4=map.get("check4");
        String checkbox5=map.get("check5");
        String checkbox6=map.get("check6");
        String type = new String();
        type += processType(checkbox1);
        type += processType(checkbox2);
        type += processType(checkbox3);
        type += processType(checkbox4);
        type += processType(checkbox5);
        type += processType(checkbox6);
        List<TbStaff> list = prossType(select, keyword, type);
        model.addAttribute("allStaff", list);
        return "index-staff::result";
    }

    public String processType(String string) {
        if (string.equals("false")) {
            return "0";
        }
        return "1";
    }

    public List<TbStaff> prossType(String select, String Keyword, String type) {
        if (Keyword != null && type.equals("000000")) {
            switch (select) {
                case "全体":
                    return staffService.selectByFuzzyStr(Keyword);
                case "编号":
                    return staffService.selectByFuzzyId(Keyword);
                case "姓名":
                    return staffService.selectByFuzzyName(Keyword);
                case "电话":
                    return staffService.selectByFuzzyPhone(Keyword);
            }
        }
        if (Keyword != null && !type.equals("000000")) {
            switch (select) {
                case "全体":
                    return staffService.selectByFuzzyStrAndType(Keyword, type);
                case "编号":
                    return staffService.selectByFuzzyIdAndType(Keyword, type);
                case "姓名":
                    return staffService.selectByFuzzyNameAndType(Keyword, type);
                case "电话":
                    return staffService.selectByFuzzyPhoneAndType(Keyword, type);
                default:
                    break;
            }
        }
        return staffService.selectAll();
    }

    public String prossSearchInFo(String select, String keyword) {
        String str = "查找目标:";
        str += select;
        if (keyword == null || keyword.isEmpty()) {
            str += "    关键字:  无　";
        } else {
            str += "    关键字:" + keyword;
        }
        str += "    查找结果:";
        return str;
    }

    @RequestMapping(value = "/deleteStaffById")
    public String deleteStaffById(Integer id, Model model) {
        System.out.println("controller:" + id);
        staffService.deleteStaffById(id);

        model.addAttribute("allStaff", staffService.selectAll());
        return "index-staff::result";
    }

    @RequestMapping(value = "/addStaff")
    public String addStaff(Model model, @RequestBody Map<String, String> map) {
        String staffName = map.get("staffName");
        String staffPhone = map.get("staffPhone");
        String type = new String();
        for (int i = 0; i < 6; i++) {
            if (map.get("rightCheck" + i) == "false") {
                type += "0";
            } else {
                type += "1";
            }
        }
        System.out.println("新员工姓名:" + staffName + "电话:" + staffPhone + "类型:" + type);
        int count = staffService.insertStaff(staffName, staffPhone, type);

        model.addAttribute("allStaff", staffService.selectAll());
        return "index-staff::result";
    }


    @Autowired
    public void setStaffService(StaffService staffService) {
        this.staffService = staffService;
    }
}
