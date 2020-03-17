package com.example.service;

import com.example.domain.dataobject.Location;

import java.util.List;

/**
 * Created by wangjie_fourth on 2019/4/21.
 */
public interface LocationService {
    // 添加新的收货地址信息
    Location addLocation(Location location);

    /*
     * 查看该user_id 下是否有默认收货地址
     *       如果有，修改该条信息为非默认收货地址
     *       如果没有，不做任何操作
     * */
    void updateLocationSelectedByUserId(String userId);

    // 获取一个用户下，所有存在的收货地址信息
    List<Location> getAllByUserIdAndLocationExist(String userId);

    // 根据 location_id 获取 一个Location对象
    Location getLocationByLocationId(String locationId);

    // 更新某个Location对象
    Location updateLocation(Location location);

    // 根据location_id 删除Location对象
    Location deleteLocationById(String locationId);

    //根据 userId 查询其默认的收货地址
    List<Location> getLocationSelectesByUserId(String userId);
}
