package com.example.domain.dataobject;

import com.example.enums.LocationExistStatusEnum;
import com.example.enums.LocationSelectedStatusEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 收货地址
 * Created by wangjie_fourth on 2019/4/21.
 */
@Data
@Entity
public class Location {
    /* id */
    @Id
    private String locationId;
    /* 用户id */
    private String userId;
    /* 收货人姓名 */
    private String locationName;
    /* 收货人电话 */
    private String locationPhone;
    /* 收货人地址 */
    private String locationAddress;
    /* 是否存在 */
    private Integer locationExist = LocationExistStatusEnum.EXIST.getCode();
    /* 是否默认收货地址 */
    private Integer locationSelected = LocationSelectedStatusEnum.UN_SELECTED.getCode();
}
