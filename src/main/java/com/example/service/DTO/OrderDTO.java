package com.example.service.DTO;

import com.example.domain.dataobject.OrderDetail;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by wangjie_fourth on 2019/4/27.
 */
@Data
public class OrderDTO {
    /** 订单id. */
    private String orderId;

    /** 收货地址id */
    private String locationId;

    /* 用户id */
    private String userId;

    /** 买家微信Openid. */
    private String userOpenid;

    /** 订单总金额. */
    private BigDecimal orderAmount;

    /** 订单状态, 默认为0新下单. */
    private Integer orderStatus;

    /** 支付状态, 默认为0未支付. */
    private Integer payStatus;

    /** 创建时间. */
    private Date createTime;

    /** 更新时间. */
    private Date updateTime;


    List<OrderDetail> orderDetailList;
}
