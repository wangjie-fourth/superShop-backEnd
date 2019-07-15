package com.example.web.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 用于向视图层显示的数据
 * Created by wangjie_fourth on 2019/4/16.
 */
@Data
public class ProductCategoryVO {
    /* 商品类目id */
    @JsonProperty("id")
    private String categoryId;

    /* 商品类目名称 */
    @JsonProperty("name")
    private String categoryName;

    /* 商品类目编号 */
    @JsonProperty("type")
    private Integer categoryType;



}
