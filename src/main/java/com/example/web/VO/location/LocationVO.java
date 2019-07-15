package com.example.web.VO.location;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 前端展示的location格式
 * Created by wangjie_fourth on 2019/4/21.
 */
@Data
public class LocationVO {
    /* id */
    @JsonProperty("id")
    private String locationId;
    /* 收货人姓名 */
    @JsonProperty("name")
    private String locationName;
    /* 收货人电话 */
    @JsonProperty("phone")
    private String locationPhone;
    /* 收货地址 */
    @JsonProperty("address")
    private String locationAddress;
    /* 是否默认收货地址 */
    @JsonProperty("selected")
    private boolean locationSelected;
}
