package com.example.service.impl;

import com.example.dao.repository.CarshopRespository;
import com.example.domain.dataobject.Carshop;
import com.example.service.CarshopService;
import com.example.web.Form.CarshopForm;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by wangjie_fourth on 2019/5/11.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class CarshopServiceImplTest {
    @Autowired
    private CarshopService service;

    @Autowired
    private CarshopRespository respository;

    @Test
    public void updateCarshop(){
        List<CarshopForm> list = new ArrayList<>();
        CarshopForm demo1 = new CarshopForm();
        demo1.setAccount(1);
        demo1.setProductId("1");
        demo1.setSelected(false);

    }

    @Test
    public void updateAfterNewOrders() throws Exception {
        List<Carshop> list = new ArrayList<>();
        Carshop demo1 = respository.findByUserIdAndProductId("1556286032654545253", "2");
        Carshop demo2 = respository.findByUserIdAndProductId("1556286032654545253", "3");

        demo1.setProductCount(1);
        demo2.setProductCount(1);

        list.add(demo1);
        list.add(demo2);


        service.updateAfterNewOrders("1556286032654545253",list);
    }

}