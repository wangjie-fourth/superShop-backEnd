package com.example.service.impl;

import com.example.service.DTO.OrderDTO;
import com.example.service.OrderService;
import com.example.service.PayService;
import com.lly835.bestpay.model.RefundResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by wangjie_fourth on 2019/5/7.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PayServiceImplTest {
    @Autowired
    private PayService payService;

    @Autowired
    private OrderService orderService;

    @Test
    public void refund(){
        OrderDTO orderId = orderService.findOneByOrderId("1558094530814233843");
        RefundResponse refund = payService.refund(orderId);
    }

    @Test
    public void create() throws Exception {
        OrderDTO orderDTO = orderService.findOneByOrderId("1556353416978524100");
        payService.create(orderDTO);
    }

}