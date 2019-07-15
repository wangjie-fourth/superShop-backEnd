package com.example.recommand;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by wangjie_fourth on 2019/5/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RecommendServiceImplTest {

    @Autowired
    private RecommendServiceImpl service;

    @Test
    public void getDatasByFinished(){
        service.setRecommendDatas();


    }

    @Test
    public void getProductIds() throws Exception {
        String[] productIds = service.getProductIds();

        for (String demo : productIds){
            log.info("{}",demo);
        }
    }

}