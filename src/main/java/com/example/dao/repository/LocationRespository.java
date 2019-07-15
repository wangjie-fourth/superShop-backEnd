package com.example.dao.repository;

import com.example.domain.dataobject.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by wangjie_fourth on 2019/4/21.
 */
public interface LocationRespository extends JpaRepository<Location,String> {
    // 添加新的收货地址
    Location save(Location location);
    // 根据 user_id 和 location_selected 查询
    List<Location> findByUserIdAndAndLocationSelected(String userId,Integer locationSelected);
    // 获取用户所有存在的收货地址信息
    List<Location> findAllByUserIdAndAndLocationExist(String userId,Integer locationExist);
    // 根据 location_id 获取Location对象
    Location findByLocationId(String locationId);
    // 根据 userId、locationExist、locationSelectd 选择
    List<Location> findAllByUserIdAndAndLocationExistAndLocationSelected(String userId,Integer locationExist,Integer locationSelected);

}
