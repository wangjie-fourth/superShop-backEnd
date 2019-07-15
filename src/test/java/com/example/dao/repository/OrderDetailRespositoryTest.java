package com.example.dao.repository;

import com.example.domain.dataobject.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by wangjie_fourth on 2019/4/27.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderDetailRespositoryTest {
    @Autowired
    private OrderDetailRespository respository;


    @Test
    public void save() throws Exception {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId("1");
        orderDetail.setDetailId("1");
        orderDetail.setProductIcon("1");
        orderDetail.setProductId("1");
        orderDetail.setProductName("1");
        orderDetail.setProductPrice(BigDecimal.ONE);
        orderDetail.setProductQuantity(1);

        respository.save(orderDetail);
    }

}