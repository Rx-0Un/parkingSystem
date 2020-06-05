package com.example.demo.controller.app;

import com.example.demo.bean.app.AmountAddBean;
import com.example.demo.bean.app.CouponDate;
import com.example.demo.entity.TbOrder;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserAmountService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/App/AmountManage")
public class AppAmountController {
    UserAmountService userAmountService;
    OrderService orderService;
    UserService userService;

    @RequestMapping(value = "/addAmountByInfo")
    public String addAmountByInfo(String user_id, String amount) {
        userAmountService.addRowByInfo(user_id, Float.valueOf(amount));
        TbOrder tbOrder = new TbOrder();
        tbOrder.setOrderPurchaseType("余额购买");
        tbOrder.setOrderAmount(Float.valueOf(amount));
        tbOrder.setOrderState("已完成");
        tbOrder.setOrderPayType("手机支付");
        tbOrder.setOrderPayer("tony");
        tbOrder.setOrderReceiver(userService.selectRowByUserId(user_id).getUserName());
        tbOrder.setOrderPayType("手机支付");
        orderService.addRowByInfo(tbOrder);

        return String.valueOf(userAmountService.selectAmountByInfo(user_id));
    }

    @RequestMapping(value = "/selectMyMoney")
    public String selectMyMoney(String user_id) {
        return String.valueOf(userAmountService.selectAmountByInfo(user_id));
    }

    @Autowired
    public void setUserAmountService(UserAmountService userAmountService) {
        this.userAmountService = userAmountService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
