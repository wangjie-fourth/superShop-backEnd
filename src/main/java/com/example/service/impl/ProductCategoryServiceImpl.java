package com.example.service.impl;

import com.example.dao.repository.ProductCategoryRepository;
import com.example.domain.dataobject.ProductCategory;
import com.example.enums.ProductCategoryStatusEnum;
import com.example.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangjie_fourth on 2019/4/14.
 */
@Service
public class ProductCategoryServiceImpl implements com.example.service.ProductCategoryService {
    //
    @Autowired
    private ProductCategoryRepository repository;


    @Override
    public List<ProductCategory> getProductCategoryByCategoryExist(Integer categoryExist ) {
        return repository.findAllByCategoryExist(categoryExist);
    }

    @Override
    // 得到类目表的所有数据
    public List<ProductCategory> getAll() {
        return repository.findAll();
    }

    @Override
    // 添加新的类目数据
    public ProductCategory save(ProductCategory productCategory) {
        // 设置主键
        productCategory.setCategoryId(KeyUtil.getUniqueKey());
        // 为其设置新的编号
        Integer type = repository.getMaxCategoryType() + 1;
        productCategory.setCategoryType(type);

        // 设置类目为未删除状态
        productCategory.setCategoryExist(ProductCategoryStatusEnum.UP.getCode());

        return repository.save(productCategory);
    }

    @Override
    // 更新指定id类目的类目名称
    public ProductCategory updateCategoryName(String categoryId, String categoryName) {
        // 得到指定id的对象
        ProductCategory productCategory = repository.findByCategoryId(categoryId);
        // 修改name属性值
        productCategory.setCategoryName(categoryName);
        // 更新
        return repository.save(productCategory);
    }

    @Override
    // 根据id，删除类目
    public void deleteById(String locationId) {
        // 先查
        ProductCategory category = repository.findByCategoryId(locationId);
        // 删除
        category.setCategoryExist(ProductCategoryStatusEnum.DOWN.getCode());
        // 更新
        ProductCategory save = repository.save(category);
    }
}
