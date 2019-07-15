package com.example.dao.repository;

import com.example.domain.dataobject.Product;
import com.example.enums.ProductStatusEnum;
import com.example.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by wangjie_fourth on 2019/4/15.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductRespositoryTest {
    @Autowired
    private ProductRespository respository;

    @Test
    public void getMaxCategoryType(){
        List<Product> type = respository.getAllProductIds();


        for (Product demo:type){
            log.info("{}",demo.getProductId());
        }
    }


    @Test
    public void save(){
        Product product = new Product();

        product.setProductId(KeyUtil.getUniqueKey());
        product.setProductName("测试");
        product.setCategoryType(0);
        product.setProductPrice(BigDecimal.valueOf(2.5));
        product.setProductIcon("测试");
        product.setProductStock(5);
        product.setProductStatus(ProductStatusEnum.UP.getCode());

        respository.save(product);
    }

    @Test
    //得到所有的商品信息
    public void findAll(){
        PageRequest pageRequest = new PageRequest(0,3);
        Page<Product> page = respository.findAll(pageRequest);
        List<Product> content = page.getContent();
        log.info("{}",content);
    }

    @Test
    public void findByProductIdIn(){
        List<String> productIDs = new ArrayList<>();
        productIDs.add("1");
        productIDs.add("2");
        productIDs.add("3");
        productIDs.add("4");
        productIDs.add("5");

        List<Product> productList = respository.findByProductIdIn(productIDs);
        for (Product p: productList) {
            System.out.println(p);
        }
    }

    @Test
    public void findByProductId(){
        Product productId = respository.findByProductId("1");
        System.out.print(productId);
    }

    @Test
    public void findByCategoryTypeAndProductStatus() throws Exception {
        PageRequest pageRequest = new PageRequest(0,3);
        Page<Product> page = respository.findByCategoryTypeAndProductStatus(pageRequest,0,0);
        System.out.print(page.getTotalPages());
    }

}