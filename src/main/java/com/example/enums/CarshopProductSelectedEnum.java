package com.example.enums;

import lombok.Getter;

/**
 * Created by wangjie_fourth on 2019/4/20.
 */
@Getter
public enum CarshopProductSelectedEnum {
    SELECTED(1, "选中"),
    UN_SELECTED(0, "未选中");
    private Integer code;
    private String message;

    CarshopProductSelectedEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
