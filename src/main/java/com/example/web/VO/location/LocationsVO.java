package com.example.web.VO.location;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 传输多个Location
 * Created by wangjie_fourth on 2019/4/21.
 */
@Data
public class LocationsVO {
    /* 某个用户的所有收货地址信息 */
    @JsonProperty("list")
    private List<LocationVO> list;
}
