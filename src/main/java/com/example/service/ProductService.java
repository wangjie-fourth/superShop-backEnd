package com.example.service;

import com.example.domain.dataobject.Product;
import com.example.service.DTO.ProductInfoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * Created by wangjie_fourth on 2019/4/15.
 */
public interface ProductService {
    // 根据指定商品分类，获取在架商品
    Page<Product> getProductsByCategoryAndStatus(PageRequest page,Integer categoryType,Integer productStatus);
    // 根据商品id，获取指定商品信息
    ProductInfoDTO getProductById(String id);
    // 根据商品id的list，获取这个list下的所有商品信息
    List<Product> findByProductIdIn(List<String> productIDs);
    // 得到所有的商品信息
    Page<Product> getProducts(PageRequest page);
    // 添加新的商品对象
    Product save(Product product);
    // 更新某个商品对象
    Product update(Product product);
}
