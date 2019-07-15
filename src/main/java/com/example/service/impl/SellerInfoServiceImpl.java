package com.example.service.impl;

import com.example.dao.repository.SellerInfoRespository;
import com.example.domain.dataobject.SellerInfo;
import com.example.service.SellerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangjie_fourth on 2019/5/10.
 */
@Service
public class SellerInfoServiceImpl implements SellerInfoService {
    @Autowired
    private SellerInfoRespository respository;

    @Override
    public SellerInfo findByUsernameAndPassword(String username, String password) {
        return respository.findBySellerUsernameAndAndSellerPassword(username,password);
    }
}
