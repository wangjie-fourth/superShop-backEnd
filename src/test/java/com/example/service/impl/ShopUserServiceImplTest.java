package com.example.service.impl;

import com.example.domain.dataobject.ShopUser;
import com.example.service.ShopUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by wangjie_fourth on 2019/4/28.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ShopUserServiceImplTest {
    @Autowired
    private ShopUserService shopUserService;

    @Test
    public void findByUserId() throws Exception {
        ShopUser shopUser = shopUserService.findByUserId("1556285873122671923");
        log.info("{}",shopUser);
    }

}