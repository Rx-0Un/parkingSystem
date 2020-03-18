package com.example.demo.controller;

import com.example.demo.entity.TbStaff;
import com.example.demo.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/index-duty-statistics")
public class DutyManageController {
    StaffService staffService;

    @RequestMapping(value = "/selectStaff", method = RequestMethod.POST)
    public String selectStaff(Model model, @RequestBody Map<String, String> map) {
        String select = map.get("select");
        String Keyword = map.get("Keyword");
        System.out.println(select+""+Keyword);
        List<Map<String, Object>> list = processSelectAndKeyword(select, Keyword);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
        System.out.println("!!!");
        model.addAttribute("allStaff", list);

        return "index-duty-statistics::staffSearch";
    }

    public List<Map<String, Object>> processSelectAndKeyword(String select, String Keyword) {
        if (Keyword != null&&!Keyword.isEmpty()) {
            switch (select) {
                case "全体":
                    return staffService.selectFuzzyAllIdAndName(Keyword);
                case "编号":
                    return staffService.selectFuzzyAllId(Keyword);
                case "姓名":
                    return staffService.selectFuzzyAllName(Keyword);
            }
        }
        return staffService.selectAllIdAndName();
    }

    @Autowired
    public void setStaffService(StaffService staffService) {
        this.staffService = staffService;
    }
}
