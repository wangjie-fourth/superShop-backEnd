package com.example.dao.repository;

import com.example.domain.dataobject.OrderMaster;
import com.example.enums.OrderStatusEnum;
import com.example.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by wangjie_fourth on 2019/4/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderMasterRespositoryTest {
    @Autowired
    private OrderMasterRespository respository;

    @Test
    public void findAllByOrderStatus(){
        List<OrderMaster> list = respository.findAllByOrderStatus(OrderStatusEnum.FINISHED.getCode());

        for (OrderMaster demo : list){
            log.info("{}",demo);
        }
    }

    @Test
    public void findAllByUserIdAndPayStatus(){
        List<OrderMaster> orderMasterList = respository.findAllByUserIdAndOrderStatus("1556285873122671923", 1);
        for (OrderMaster orderMaster : orderMasterList){
            log.info("{}",orderMaster);
        }
    }

    @Test
    public void findAllByUserId(){
        List<OrderMaster> list = respository.findAllByUserId("1556285873122671923");

        for (OrderMaster orderMaster : list){
            log.info("{}",orderMaster);
        }
    }

    @Test
    public void findAll(){
        PageRequest pageRequest = new PageRequest(0,10);

        Page<OrderMaster> orderMasters = respository.findAll(pageRequest);

        for(OrderMaster orderMaster : orderMasters.getContent()){
            log.info("{}",orderMaster);
        }
    }

    @Test
    // 分页   根据支付状态，获取订单主表信息
    public void findAllByPayStatus(){
        PageRequest pageRequest = new PageRequest(0,3);
        Page<OrderMaster> page = respository.findAllByPayStatus(pageRequest, PayStatusEnum.WAIT.getCode());
        List<OrderMaster> content = page.getContent();

        for (OrderMaster o:content) {
            log.info("{}",o);
        }
    }


    @Test
    public void save() throws Exception {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1");
        orderMaster.setLocationId("1");
        orderMaster.setOrderAmount(BigDecimal.valueOf(1.0));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setUserId("1");

        respository.save(orderMaster);


    }

}