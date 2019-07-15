package com.example.web.VO.orderInfo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by wangjie_fourth on 2019/5/10.
 * 用户查看自己订单信息时，订单中每件商品信息
 */
@Data
public class OrderInfoVO {
    /** 商品名称. */
    private String productName;

    /** 商品单价. */
    private BigDecimal productPrice;

    /** 商品数量. */
    private Integer productQuantity;

    /** 商品小图. */
    private String productIcon;

}
