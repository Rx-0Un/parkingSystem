package com.example.demo.controller;

import com.example.demo.entity.TbStaff;
import com.example.demo.service.StaffService;
import com.example.demo.service.StaffTaskService;
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
public class StaffDutyController {
    StaffService staffService;
    StaffTaskService staffTaskService;

    @RequestMapping(value = "/selectStaff", method = RequestMethod.POST)
    public String selectStaff(Model model, @RequestBody Map<String, String> map) {
        String select = map.get("select");
        String Keyword = map.get("Keyword");
        List<Map<String, Object>> list = processSelectAndKeyword(select, Keyword);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
        model.addAttribute("allStaff", list);
        return "index-duty-statistics::staffSearch";
    }

    public List<Map<String, Object>> processSelectAndKeyword(String select, String Keyword) {
        if (Keyword != null && !Keyword.isEmpty()) {
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

    @RequestMapping(value = "/addNewTask", method = RequestMethod.POST)
    public String addNewTask(Model model, @RequestBody Map<String, String> map) {
        String staffId = map.get("staffId");
        String starting_time = map.get("starting_time");
        String description = map.get("description");
        if (starting_time != null && description != null) {
            int count = staffTaskService.addTask(staffId, starting_time, description);
            System.out.println(count == 1 ? "成功" : "失败");
        }
        model.addAttribute("allTask",staffTaskService.selectAll());
        return "index-duty-statistics::result";
    }

    
    @Autowired
    public void setStaffTaskService(StaffTaskService staffTaskService) {
        this.staffTaskService = staffTaskService;
    }

    @Autowired
    public void setStaffService(StaffService staffService) {
        this.staffService = staffService;
    }
}
