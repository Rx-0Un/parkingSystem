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

    /**
     * 条件查询
     * @param model
     * @param select
     * @param Keyword
     * @param checkbox1
     * @param checkbox2
     * @param checkbox3
     * @param checkbox4
     * @param checkbox5
     * @param checkbox6
     * @return
     */
    @RequestMapping(value = "/selectStaffByCondition")
    public String selectStaffByCondition(Model model, String select, @RequestParam(value = "Keyword") String Keyword, String checkbox1, String checkbox2, String checkbox3, String checkbox4, String checkbox5, String checkbox6) {
        String type = new String();
        type += processType(checkbox1);
        type += processType(checkbox2);
        type += processType(checkbox3);
        type += processType(checkbox4);
        type += processType(checkbox5);
        type += processType(checkbox6);

        System.out.println("Keyword:" + Keyword);
        System.out.println("select:" + select);
        System.out.println("type:" + type);

        List<TbStaff> list = prossType(select, Keyword, type);
        model.addAttribute("allStaff", list);
        model.addAttribute("searchInfo", prossSearchInFo(select,Keyword)+list.size()+"条数据");
        return "index-staff";
    }

    public String processType(String string) {
        if (string == null || string.isEmpty()) {
            return "0";
        }
        return "1";
    }

    public List<TbStaff> prossType(String select, String Keyword, String type) {
        if (Keyword != null&&type.equals("000000")) {
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
        if (Keyword!=null&&!type.equals("000000")){
            switch (select) {
                case "全体":
                    return staffService.selectByFuzzyStrAndType(Keyword,type);
                case "编号":
                    return staffService.selectByFuzzyIdAndType(Keyword,type);
                case "姓名":
                    return staffService.selectByFuzzyNameAndType(Keyword,type);
                case "电话":
                    return staffService.selectByFuzzyPhoneAndType(Keyword,type);
                default:break;
            }
        }
        return staffService.selectAll();
    }

    public String prossSearchInFo(String select,String keyword){
        String str = "查找目标:";
        str+=select;
        if (keyword==null||keyword.isEmpty()){
            str+="    关键字:  无　";
        }else {
            str+="    关键字:"+keyword;
        }
        str+="    查找结果:";
        return str;
    }

    @RequestMapping(value = "/deleteStaffById")
    public String deleteStaffById(@RequestParam(value = "id") String id){
        System.out.println(id);
        return "index-staff";
    }
    @Autowired
    public void setStaffService(StaffService staffService) {
        this.staffService = staffService;
    }
}
