package com.example.service.impl;

import com.example.dao.repository.OrderDetailRespository;
import com.example.dao.repository.OrderMasterRespository;
import com.example.dao.repository.ProductRespository;
import com.example.dao.repository.ShopUserRespository;
import com.example.domain.dataobject.OrderDetail;
import com.example.domain.dataobject.OrderMaster;
import com.example.domain.dataobject.Product;
import com.example.domain.dataobject.ShopUser;
import com.example.enums.OrderStatusEnum;
import com.example.enums.PayStatusEnum;
import com.example.service.DTO.OrderDTO;
import com.example.service.OrderService;
import com.example.service.PayService;
import com.example.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjie_fourth on 2019/4/27.
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMasterRespository orderMasterRespository;

    @Autowired
    private OrderDetailRespository orderDetailRespository;

    @Autowired
    private ShopUserRespository shopUserRespository;

    @Autowired
    private ProductRespository productRespository;

    @Autowired
    private PayService payService;

    @Override
    @Transactional
    public OrderDTO saveOrder(OrderDTO orderDTO) {
        /*
         * 1、将 orderDTO 分解成 orderMaster和orderDetail
         * 2、分别添加到数据库中
         * 3、扣除商品库存
         * */
        String orderId = KeyUtil.getUniqueKey();
        orderDTO.setOrderId(orderId);
        orderDTO.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderDTO.setOrderStatus(OrderStatusEnum.NEW.getCode());

        // 1.1
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(orderId);
        orderMaster.setUserId(orderDTO.getUserId());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setOrderAmount(orderDTO.getOrderAmount());
        orderMaster.setLocationId(orderDTO.getLocationId());
        // 2.1
        OrderMaster saveOrderMaster = orderMasterRespository.save(orderMaster);

        // 1.2
        List<OrderDetail> detailList = orderDTO.getOrderDetailList();
        for (OrderDetail p : detailList) {
            p.setDetailId(KeyUtil.getUniqueKey());
            p.setOrderId(orderId);
            // 2.2
            orderDetailRespository.save(p);

            // 3、
            Product product = productRespository.findByProductId(p.getProductId());
            Integer stock = product.getProductStock() - p.getProductQuantity();
            product.setProductStock(stock);
            productRespository.save(product);
        }


        return orderDTO;
    }

    @Override
    /* 分页获取，支付状态为未支付 的 主订单信息 */
    public Page<OrderMaster> getNoPayStatusOfOrderMaster(Pageable pageable) {
        return orderMasterRespository.findAllByPayStatus(pageable, PayStatusEnum.WAIT.getCode());
    }

    @Override
    // 根据orderId，获取OrderDTO
    public OrderDTO findOneByOrderId(String orderId) {
        OrderMaster orderMaster = orderMasterRespository.findOne(orderId);
        List<OrderDetail> orderDetailList = orderDetailRespository.findAllByOrderId(orderId);
        ShopUser shopUser = shopUserRespository.findByUserId(orderMaster.getUserId());
        //
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(orderMaster.getOrderId());
        orderDTO.setLocationId(orderMaster.getLocationId());
        orderDTO.setUserId(orderMaster.getUserId());
        orderDTO.setUserOpenid(shopUser.getUserOpenid());
        orderDTO.setOrderAmount(orderMaster.getOrderAmount());
        orderDTO.setOrderStatus(orderMaster.getOrderStatus());
        orderDTO.setPayStatus(orderMaster.getPayStatus());
        orderDTO.setCreateTime(orderMaster.getCreateTime());
        orderDTO.setUpdateTime(orderMaster.getUpdateTime());
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        // 将订单支付状态修改为已支付
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        //
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(orderDTO.getOrderId());
        orderMaster.setCreateTime(orderDTO.getCreateTime());
        orderMaster.setLocationId(orderDTO.getLocationId());
        orderMaster.setOrderAmount(orderDTO.getOrderAmount());
        orderMaster.setOrderStatus(orderDTO.getOrderStatus());
        orderMaster.setPayStatus(orderDTO.getPayStatus());
        orderMaster.setUpdateTime(orderDTO.getUpdateTime());
        orderMaster.setUserId(orderDTO.getUserId());

        // 正式修改
        OrderMaster result = orderMasterRespository.save(orderMaster);


        return orderDTO;
    }

    @Override
    // 取消某个已支付的订单
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        orderMaster.setOrderId(orderDTO.getOrderId());
        orderMaster.setLocationId(orderDTO.getLocationId());
        orderMaster.setOrderAmount(orderDTO.getOrderAmount());
        orderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        orderMaster.setPayStatus(orderDTO.getPayStatus());
        orderMaster.setUserId(orderDTO.getUserId());
        orderMaster.setCreateTime(orderDTO.getCreateTime());
        orderMaster.setUpdateTime(orderDTO.getUpdateTime());
        orderMasterRespository.save(orderMaster);

        // 修改商品库存
        // TODO

        // 判断订单是否支付，如果支付，就退款
        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            payService.refund(orderDTO);
        }
        return orderDTO;
    }

    @Override
    /* 分页获取，所有订单主表信息 */
    public Page<OrderMaster> getAllOrderMaster(Pageable pageable) {
        return orderMasterRespository.findAll(pageable);
    }

    @Override
    /* 分页获取，所有未完结状态的订单 */
    public Page<OrderMaster> getAllNoFinishOrderMaster(Pageable pageable) {
        return orderMasterRespository.findAllByOrderStatus(pageable, OrderStatusEnum.NEW.getCode());
    }

    @Override
    /* 获取所有的订单信息 */
    public List<OrderDTO> getAllOrderDTOByUserId(String userId) {
        /*
         * 1、获取userId下所有的OrderMaster对象
         * 2、遍历所有的OrderMaster对象，分别获取其所有的OrderDetail对象；并将其封装称OrderDTO对象
         * */
        List<OrderDTO> result = new ArrayList<>();
        ShopUser shopUser = shopUserRespository.findByUserId(userId);

        // 1、
        List<OrderMaster> orderMasterList = orderMasterRespository.findAllByUserId(userId);
        // 2、
        for (OrderMaster orderMaster : orderMasterList) {
            OrderDTO orderDTO = new OrderDTO();

            List<OrderDetail> orderDetailList = orderDetailRespository.findAllByOrderId(orderMaster.getOrderId());

            orderDTO.setOrderId(orderMaster.getOrderId());
            orderDTO.setOrderStatus(orderMaster.getOrderStatus());
            orderDTO.setPayStatus(orderMaster.getPayStatus());
            orderDTO.setOrderDetailList(orderDetailList);
            orderDTO.setUpdateTime(orderMaster.getUpdateTime());
            orderDTO.setCreateTime(orderMaster.getCreateTime());
            orderDTO.setOrderAmount(orderMaster.getOrderAmount());
            orderDTO.setUserOpenid(shopUser.getUserOpenid());
            orderDTO.setUserId(userId);
            orderDTO.setLocationId(orderMaster.getLocationId());

            result.add(orderDTO);
        }

        return result;
    }

    @Override
    // 获取用户下所有未完结订单
    public List<OrderDTO> getAllUnfinishedOrdersByUserId(String userId) {
        /*
         * 1、获取userId下所有状态未完结的OrderMaster对象
         * 2、遍历所有的OrderMaster对象，分别获取其所有的OrderDetail对象；并将其封装称OrderDTO对象
         * */
        List<OrderDTO> result = new ArrayList<>();
        ShopUser shopUser = shopUserRespository.findByUserId(userId);

        // 1、
        List<OrderMaster> orderMasterList = orderMasterRespository.findAllByUserIdAndOrderStatus(userId, OrderStatusEnum.NEW.getCode());

        // 2、
        for (OrderMaster orderMaster : orderMasterList) {
            OrderDTO orderDTO = new OrderDTO();

            List<OrderDetail> orderDetailList = orderDetailRespository.findAllByOrderId(orderMaster.getOrderId());

            orderDTO.setOrderId(orderMaster.getOrderId());
            orderDTO.setOrderStatus(orderMaster.getOrderStatus());
            orderDTO.setPayStatus(orderMaster.getPayStatus());
            orderDTO.setOrderDetailList(orderDetailList);
            orderDTO.setUpdateTime(orderMaster.getUpdateTime());
            orderDTO.setCreateTime(orderMaster.getCreateTime());
            orderDTO.setOrderAmount(orderMaster.getOrderAmount());
            orderDTO.setUserOpenid(shopUser.getUserOpenid());
            orderDTO.setUserId(userId);
            orderDTO.setLocationId(orderMaster.getLocationId());

            result.add(orderDTO);
        }

        return result;
    }

    @Override
    // 获取用户所有完结状态的订单
    public List<OrderDTO> getAllFinishedOrdersByUserId(String userId) {
        /*
         * 1、获取userId下所有已完结状态的OrderMaster对象
         * 2、遍历所有的OrderMaster对象，分别获取其所有的OrderDetail对象；并将其封装称OrderDTO对象
         * */
        List<OrderDTO> result = new ArrayList<>();
        ShopUser shopUser = shopUserRespository.findByUserId(userId);

        List<OrderMaster> orderMasterList = new ArrayList<>();
        // 1、
        List<OrderMaster> orderMasterList1 = orderMasterRespository.findAllByUserIdAndOrderStatus(userId, OrderStatusEnum.FINISHED.getCode());
        List<OrderMaster> orderMasterList2 = orderMasterRespository.findAllByUserIdAndOrderStatus(userId, OrderStatusEnum.CANCEL.getCode());
        orderMasterList.addAll(orderMasterList1);
        orderMasterList.addAll(orderMasterList2);

        // 2、
        for (OrderMaster orderMaster : orderMasterList) {
            OrderDTO orderDTO = new OrderDTO();

            List<OrderDetail> orderDetailList = orderDetailRespository.findAllByOrderId(orderMaster.getOrderId());

            orderDTO.setOrderId(orderMaster.getOrderId());
            orderDTO.setOrderStatus(orderMaster.getOrderStatus());
            orderDTO.setPayStatus(orderMaster.getPayStatus());
            orderDTO.setOrderDetailList(orderDetailList);
            orderDTO.setUpdateTime(orderMaster.getUpdateTime());
            orderDTO.setCreateTime(orderMaster.getCreateTime());
            orderDTO.setOrderAmount(orderMaster.getOrderAmount());
            orderDTO.setUserOpenid(shopUser.getUserOpenid());
            orderDTO.setUserId(userId);
            orderDTO.setLocationId(orderMaster.getLocationId());

            result.add(orderDTO);
        }


        return result;
    }

    @Override
    // 完结订单
    @Transactional
    public OrderDTO finishOrder(OrderDTO orderDTO) {
        //
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(orderDTO.getOrderId());
        orderMaster.setCreateTime(orderDTO.getCreateTime());
        orderMaster.setLocationId(orderDTO.getLocationId());
        orderMaster.setOrderAmount(orderDTO.getOrderAmount());
        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        orderMaster.setPayStatus(orderDTO.getPayStatus());
        orderMaster.setUpdateTime(orderDTO.getUpdateTime());
        orderMaster.setUserId(orderDTO.getUserId());

        // 正式修改
        OrderMaster result = orderMasterRespository.save(orderMaster);


        return orderDTO;
    }
}
