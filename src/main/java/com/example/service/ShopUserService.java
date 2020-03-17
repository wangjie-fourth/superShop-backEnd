package com.example.service;

import com.example.domain.dataobject.ShopUser;

/**
 * Created by wangjie_fourth on 2019/4/26.
 */
public interface ShopUserService {
    ShopUser save(ShopUser shopUser);

    // 根据userId查找User对象
    ShopUser findByUserId(String userId);

    // 根据openId查找User对象
    ShopUser findByOpenId(String openId);
}
