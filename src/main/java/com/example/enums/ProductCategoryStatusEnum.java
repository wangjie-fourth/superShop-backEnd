package com.example.enums;

import lombok.Getter;

/**
 * Created by wangjie_fourth on 2019/4/16.
 */
@Getter
public enum ProductCategoryStatusEnum {
    UP(0, "在架"),
    DOWN(1, "下架");
    private Integer code;
    private String message;

    ProductCategoryStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
