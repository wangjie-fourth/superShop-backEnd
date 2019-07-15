package com.example.service.impl;

import com.example.dao.repository.LocationRespository;
import com.example.domain.dataobject.Location;
import com.example.enums.LocationExistStatusEnum;
import com.example.enums.LocationSelectedStatusEnum;
import com.example.service.LocationService;
import com.example.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangjie_fourth on 2019/4/21.
 */
@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationRespository respository;

    @Override
    public Location addLocation(Location location) {
        // 设置主键id
        location.setLocationId(KeyUtil.getUniqueKey());
        // 新的收货地址肯定是未删除状态的
        location.setLocationExist(LocationExistStatusEnum.EXIST.getCode());
        // 保存
        return respository.save(location);
    }

    @Override
    /*
    * 查看该user_id 下是否有默认收货地址
    *       如果有，修改该条信息为非默认收货地址
    *       如果没有，不做任何操作
    * */
    public void updateLocationSelectedByUserId(String userId) {
        // 查询对应user_id 和 location_selected为默认收货地址 的对象
        List<Location> locationList = respository.findByUserIdAndAndLocationSelected(userId, LocationSelectedStatusEnum.SELECTED.getCode());
        // 如果该用户存在默认收货地址，则将其修改为非默状态
        if (locationList.size() != 0) {
            Location location = locationList.get(0);
            location.setLocationSelected(LocationSelectedStatusEnum.UN_SELECTED.getCode());
            respository.save(location);
        }
    }

    @Override
    // 获取一个用户下，所有存在的收货地址信息
    public List<Location> getAllByUserIdAndLocationExist(String userId) {
        return respository.findAllByUserIdAndAndLocationExist(userId,LocationExistStatusEnum.EXIST.getCode());
    }

    @Override
    // 根据 location_id 获取 一个Location对象
    public Location getLocationByLocationId(String locationId) {
        return respository.findByLocationId(locationId);
    }

    @Override
    // 更新某个Location对象
    public Location updateLocation(Location location) {
        return respository.save(location);
    }

    @Override
    // 根据location_id 删除Location对象
    public Location deleteLocationById(String locationId) {
        // 先查这个Location对象
        Location location = respository.findByLocationId(locationId);
        // 修改Exist属性
        location.setLocationExist(LocationExistStatusEnum.DELETED.getCode());
        // 更新
        return respository.save(location);
    }

    @Override
    // 根据 userId 查询其默认的收货地址
    public List<Location> getLocationSelectesByUserId(String userId) {
        //
        return respository.findAllByUserIdAndAndLocationExistAndLocationSelected(userId,
                        LocationExistStatusEnum.EXIST.getCode(),
                LocationSelectedStatusEnum.SELECTED.getCode());
    }
}
