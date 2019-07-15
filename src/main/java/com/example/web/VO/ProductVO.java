package com.example.web.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 前端所需要的Product 格式数据
 * Created by wangjie_fourth on 2019/4/16.
 */
@Data
public class ProductVO {
    /* 商品id */
    @JsonProperty("id")
    private String productId;
    /* 商品名称 */
    @JsonProperty("name")
    private String productName;
    /* 商品价格 */
    @JsonProperty("price")
    private BigDecimal productPrice;
    /* 商品存量 */
    @JsonProperty("stock")
    private Integer productStock;
    /* 商品图片 */
    @JsonProperty("icon")
    private String productIcon;
    /* 商品类目编号 */
    @JsonProperty("type")
    private Integer categoryType;

    /* 该商品下的推荐商品 */
    List<RecommendVO> recommendList;
}
