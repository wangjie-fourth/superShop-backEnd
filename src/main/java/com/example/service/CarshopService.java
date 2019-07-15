package com.example.service;

import com.example.domain.dataobject.Carshop;
import com.example.web.Form.CarshopForm;

import java.util.List;

/**
 * Created by wangjie_fourth on 2019/4/20.
 */
public interface CarshopService {
    List<Carshop> getAllByUserId(String userid);

    // 从购物车下单后，修改该用户的购物车信息
    void updateAfterNewOrders(String userId, List<Carshop> list);

    // 切入购物车界面后，修改购物车的状态
    void updateCarshop(String userId, List<CarshopForm> list);


}
