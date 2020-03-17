package com.example.web.controller;

import com.example.domain.dataobject.Location;
import com.example.domain.dataobject.OrderMaster;
import com.example.domain.dataobject.ShopUser;
import com.example.enums.OrderStatusEnum;
import com.example.service.DTO.OrderDTO;
import com.example.service.LocationService;
import com.example.service.OrderService;
import com.example.service.ShopUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by wangjie_fourth on 2019/4/27.
 */
@Slf4j
@Controller
@RequestMapping("/seller/order")
public class SellerOrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private ShopUserService userService;

    // 查看订单中详情
    @GetMapping("/goToOrderDetail")
    public ModelAndView goToOrderDetail(
            @RequestParam("orderId") String orderId,
            Map<String, Object> map
    ) {
        //
        OrderDTO orderDTO = orderService.findOneByOrderId(orderId);
        //
        map.put("orderDeatilList", orderDTO.getOrderDetailList());

        return new ModelAndView("order/OrderDetail", map);
    }

    // 取消订单
    @GetMapping("/cancelOrder")
    public ModelAndView cancelOrder(
            @RequestParam("orderId") String orderId,
            Map<String, Object> map
    ) {
        //
        OrderDTO orderDTO = orderService.findOneByOrderId(orderId);
        //
        if (orderDTO.getOrderStatus().equals(OrderStatusEnum.CANCEL.getCode())) {
            map.put("msg", "该订单已取消");
            map.put("url", "/supershop/seller/order/list");
            return new ModelAndView("commons/error", map);
        } else if (orderDTO.getOrderStatus().equals(OrderStatusEnum.FINISHED.getCode())) {
            map.put("msg", "该订单已完结");
            map.put("url", "/supershop/seller/order/list");
            return new ModelAndView("commons/error", map);
        }
        //
        OrderDTO dto = orderService.cancel(orderDTO);

        map.put("msg", "成功取消该订单");
        map.put("url", "/supershop/seller/order/list");
        return new ModelAndView("commons/success", map);
    }

    // 完结订单
    @GetMapping("/finishOrder")
    public ModelAndView finishOrder(
            @RequestParam("orderId") String orderId,
            Map<String, Object> map
    ) {
        //
        OrderDTO orderDTO = orderService.findOneByOrderId(orderId);
        //
        if (orderDTO.getOrderStatus().equals(OrderStatusEnum.CANCEL.getCode())) {
            map.put("msg", "该订单已取消");
            map.put("url", "/supershop/seller/order/list");
            return new ModelAndView("commons/error", map);
        } else if (orderDTO.getOrderStatus().equals(OrderStatusEnum.FINISHED.getCode())) {
            map.put("msg", "该订单已完结");
            map.put("url", "/supershop/seller/order/list");
            return new ModelAndView("commons/error", map);
        }
        //
        OrderDTO dto = orderService.finishOrder(orderDTO);

        map.put("msg", "成功结束该订单");
        map.put("url", "/supershop/seller/order/list");
        return new ModelAndView("commons/success", map);
    }

    @GetMapping("/goToUser")
    public ModelAndView goToUserByUserId(
            @RequestParam("userId") String userId,
            Map<String, Object> map
    ) {
        //
        ShopUser shopUser = userService.findByUserId(userId);
        map.put("shopUser", shopUser);

        return new ModelAndView("shopUser/detail", map);
    }


    @GetMapping("/goToLocation")
    public ModelAndView goToLocationByLocationId(
            @RequestParam("locationId") String locationId,
            Map<String, Object> map
    ) {
        Location location = locationService.getLocationByLocationId(locationId);
        map.put("location", location);

        return new ModelAndView("location/detail", map);
    }

    @GetMapping("/noPayList")
    public ModelAndView getAllNoPayStatusOfOrderMaster(
            @RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            Map<String, Object> map
    ) {
        /*
         * 1、获取所有未支付状态的OrderMaster表中数据
         * 2、发送到前端
         * */
        // 1、
        PageRequest pageRequest = new PageRequest(pageIndex - 1, pageSize);
        Page<OrderMaster> orderMasterPage = orderService.getNoPayStatusOfOrderMaster(pageRequest);
        // 2、保存到map
        map.put("currentPage", pageIndex);
        map.put("size", pageSize);
        map.put("orderMasterPage", orderMasterPage);

        return new ModelAndView("order/NoPayList", map);
    }

    @GetMapping("/list")
    public ModelAndView getAll(
            @RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            Map<String, Object> map
    ) {
        // 1、
        PageRequest pageRequest = new PageRequest(pageIndex - 1, pageSize);
        Page<OrderMaster> orderMasterPage = orderService.getAllOrderMaster(pageRequest);
        //2、
        map.put("currentPage", pageIndex);
        map.put("size", pageSize);
        map.put("orderMasterPage", orderMasterPage);

        return new ModelAndView("order/AllOrderList", map);
    }


    @GetMapping("/noFinish")
    // 获取所有未完结订单
    public ModelAndView getNoFinishOrder(
            @RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            Map<String, Object> map
    ) {
        // 1、
        PageRequest pageRequest = new PageRequest(pageIndex - 1, pageSize);
        Page<OrderMaster> orderMasterPage = orderService.getAllNoFinishOrderMaster(pageRequest);
        //
        map.put("currentPage", pageIndex);
        map.put("size", pageSize);
        map.put("orderMasterPage", orderMasterPage);

        return new ModelAndView("order/NoFinishedOrder", map);
    }


}
