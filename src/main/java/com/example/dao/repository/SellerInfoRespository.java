package com.example.dao.repository;

import com.example.domain.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wangjie_fourth on 2019/5/10.
 */

public interface SellerInfoRespository extends JpaRepository<SellerInfo,String>{
    // 根据username 和 password 查询SellerInfo对象
    SellerInfo findBySellerUsernameAndAndSellerPassword(String username,String password);
}
