package com.example.web.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by wangjie_fourth on 2019/4/20.
 * 购物车中商品对象
 */
@Data
public class CarshopInfoVO {
    /* 商品id */
    @JsonProperty("id")
    private String product_id;
    /* 商品名称 */
    @JsonProperty("name")
    private String product_name;
    /* 商品价格 */
    @JsonProperty("price")
    private BigDecimal product_price;
    /* 商品图片 */
    @JsonProperty("icon")
    private String product_icon;
    /* 商品数量 */
    @JsonProperty("account")
    private Integer product_account;
    /* 是否选中 */
    @JsonProperty("selected")
    private boolean product_selected;
    /* 商品库存量 */
    @JsonProperty("stock")
    private Integer product_stock;
}
