package com.example.service.impl;

import com.example.dao.repository.ProductRespository;
import com.example.dao.repository.RecommendRespository;
import com.example.domain.dataobject.Product;
import com.example.domain.dataobject.Recommend;
import com.example.service.DTO.ProductInfoDTO;
import com.example.service.ProductService;
import com.example.utils.KeyUtil;
import com.example.web.VO.RecommendVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjie_fourth on 2019/4/15.
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRespository respository;

    @Autowired
    private RecommendRespository recommendRespository;

    @Override
    // 根据商品分类和是否在架获取商品信息
    public Page<Product> getProductsByCategoryAndStatus(PageRequest page, Integer categoryType, Integer productStatus) {
        return respository.findByCategoryTypeAndProductStatus(page, categoryType, productStatus);
    }

    @Override
    // 根据商品id，获取指定商品信息
    public ProductInfoDTO getProductById(String id) {
        //
        Product product = respository.findByProductId(id);
        //
        Recommend recommend = recommendRespository.findByRecommendId(product.getRecommend_id());

        Product product1 = respository.findByProductId(recommend.getProductId1());
        Product product2 = respository.findByProductId(recommend.getProductId2());
        Product product3 = respository.findByProductId(recommend.getProductId3());
        RecommendVO recommend1 = new RecommendVO();
        RecommendVO recommend2 = new RecommendVO();
        RecommendVO recommend3 = new RecommendVO();

        recommend1.setProductId(product1.getProductId());
        recommend1.setRecommendIcon(product1.getProductIcon());
        recommend1.setRecommendName(product1.getProductName());

        recommend2.setProductId(product2.getProductId());
        recommend2.setRecommendIcon(product2.getProductIcon());
        recommend2.setRecommendName(product2.getProductName());

        recommend3.setProductId(product3.getProductId());
        recommend3.setRecommendIcon(product3.getProductIcon());
        recommend3.setRecommendName(product3.getProductName());

        List<RecommendVO> recommendVOList = new ArrayList<>();
        recommendVOList.add(recommend1);
        recommendVOList.add(recommend2);
        recommendVOList.add(recommend3);
        //

        ProductInfoDTO result = new ProductInfoDTO();
        result.setProductId(product.getProductId());
        result.setCategoryType(product.getCategoryType());
        result.setCreateTime(product.getCreateTime());
        result.setProductIcon(product.getProductIcon());
        result.setProductName(product.getProductName());
        result.setProductPrice(product.getProductPrice());
        result.setProductStatus(product.getProductStatus());
        result.setProductStock(product.getProductStock());
        result.setRecommendList(recommendVOList);
        result.setUpdateTime(product.getUpdateTime());

        return result;
    }

    @Override
    public List<Product> findByProductIdIn(List<String> productIDs) {
        return respository.findByProductIdIn(productIDs);
    }

    @Override
    public Page<Product> getProducts(PageRequest page) {
        return respository.findAll(page);
    }

    @Override
    public Product save(Product product) {
        // 设置主键
        product.setProductId(KeyUtil.getUniqueKey());
        // 设置recommend_id
        product.setRecommend_id(KeyUtil.getUniqueKey());

        return respository.save(product);
    }

    @Override
    // 更新某个商品对象
    public Product update(Product product) {
        return respository.save(product);
    }
}
