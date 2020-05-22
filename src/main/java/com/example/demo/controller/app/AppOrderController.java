package com.example.demo.controller.app;

import com.example.demo.bean.app.OrderData;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/App")
public class AppOrderController {
    OrderService orderService;

    @RequestMapping("/Order/selectOrderByName")
    public OrderData selectOrderByName(String userId) {
        return new OrderData(orderService.selectOrderByUserId(userId));
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
