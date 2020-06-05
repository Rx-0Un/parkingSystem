package com.example.demo.controller;

import com.example.demo.entity.TbStaffDuty;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Controller("OrderController")
@RequestMapping(value = "/index-order-search")
public class OrderController {
    OrderService orderService;

    @RequestMapping(value = "/selectOrderByStr", method = RequestMethod.POST)
    public String selectOrderByStr(Model model, @RequestBody Map<String, String> map) {
        String orderPurchaseType = map.get("orderPurchaseType");
        String orderPayType = map.get("OrderPayType");
        String orderState = map.get("orderState");
        String keyword_title = map.get("keyword_title");
        String keyword = map.get("keyword");
        int pageNum = Integer.valueOf(map.get("pageNum"));
        int page = Integer.valueOf(map.get("page"));
        model.addAttribute("OrderResult", orderService.selectOrderByStr(orderPurchaseType, orderPayType, orderState, keyword_title, keyword, pageNum, (page - 1) * pageNum));
        model.addAttribute("NowPage", page);
        model.addAttribute("TotalPage", (orderService.selectOrderByStr(orderPurchaseType, orderPayType, orderState, keyword_title, keyword, 0, 0).size() / pageNum)+1);
        return "index-order-search::OrderResult";
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
