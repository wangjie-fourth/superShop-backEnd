package com.example.service.impl;

import com.example.domain.dataobject.SellerInfo;
import com.example.service.SellerInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by wangjie_fourth on 2019/5/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SellerInfoServiceImplTest {
    @Autowired
    private SellerInfoService sellerInfoService;

    @Test
    public void findByUsernameAndPassword() throws Exception {
        SellerInfo sellerInfo = sellerInfoService.findByUsernameAndPassword("admin", "admin");
        log.info("{}",sellerInfo);
    }

}