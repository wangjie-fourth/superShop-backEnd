package com.example.dao.repository;

import com.example.domain.dataobject.ProductCategory;
import com.example.enums.ProductCategoryStatusEnum;
import com.example.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by wangjie_fourth on 2019/4/14.
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repositorys;

    @Test
    public void getMaxCategoryType(){
        Integer maxCategoryType = repositorys.getMaxCategoryType();
        log.info("{}",maxCategoryType);
    }

    @Test
    public void testfindAllByCategoryExist(){
        List<ProductCategory> list = repositorys.findAllByCategoryExist(ProductCategoryStatusEnum.UP.getCode());
        System.out.print(list.size());
        for (ProductCategory p: list) {
            System.out.print(p);
        }
    }

    @Test
    public void testsave(){
        ProductCategory p = new ProductCategory();
        p.setCategoryId(KeyUtil.getUniqueKey());
        p.setCategoryName("饮料");
        p.setCategoryType(2);
        p.setCategoryExist(ProductCategoryStatusEnum.UP.getCode());

        repositorys.save(p);

    }

    @Test
    public void testfindAll() {
        PageRequest pageRequest = new PageRequest(0,3);
        Page<ProductCategory> page = repositorys.findAll(pageRequest);
        System.out.print(page.getContent().get(2));
    }

    @Test
    public void findAll() throws Exception {
        List<ProductCategory> all = repositorys.findAll();
        for (ProductCategory p: all) {
            System.out.print(p);
        }
    }

}