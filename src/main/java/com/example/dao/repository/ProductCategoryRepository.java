package com.example.dao.repository;

import com.example.domain.dataobject.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by wangjie_fourth on 2019/4/14.
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, String> {
    // 查询所有商品类目
    List<ProductCategory> findAll();
    // 分页，获取商品分类
    Page<ProductCategory> findAll(Pageable pageable);
    // 添加或更新商品分类
    ProductCategory save(ProductCategory productCategory);
    // 查询所有上架的商品类目
    List<ProductCategory> findAllByCategoryExist(Integer categoryExist);
    // 查询 categoryType 的最大值
    @Query("select max(categoryType) from ProductCategory")
    Integer getMaxCategoryType();
    // 根据id，查询对应的对象
    ProductCategory findByCategoryId(String categoryId);

}
