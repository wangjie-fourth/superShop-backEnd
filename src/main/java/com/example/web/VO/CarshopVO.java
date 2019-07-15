package com.example.web.VO;

import lombok.Data;

import java.util.List;

/**
 * 返回购物车对象
 * Created by wangjie_fourth on 2019/4/20.
 */
@Data
public class CarshopVO {
    /* 购物车中商品对象 */
    private List<CarshopInfoVO> infoList;
}
