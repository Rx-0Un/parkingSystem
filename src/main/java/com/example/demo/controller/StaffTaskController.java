package com.example.demo.controller;

import com.example.demo.entity.TbStaff;
import com.example.demo.entity.TbStaffTask;
import com.example.demo.service.StaffService;
import com.example.demo.service.StaffTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/index-duty-statistics")
public class StaffTaskController {
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
        model.addAttribute("allTask", staffTaskService.selectAll());
        return "index-duty-statistics::result";
    }

    @RequestMapping(value = "/selectByFuzzyStr", method = RequestMethod.POST)
    public String selectByFuzzyStr(Model model, @RequestBody Map<String, String> map) {
        //显示几条数据
        String searchNum = map.get("searchNum");
        //查找关键字
        String search = map.get("search");
        //开始时间
        String searchDate = map.get("searchDate");
        //结束时间
        String searchDate1 = map.get("searchDate1");
        int pageNum = Integer.parseInt(searchNum);
        new Thread() {
            @Override
            public void run() {
                System.out.println("关键字:" + search + "开始时间:" + searchDate + "结束时间:" + searchDate1 + "条数:" + pageNum + "页数:" + 1);
                //需要进行两次查询,第一遍查询第一页上面的内容
                List<TbStaffTask> list = processFuzzyStr(search, searchDate, searchDate1, pageNum, 0);
                //第二次查询对总查询结果进行统计
                List<TbStaffTask> list2 = processFuzzyStr(search, searchDate, searchDate1, 0, 0);
                int totalPage = list2.size() / pageNum;
                System.out.println("找到" + list2.size() + "条数据" + "一共" + totalPage + "页");
                model.addAttribute("nowPage", 1);
                model.addAttribute("totalPage", totalPage+1);
                model.addAttribute("allTask", list);
            }
        }.run();
        return "index-duty-statistics::result";
    }

    /**
     * @param search      代表查找的关键字
     * @param searchDate  任务开始日期
     * @param searchDate1 　任务结束日期
     * @param pageNum     　显示条数
     * @param page        　页数
     * @return
     */
    public List<TbStaffTask> processFuzzyStr(String search, String searchDate, String searchDate1, Integer pageNum, Integer page) {
        if (search == null && search.isEmpty() && search.equals("")) {
            return staffTaskService.selectAllPage(pageNum, 0);
        }
        return staffTaskService.selectTaskByFuzzStr(search, searchDate, searchDate1, pageNum, page);
    }

    /**
     * pageNum代表一个页面显示几条数据,page代表第几页
     */
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public String selectAllByPage(Model model, @RequestBody Map<String, String> map) {
        String search = map.get("search");
        String searchDate = map.get("searchDate");
        String searchDate1 = map.get("searchDate1");
        int pageNum = Integer.parseInt(map.get("pageNum"));
        int page = Integer.parseInt(map.get("page"));
        System.out.println("关键字:" + search + "开始时间:" + searchDate + "结束时间:" + searchDate1 + "条数:" + pageNum + "页数:" + page);
        List<TbStaffTask> list = processFuzzyStr(search, searchDate, searchDate1, pageNum, (page - 1) * pageNum);
        List<TbStaffTask> list2 = processFuzzyStr(search, searchDate, searchDate1, 0, (page - 1) * pageNum);
        int totalPage = list2.size() / pageNum;
        System.out.println("找到" + list2.size() + "条数据" + "一共" + totalPage + "页");
        model.addAttribute("allTask", list);
        model.addAttribute("nowPage", page);
        model.addAttribute("totalPage", totalPage+1);
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
