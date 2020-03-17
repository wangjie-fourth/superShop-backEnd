package com.example.service;

import com.example.domain.dataobject.OrderMaster;
import com.example.service.DTO.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by wangjie_fourth on 2019/4/27.
 */
public interface OrderService {
    /* 新的订单 */
    OrderDTO saveOrder(OrderDTO orderDTO);

    /* 分页获取，支付状态为未支付 的 主订单信息 */
    Page<OrderMaster> getNoPayStatusOfOrderMaster(Pageable pageable);

    // 根据orderId，获取OrderDTO
    OrderDTO findOneByOrderId(String orderId);

    // 将订单的支付状态修改为已支付
    OrderDTO paid(OrderDTO orderDTO);

    // 取消某个已支付的订单
    OrderDTO cancel(OrderDTO orderDTO);

    /* 分页获取，所有订单主表信息 */
    Page<OrderMaster> getAllOrderMaster(Pageable pageable);

    /* 分页获取，所有未完结状态的订单 */
    Page<OrderMaster> getAllNoFinishOrderMaster(Pageable pageable);

    /* 获取所有的订单信息 */
    List<OrderDTO> getAllOrderDTOByUserId(String userId);

    // 获取用户下所有未完结订单
    List<OrderDTO> getAllUnfinishedOrdersByUserId(String userId);

    // 获取用户所有完结状态的订单
    List<OrderDTO> getAllFinishedOrdersByUserId(String userId);

    // 完结订单
    OrderDTO finishOrder(OrderDTO orderDTO);
}
