package com.example.demo.controller.app;

import com.example.demo.bean.app.OrderData;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/App")
public class AppOrderController {
    OrderService orderService;

    @RequestMapping("/App")
    public OrderData selectOrderByName(String username) {
        return new OrderData(orderService.selectOrderByUserName(username));
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
