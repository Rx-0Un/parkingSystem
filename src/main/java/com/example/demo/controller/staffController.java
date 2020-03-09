package com.example.demo.controller;

import com.example.demo.entity.TbStaff;
import com.example.demo.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class staffController {

    StaffService staffService;

    @RequestMapping(value = "/index-staff")
    public String getAll(Model model) {
        model.addAttribute("allStaff", staffService.selectAll());
        return "index-staff";
    }

    @RequestMapping(value = "/selectStaffByCondition")
    public String selectStaffByCondition(Model model, String select, @RequestParam(value = "Keyword") String Keyword, String checkbox1, String checkbox2, String checkbox3, String checkbox4, String checkbox5, String checkbox6) {
        String type = new String();
        type += processType(checkbox1);
        type += processType(checkbox2);
        type += processType(checkbox3);
        type += processType(checkbox4);
        type += processType(checkbox5);
        type += processType(checkbox6);

        System.out.println("Keyword:"+Keyword);
        System.out.println("select:"+select);
        System.out.println("type:"+type);

        model.addAttribute("allStaff",prossType(select,Keyword));
        model.addAttribute("checkbox1", checkbox1);
        model.addAttribute("checkbox1", checkbox2);
        model.addAttribute("checkbox1", checkbox3);
        model.addAttribute("checkbox1", checkbox4);
        model.addAttribute("checkbox1", checkbox5);
        model.addAttribute("checkbox1", checkbox6);
        return "index-staff";
    }

    public String processType(String string) {
        if (string == null || string.isEmpty()) {
            return "0";
        }
        return "1";
    }

    public List<TbStaff> prossType(String select, String Keyword) {
        switch (select) {
            case "全体":
                return staffService.selectByFuzzyStr(Keyword);
            case "编号":
                return staffService.selectByFuzzyId(Keyword);
            case "姓名":
                return staffService.selectByFuzzyName(Keyword);
            case "电话":
                return staffService.selectByFuzzyPhone(Keyword);
            default:
                break;
        }
        return null;
    }


    @Autowired
    public void setStaffService(StaffService staffService) {
        this.staffService = staffService;
    }
}
