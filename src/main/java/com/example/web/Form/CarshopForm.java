package com.example.web.Form;

import lombok.Data;

/**
 * 更新用户购物车数据的对象
 * Created by wangjie_fourth on 2019/5/12.
 */
@Data
public class CarshopForm {

    private String productId;

    private Integer account;

    private boolean selected;

}
