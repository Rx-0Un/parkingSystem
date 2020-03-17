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
        List<Map<String,Object>> list = staffService.selectAllIdAndName();
        for (int i=0;i<list.size();i++){
            System.out.println(list.get(i).toString());
        }
        model.addAttribute("allStaff", list);

        return "index-duty-statistics::staffSearch";
    }

    public List<TbStaff> processSelectAndKeyword(String select, String Keyword) {
        switch (select) {
            case "全体":
                staffService.selectAll();
            case "编号":
                staffService.selectByFuzzyId(Keyword);
            case "姓名":
                staffService.selectByStaffName(Keyword);
        }
        return staffService.selectAll();
    }

    @Autowired
    public void setStaffService(StaffService staffService) {
        this.staffService = staffService;
    }
}
