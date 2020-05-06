package com.example.demo.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HelloController {
    @GetMapping("/test")
    public ModelAndView test(Model model) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index5");
        return mv;
    }
}
