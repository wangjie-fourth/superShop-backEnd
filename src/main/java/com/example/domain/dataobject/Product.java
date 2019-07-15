package com.example.domain.dataobject;

import com.example.enums.ProductStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by wangjie_fourth on 2019/4/15.
 */
@Entity
@Data
@DynamicUpdate
public class Product {
    @Id
    private String productId;

    /** 名字. */
    private String productName;

    /** 单价. */
    private BigDecimal productPrice;

    /** 库存. */
    private Integer productStock;

    /** 商品图片. */
    private String productIcon;

    /** 状态, 0正常1下架. */
    private Integer productStatus = ProductStatusEnum.UP.getCode();

    /** 类目编号. */
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    /** 商品推荐信息 */
    private String recommend_id;

    public Product(String productId) {
        this.productId = productId;
    }

    public Product() {
    }
}
