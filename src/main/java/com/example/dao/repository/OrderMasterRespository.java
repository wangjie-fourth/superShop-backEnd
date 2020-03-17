package com.example.dao.repository;

import com.example.domain.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by wangjie_fourth on 2019/4/27.
 */
public interface OrderMasterRespository extends JpaRepository<OrderMaster, String> {

    // 添加新的订单主表
    OrderMaster save(OrderMaster orderMaster);

    // 分页   根据支付状态，获取订单主表信息
    Page<OrderMaster> findAllByPayStatus(Pageable pageable, Integer payStatus);

    // 分页 获取所有订单主表信息
    Page<OrderMaster> findAll(Pageable pageable);

    // 分页 获取所有未完结状态的订单主表
    Page<OrderMaster> findAllByOrderStatus(Pageable pageable, Integer orderStatus);

    // 获取userId下的所有OrderMaster信息
    List<OrderMaster> findAllByUserId(String userId);

    // 根据订单状态获取userId下的所有OrderMaster信息
    List<OrderMaster> findAllByUserIdAndOrderStatus(String userId, Integer orderStatus);

    // 根据订单状态，获取所有的订单主表信息
    List<OrderMaster> findAllByOrderStatus(Integer orderStatus);


}
