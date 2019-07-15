package com.example.service;

import com.example.domain.dataobject.ProductCategory;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * Created by wangjie_fourth on 2019/4/14.
 */
public interface ProductCategoryService {

    /** 获取所有分类数据，用于商品左侧导航栏 */
    List<ProductCategory> getProductCategoryByCategoryExist(Integer categoryExist);

    // 得到类目表的所有数据
    List<ProductCategory> getAll();

    // 添加新的类目数据
    ProductCategory save(ProductCategory productCategory);

    // 更新指定id类目的类目名称
    ProductCategory updateCategoryName(String categoryId, String categoryName);

    // 根据id，删除类目
    void deleteById(String categoryId);
}
