package com.example.domain.dataobject;

import com.example.enums.ProductCategoryStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * 商品类目表
 * Created by wangjie_fourth on 2019/4/14.
 */
@Entity
@DynamicUpdate
@Data
public class ProductCategory {
    /** 类目id */
    @Id
    private String categoryId;
    /** 类目名称 */
    private String categoryName;
    /** 类目编号 */
    private Integer categoryType;
    /** 创建时间 */
    private Date createTime;
    /** 修改时间 */
    private Date updateTime;
    /** 是否存在 */
    private Integer categoryExist = ProductCategoryStatusEnum.UP.getCode();

    public ProductCategory() {
    }
}
