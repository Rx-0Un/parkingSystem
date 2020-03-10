package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class StartController {
    @GetMapping("/index")
    public String test() {
        return "authentication-login";
    }
}
