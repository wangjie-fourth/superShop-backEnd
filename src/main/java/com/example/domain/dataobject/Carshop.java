package com.example.domain.dataobject;

import com.example.enums.CarshopProductSelectedEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 购物车对象
 * Created by wangjie_fourth on 2019/4/20.
 */
@Entity
@Data
public class Carshop {
    /* 购物车id */
    @Id
    private String carshopId;
    /* 用户id */
    private String userId;
    /* 商品id */
    private String productId;
    /* 商品数量 */
    private Integer productCount;
    /* 是否选中 */
    private Integer productSelected = CarshopProductSelectedEnum.SELECTED.getCode();
}
