package com.example.enums;

import lombok.Getter;

/**
 * 收货地址信息存在状态
 * Created by wangjie_fourth on 2019/4/21.
 */
@Getter
public enum LocationExistStatusEnum {
    EXIST(1,"存在"),
    DELETED(0,"已删除")
    ;
    private Integer code;
    private String message;

    LocationExistStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
