package com.example.domain.dataobject;

import com.example.enums.OrderStatusEnum;
import com.example.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by wangjie_fourth on 2019/4/27.
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {
    /* 订单id */
    @Id
    private String orderId;
    /* 收货地址id */
    private String locationId;
    /* 用户id */
    private String userId;
    /* 订单总金额 */
    private BigDecimal orderAmount;
    /* 订单状态，默认0为新下单 */
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();
    /* 支付状态，默认0为未支付 */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();
    /** 创建时间. */
    private Date createTime;
    /** 更新时间. */
    private Date updateTime;

}
