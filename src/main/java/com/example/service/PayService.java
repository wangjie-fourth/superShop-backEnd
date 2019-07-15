package com.example.service;

import com.example.service.DTO.OrderDTO;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;

/**
 * 微信支付的业务接口
 * Created by wangjie_fourth on 2019/4/26.
 */
public interface PayService {

    PayResponse create(OrderDTO orderDTO);

    PayResponse notify(String notifyData);

    // 退款
    RefundResponse refund(OrderDTO orderDTO);

}
