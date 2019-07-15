package com.example.service.impl;

import com.example.service.DTO.ProductInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by wangjie_fourth on 2019/5/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;

    @Test
    public void getProductById() throws Exception {
        ProductInfoDTO productInfoDTO = productService.getProductById("1");

        log.info("{}",productInfoDTO);
    }

}