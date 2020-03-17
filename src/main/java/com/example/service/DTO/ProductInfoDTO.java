package com.example.service.DTO;

import com.example.enums.ProductStatusEnum;
import com.example.web.VO.RecommendVO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by wangjie_fourth on 2019/5/17.
 */
@Data
public class ProductInfoDTO {
    private String productId;

    /**
     * 名字.
     */
    private String productName;

    /**
     * 单价.
     */
    private BigDecimal productPrice;

    /**
     * 库存.
     */
    private Integer productStock;

    /**
     * 商品图片.
     */
    private String productIcon;

    /**
     * 状态, 0正常1下架.
     */
    private Integer productStatus = ProductStatusEnum.UP.getCode();

    /**
     * 类目编号.
     */
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    private List<RecommendVO> recommendList;
}
