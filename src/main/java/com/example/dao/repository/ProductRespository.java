package com.example.dao.repository;

import com.example.domain.dataobject.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by wangjie_fourth on 2019/4/15.
 */
public interface ProductRespository extends JpaRepository<Product, String> {
    // 根据商品分类查找对应的商品信息
    public Page<Product> findByCategoryTypeAndProductStatus(Pageable pageable,Integer categoryType, Integer productStatus);
    // 根据商品id，获取指定商品信息
    public Product findByProductId(String productId);
    // 根据商品id的list，获取这个list下的所有商品信息
    public List<Product> findByProductIdIn(List<String> productIDs);
    // 得到所有的商品信息
    Page<Product> findAll(Pageable pageable);
    // 保存商品
    Product save(Product product);

    // 获取所有商品信息
    @Query("select new com.example.domain.dataobject.Product(productId) from Product")
    List<Product> getAllProductIds();

}
