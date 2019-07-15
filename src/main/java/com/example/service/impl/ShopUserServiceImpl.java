package com.example.service.impl;

import com.example.dao.repository.ShopUserRespository;
import com.example.domain.dataobject.ShopUser;
import com.example.service.ShopUserService;
import com.example.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangjie_fourth on 2019/4/26.
 */
@Service
public class ShopUserServiceImpl implements ShopUserService {
    @Autowired
    private ShopUserRespository respository;


    @Override
    // 如果没有注册过，就在表中添加数据；如果注册过，就什么都不做
    public ShopUser save(ShopUser shopUser) {
        // 检查该用户是否注册过
        List<ShopUser> userList = respository.findAllByUserOpenid(shopUser.getUserOpenid());
        if (userList.size() > 0){
            // 被注册过
            return userList.get(0);
        }

        // 没有被注册过
        // 设置主键
        shopUser.setUserId(KeyUtil.getUniqueKey());

        return respository.save(shopUser);
    }

    @Override
    public ShopUser findByUserId(String userId) {
        return respository.findByUserId(userId);
    }

    @Override
    public ShopUser findByOpenId(String openId) {
        return respository.findAllByUserOpenid(openId).get(0);
    }
}
