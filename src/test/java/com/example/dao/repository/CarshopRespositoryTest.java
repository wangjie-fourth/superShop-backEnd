package com.example.dao.repository;

import com.example.domain.dataobject.Carshop;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by wangjie_fourth on 2019/4/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CarshopRespositoryTest {

    @Autowired
    private CarshopRespository respository;


    @Test
    public void deleteByCarshopId() {
        Carshop carshop = respository.findByUserIdAndProductId("1556285873122671923", "1");

        respository.delete(carshop);
    }

    @Test
    public void findByUserIdAndProductId() {
        Carshop carshop = respository.findByUserIdAndProductId("1556285873122671923", "1");
        log.info("{}", carshop);
    }

    @Test
    public void save() {
        Carshop carshop = new Carshop();
        carshop.setUserId("1556285873122671923");
        carshop.setProductSelected(0);
        carshop.setCarshopId("1");
        carshop.setProductCount(1);
        carshop.setProductId("1");


        respository.save(carshop);
    }

    @Test
    public void getAllByUserId() throws Exception {
        List<Carshop> carshopList = respository.getAllByUserId("1");
        for (Carshop p : carshopList) {
            System.out.println(p.toString());
        }
    }

}