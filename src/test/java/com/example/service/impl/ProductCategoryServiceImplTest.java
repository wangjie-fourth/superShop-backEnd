package com.example.service.impl;

import com.example.domain.dataobject.ProductCategory;
import com.example.enums.ProductCategoryStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by wangjie_fourth on 2019/4/14.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryServiceImplTest {
    @Autowired
    private ProductCategoryServiceImpl service;

    @Test
    public void deleteById(){
        service.deleteById("1555924715367446249");
    }

    @Test
    public void updateCategoryName(){
        service.updateCategoryName("1555924715367446249","测试");
    }

    @Test
    public void getProductCategoryByCategoryExist() throws Exception {
        List<ProductCategory> l = service.getProductCategoryByCategoryExist(ProductCategoryStatusEnum.UP.getCode());

        for (ProductCategory p:l){
            log.info("{}",p);
        }
    }

}