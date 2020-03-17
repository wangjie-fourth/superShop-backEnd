package com.example.web.Form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单表单信息
 * Created by wangjie_fourth on 2019/4/27.
 */
@Data
public class OrderForm {

    /*收货地址id*/
//    @NotEmpty(message = "收货地址必填")
    private String locationId;

    /*用户id*/
//    @NotEmpty(message = "用户信息必填")
    private String userId;

    /*用户openid*/
//    @NotEmpty(message = "用户openid必填")
    private String userOpenid;

    /*订单总金额*/
//    @NotEmpty(message = "订单总金额必填")
    private BigDecimal orderAmount;

    /*订单商品*/
//    @NotEmpty(message = "订单商品不能为空")
    private String orderDeatils;
}
