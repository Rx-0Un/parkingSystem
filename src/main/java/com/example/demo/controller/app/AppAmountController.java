package com.example.demo.controller.app;

import com.example.demo.bean.app.AmountAddBean;
import com.example.demo.bean.app.CouponDate;
import com.example.demo.service.UserAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/App/AmountManage")
public class AppAmountController {
    UserAmountService userAmountService;

    @RequestMapping(value = "/addAmountByInfo")
    public AmountAddBean addAmountByInfo(String user_id, String amount) {
        int code = userAmountService.addRowByInfo(user_id, Integer.valueOf(amount));
        return new AmountAddBean(code);
    }

    @Autowired
    public AppAmountController(UserAmountService userAmountService) {
        this.userAmountService = userAmountService;
    }
}
