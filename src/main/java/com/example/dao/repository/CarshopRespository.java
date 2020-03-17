package com.example.dao.repository;

import com.example.domain.dataobject.Carshop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by wangjie_fourth on 2019/4/20.
 */
public interface CarshopRespository extends JpaRepository<Carshop, String> {
    // 根据userId获取所有其所有的数据
    List<Carshop> getAllByUserId(String userid);

    // 修改某条carshop信息状态
    Carshop save(Carshop carshop);

    // 获取用户购物车中某个商品的数据
    Carshop findByUserIdAndProductId(String userId, String productId);

    // 根据id信息，删除数据
    void delete(Carshop carshop);
}
