package com.example.dao.repository;

import com.example.domain.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by wangjie_fourth on 2019/4/27.
 */
public interface OrderDetailRespository extends JpaRepository<OrderDetail, String> {

    OrderDetail save(OrderDetail orderDetail);

    // 根据orderId获取对应的OrderDetail
    List<OrderDetail> findAllByOrderId(String orderId);
}
