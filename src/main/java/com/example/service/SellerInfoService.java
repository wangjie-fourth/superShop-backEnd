package com.example.service;

import com.example.domain.dataobject.SellerInfo;

/**
 * Created by wangjie_fourth on 2019/5/10.
 */
public interface SellerInfoService {
    SellerInfo findByUsernameAndPassword(String username,String password);
}
