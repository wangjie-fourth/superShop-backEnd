package com.example.service.impl;

import com.example.service.DTO.OrderDTO;
import com.example.service.OrderService;
import com.example.service.PayService;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangjie_fourth on 2019/4/26.
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {

    @Autowired
    // 第三方微信支付类
    private BestPayServiceImpl bestPayService;

    @Autowired
    private OrderService orderService;

    @Override
    public PayResponse create(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getUserOpenid());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderName("新订单");
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);


        log.info("【微信支付】payRequest={}", payRequest);
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("【微信支付】payResponse={}", payResponse);

        return payResponse;
    }

    @Override
    public PayResponse notify(String notifyData) {
        /*
         * 1、 验证签名
         * 2、支付状态
         * 3、支付金额
         * 4、支付人（支付人==下单人）
         * */


        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("【微信支付】异步通知={}", payResponse);

        // 查找订单
        OrderDTO orderDTO = orderService.findOneByOrderId(payResponse.getOrderId());

        // 判断订单是否存在
        if (orderDTO == null) {
            // 抛出异常，终止代码运行
        }

        // 判断金额是否一致
//        if (orderDTO.getOrderAmount().compareTo(new BigDecimal(payResponse.getOrderAmount())) != 0){
//            // 不一致，抛出异常
//        }

        // 修改订单支付状态
        orderService.paid(orderDTO);

        return payResponse;
    }

    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(orderDTO.getOrderId());
        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5.WXPAY_H5);

        log.info("{}", refundRequest);

        RefundResponse refund = bestPayService.refund(refundRequest);

        log.info("{}", refund);

        return refund;
    }
}
