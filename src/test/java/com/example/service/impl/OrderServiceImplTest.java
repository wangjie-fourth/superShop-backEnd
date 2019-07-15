package com.example.service.impl;

import com.example.domain.dataobject.OrderDetail;
import com.example.enums.OrderStatusEnum;
import com.example.service.DTO.OrderDTO;
import com.example.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by wangjie_fourth on 2019/4/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {
    @Autowired
    private OrderService service;

    @Test
    public void getAllFinishedOrdersByUserId(){
        service.getAllFinishedOrdersByUserId("1556285873122671923");
    }

    @Test
    public void getAllUnfinishedOrdersByUserId(){
        List<OrderDTO> orderDTOList = service.getAllUnfinishedOrdersByUserId("1556285873122671923");

        for (OrderDTO orderDTO : orderDTOList){
            log.info("{}",orderDTO);
        }
    }

    @Test
    public void getAllOrderDTOByUserId(){
        List<OrderDTO> orderDTOList = service.getAllOrderDTOByUserId("1556285873122671923");

        for (OrderDTO orderDTO : orderDTOList){
            log.info("{}",orderDTO);
        }
    }

    @Test
    public void findOneByOrderId(){
        OrderDTO orderDTO = service.findOneByOrderId("1556353416978524100");
        log.info("{}",orderDTO);
    }

    @Test
    public void saveOrder() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setLocationId("收货地址id");
        orderDTO.setUserId("用户id");
        orderDTO.setUserOpenid("用户openId");
        orderDTO.setOrderAmount(BigDecimal.ONE);

        List<OrderDetail> list = new ArrayList<>();
        OrderDetail p1 = new OrderDetail();
        p1.setProductId("商品id");
        p1.setProductQuantity(1);
        p1.setProductPrice(BigDecimal.ONE);
        p1.setProductName("商品名称");
        p1.setProductIcon("商品图标");
        OrderDetail p2 = new OrderDetail();
        p2.setProductId("商品id2");
        p2.setProductQuantity(2);
        p2.setProductPrice(BigDecimal.TEN);
        p2.setProductName("商品名称2");
        p2.setProductIcon("商品图标2");
        list.add(p1);
        list.add(p2);

        orderDTO.setOrderDetailList(list);


        OrderDTO orderDTO1 = service.saveOrder(orderDTO);
        log.info("{}",orderDTO1);


    }

}