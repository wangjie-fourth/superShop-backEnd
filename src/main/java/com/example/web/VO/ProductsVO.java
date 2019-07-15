package com.example.web.VO;

import lombok.Data;

import java.util.List;

/**
 * 返回指定分类的商品集合
 * Created by wangjie_fourth on 2019/4/20.
 */
@Data
public class ProductsVO {

    /* 是否还有数据 */
    private Integer exist;

    /* 返回的商品集合数据 */
    private List<ProductVO> productVOList;
}
