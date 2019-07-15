package com.example.dao.repository;

import com.example.domain.dataobject.ShopUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by wangjie_fourth on 2019/4/26.
 */
public interface ShopUserRespository extends JpaRepository<ShopUser,String> {

    // 添加新的用户
    ShopUser save(ShopUser shopUser);

    //
    ShopUser findByUserId(String userId);

    // 根据openId查找
    List<ShopUser> findAllByUserOpenid(String userOpenid);
}
