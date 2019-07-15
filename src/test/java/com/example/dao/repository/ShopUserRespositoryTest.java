package com.example.dao.repository;

import com.example.domain.dataobject.ShopUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by wangjie_fourth on 2019/4/26.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ShopUserRespositoryTest {

    @Autowired
    private ShopUserRespository respository;

    @Test
    public void findAllByUserOpenid(){
        List<ShopUser> list = respository.findAllByUserOpenid("obFLt0e0Rmu-8SOX5lo7gC_B368w");

        for (ShopUser p:list){
            log.info("{}",p);
        }
    }

    @Test
    public void save() throws Exception {
        ShopUser user = new ShopUser();
        user.setUserId("1");
        user.setUsername("1");
        user.setPassword("1");
        user.setUserOpenid("1");
        user.setUserIcon("1");

        respository.save(user);
    }

}