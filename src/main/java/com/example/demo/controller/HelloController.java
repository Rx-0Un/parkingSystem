package com.example.demo.controller;

import com.example.demo.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class HelloController {
    @GetMapping("/test")
    public ModelAndView test() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index-duty-statistics");
        return mv;
    }
}
