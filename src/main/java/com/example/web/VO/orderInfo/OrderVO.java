package com.example.web.VO.orderInfo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wangjie_fourth on 2019/5/10.
 * 用户查看自己订单信息时，前端所需要的信息类
 */
@Data
public class OrderVO {
    /** 订单id. */
    private String orderId;

    /** 收货地址id */
    private String locationId;

    /** 订单总金额. */
    private BigDecimal orderAmount;

    /** 订单状态, 默认为0新下单. */
    private Integer orderStatus;

    /** 支付状态, 默认为0未支付. */
    private Integer payStatus;

    /* 订单中商品信息 */
    private List<OrderInfoVO> productList;
}
