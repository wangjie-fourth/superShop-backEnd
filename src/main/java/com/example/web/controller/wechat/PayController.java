package com.example.web.controller.wechat;

import com.example.service.DTO.OrderDTO;
import com.example.service.OrderService;
import com.example.service.PayService;
import com.lly835.bestpay.model.PayResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 微信支付
 * Created by wangjie_fourth on 2019/4/26.
 */
@Controller
@RequestMapping("/pay")
@Slf4j
public class PayController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    // 创建订单，发起支付
    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl,
                               Map<String, Object> map) {
        log.info(orderId + "订单，开始支付");
        //1、查询订单
        OrderDTO orderDTO = orderService.findOneByOrderId(orderId);

        //发起支付
        PayResponse payResponse = payService.create(orderDTO);

        map.put("payResponse", payResponse);
        map.put("returnUrl", returnUrl);

        return new ModelAndView("pay/create", map);
    }

    // 接收微信支付异步通知，修改订单状态
    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData) {
        // 处理业务逻辑
        payService.notify(notifyData);

        // 反馈微信支付，告知系统已经修改这个订单信息了。不需要再发这个订单异步通知
        return new ModelAndView("pay/success");
    }


}
