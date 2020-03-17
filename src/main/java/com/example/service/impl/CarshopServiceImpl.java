package com.example.service.impl;

import com.example.dao.repository.CarshopRespository;
import com.example.domain.dataobject.Carshop;
import com.example.enums.CarshopProductSelectedEnum;
import com.example.service.CarshopService;
import com.example.utils.KeyUtil;
import com.example.web.Form.CarshopForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangjie_fourth on 2019/4/20.
 */
@Service
public class CarshopServiceImpl implements CarshopService {
    @Autowired
    private CarshopRespository respository;

    @Override
    public List<Carshop> getAllByUserId(String userid) {
        return respository.getAllByUserId(userid);
    }

    @Override
    // 从购物车下单后，修改该用户的购物车信息
    public void updateAfterNewOrders(String userId, List<Carshop> list) {
        // 遍历 list，修改购物车数据
        for (Carshop carshop : list) {
            /*
             * 1、获取 这个carshop对象
             * 2、删除该条数据
             * */

            Carshop item = respository.findByUserIdAndProductId(userId, carshop.getProductId());
            respository.delete(item);
        }
    }

    @Override
    // 切入购物车界面后，修改购物车的状态
    public void updateCarshop(String userId, List<CarshopForm> list) {
        /*
         * 1、删除该用户下carshop的所有数据
         * 2、遍历获取要新添加的Carshop对象
         * */
        // 1、
        List<Carshop> carshopList = respository.getAllByUserId(userId);
        for (Carshop demo : carshopList) {
            respository.delete(demo);
        }

        // 2、
        for (CarshopForm demo : list) {
            Carshop item = new Carshop();
            item.setUserId(userId);
            item.setCarshopId(KeyUtil.getUniqueKey());
            item.setProductCount(demo.getAccount());
            item.setProductId(demo.getProductId());
            if (demo.isSelected()) {
                item.setProductSelected(CarshopProductSelectedEnum.SELECTED.getCode());
            } else {
                item.setProductSelected(CarshopProductSelectedEnum.UN_SELECTED.getCode());
            }

            respository.save(item);
        }


    }
}
