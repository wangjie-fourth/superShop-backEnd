package com.example.enums;

import lombok.Getter;

/**
 * 默认收货地址状态
 * Created by wangjie_fourth on 2019/4/21.
 */
@Getter
public enum LocationSelectedStatusEnum {
    SELECTED(1,"默认收货地址"),
    UN_SELECTED(0,"不是默认收货地址")
    ;
    private Integer code;
    private String message;

    LocationSelectedStatusEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }
}
