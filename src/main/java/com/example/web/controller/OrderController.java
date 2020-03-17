package com.example.web.controller;

import com.example.domain.dataobject.Carshop;
import com.example.domain.dataobject.OrderDetail;
import com.example.service.CarshopService;
import com.example.service.DTO.OrderDTO;
import com.example.service.OrderService;
import com.example.utils.JsonUtil;
import com.example.web.Form.OrderDetailForm;
import com.example.web.Form.OrderForm;
import com.example.web.VO.ResultVO;
import com.example.web.VO.orderInfo.OrderInfoVO;
import com.example.web.VO.orderInfo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjie_fourth on 2019/4/27.
 */
@Slf4j
@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "*", maxAge = 3600)
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private CarshopService carshopService;


    @PostMapping("/news")
    // 购物车端下订单
    public ResultVO<OrderDTO> createNewOrders(
            OrderForm orderForm
    ) {
        log.info("{}", orderForm);
        /*
         * 1、将Form数据转换为DTO数据
         * 2、交给service层处理
         * 3、修改购物车状态
         * */
        List<Carshop> carshopList = new ArrayList<>();
        OrderDTO orderDTO = new OrderDTO();
        // 1.1将list字符串转换为数组
        List<OrderDetailForm> list = JsonUtil.jsonListToList(orderForm.getOrderDeatils());

        // 1.2 OrderDetailForm 转换为 OrderDetail
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (OrderDetailForm form : list) {
            OrderDetail p = new OrderDetail();

            p.setProductId(form.getProductId());
            p.setProductQuantity(form.getProductQuantity());
            p.setProductPrice(form.getProductPrice());
            p.setProductName(form.getProductName());
            p.setProductIcon(form.getProductIcon());

            orderDetailList.add(p);

            // 获取修改购物车所需要的list数据
            Carshop item_carshop = new Carshop();
            item_carshop.setProductId(form.getProductId());
            item_carshop.setProductCount(form.getProductQuantity());
            item_carshop.setUserId(orderForm.getUserId());
            carshopList.add(item_carshop);
        }

        // 1.3 将 OrderForm 转换为 OrderDTO
        orderDTO.setLocationId(orderForm.getLocationId());
        orderDTO.setUserId(orderForm.getUserId());
        orderDTO.setUserOpenid(orderForm.getUserOpenid());
//        orderDTO.setOrderAmount(orderForm.getOrderAmount());
        // 这里将所有订单的总价设置为0.01元
        orderDTO.setOrderAmount(new BigDecimal(0.01));
        orderDTO.setOrderDetailList(orderDetailList);

        log.info("{}", orderDTO);

        // 2、
        OrderDTO saveOrder = orderService.saveOrder(orderDTO);

        // 3、修改购物车状态
        carshopService.updateAfterNewOrders(orderForm.getUserId(), carshopList);

        // 4、
        ResultVO<OrderDTO> resultVO = new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(saveOrder);

        return resultVO;
    }

    @PostMapping("/new")
    // 从单个商品端下单
    public ResultVO<OrderDTO> createNewOrder(
            OrderForm orderForm
    ) {
        log.info("{}", orderForm);
        /*
         * 1、将Form数据转换为DTO数据
         * 2、交给service层处理
         * */
        OrderDTO orderDTO = new OrderDTO();
        // 1.1将list字符串转换为数组
        List<OrderDetailForm> list = JsonUtil.jsonListToList(orderForm.getOrderDeatils());

        // 1.2 OrderDetailForm 转换为 OrderDetail
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (OrderDetailForm form : list) {
            OrderDetail p = new OrderDetail();

            p.setProductId(form.getProductId());
            p.setProductQuantity(form.getProductQuantity());
            p.setProductPrice(form.getProductPrice());
            p.setProductName(form.getProductName());
            p.setProductIcon(form.getProductIcon());

            orderDetailList.add(p);
        }

        // 1.3 将 OrderForm 转换为 OrderDTO
        orderDTO.setLocationId(orderForm.getLocationId());
        orderDTO.setUserId(orderForm.getUserId());
        orderDTO.setUserOpenid(orderForm.getUserOpenid());
//        orderDTO.setOrderAmount(orderForm.getOrderAmount());
        // 这里将所有订单的总价设置为0.01元
        orderDTO.setOrderAmount(new BigDecimal(0.01));
        orderDTO.setOrderDetailList(orderDetailList);

        log.info("{}", orderDTO);

        // 2、
        OrderDTO saveOrder = orderService.saveOrder(orderDTO);

        // 3、
        ResultVO<OrderDTO> resultVO = new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(saveOrder);

        return resultVO;
    }

    @GetMapping("/getAllOrders")
    public ResultVO<List<OrderVO>> getAllOrders(
            @RequestParam("userId") String userId
    ) {
        //1、获取userId下的所有订单信息
        List<OrderDTO> orderDTOList = orderService.getAllOrderDTOByUserId(userId);

        //2、转换数据
        List<OrderVO> data = new ArrayList<>();

        for (OrderDTO orderDTO : orderDTOList) {
            OrderVO orderVO = new OrderVO();

            orderVO.setOrderId(orderDTO.getOrderId());
            orderVO.setLocationId(orderDTO.getLocationId());
            orderVO.setOrderAmount(orderDTO.getOrderAmount());
            orderVO.setOrderStatus(orderDTO.getOrderStatus());
            orderVO.setPayStatus(orderDTO.getPayStatus());

            List<OrderInfoVO> list = new ArrayList<>();
            for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
                OrderInfoVO orderInfoVO = new OrderInfoVO();

                orderInfoVO.setProductName(orderDetail.getProductName());
                orderInfoVO.setProductIcon(orderDetail.getProductIcon());
                orderInfoVO.setProductPrice(orderDetail.getProductPrice());
                orderInfoVO.setProductQuantity(orderDetail.getProductQuantity());

                list.add(orderInfoVO);
            }
            orderVO.setProductList(list);

            data.add(orderVO);
        }

        //
        ResultVO<List<OrderVO>> resultVO = new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(data);

        return resultVO;
    }

    // 获取用户未完结的所有订单
    @GetMapping("/getAllUnfinishedOrders")
    public ResultVO<List<OrderVO>> getAllUnfinishedOrders(
            @RequestParam("userId") String userId
    ) {
        // 1、获取用户下所有未完结订单
        List<OrderDTO> orderDTOList = orderService.getAllUnfinishedOrdersByUserId(userId);

        //2、转换数据
        List<OrderVO> data = new ArrayList<>();

        for (OrderDTO orderDTO : orderDTOList) {
            OrderVO orderVO = new OrderVO();

            orderVO.setOrderId(orderDTO.getOrderId());
            orderVO.setLocationId(orderDTO.getLocationId());
            orderVO.setOrderAmount(orderDTO.getOrderAmount());
            orderVO.setOrderStatus(orderDTO.getOrderStatus());
            orderVO.setPayStatus(orderDTO.getPayStatus());

            List<OrderInfoVO> list = new ArrayList<>();
            for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
                OrderInfoVO orderInfoVO = new OrderInfoVO();

                orderInfoVO.setProductName(orderDetail.getProductName());
                orderInfoVO.setProductIcon(orderDetail.getProductIcon());
                orderInfoVO.setProductPrice(orderDetail.getProductPrice());
                orderInfoVO.setProductQuantity(orderDetail.getProductQuantity());

                list.add(orderInfoVO);
            }
            orderVO.setProductList(list);

            data.add(orderVO);
        }


        //
        ResultVO<List<OrderVO>> resultVO = new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(data);

        return resultVO;
    }

    // 获取用户所有完结状态的订单
    @GetMapping("/getAllFinishedOrders")
    public ResultVO<List<OrderVO>> getAllFinishedOrders(
            @RequestParam("userId") String userId
    ) {
        // 1、获取用户所有完结状态的订单
        List<OrderDTO> orderDTOList = orderService.getAllFinishedOrdersByUserId(userId);

        //2、转换数据
        List<OrderVO> data = new ArrayList<>();

        for (OrderDTO orderDTO : orderDTOList) {
            OrderVO orderVO = new OrderVO();

            orderVO.setOrderId(orderDTO.getOrderId());
            orderVO.setLocationId(orderDTO.getLocationId());
            orderVO.setOrderAmount(orderDTO.getOrderAmount());
            orderVO.setOrderStatus(orderDTO.getOrderStatus());
            orderVO.setPayStatus(orderDTO.getPayStatus());

            List<OrderInfoVO> list = new ArrayList<>();
            for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
                OrderInfoVO orderInfoVO = new OrderInfoVO();

                orderInfoVO.setProductName(orderDetail.getProductName());
                orderInfoVO.setProductIcon(orderDetail.getProductIcon());
                orderInfoVO.setProductPrice(orderDetail.getProductPrice());
                orderInfoVO.setProductQuantity(orderDetail.getProductQuantity());

                list.add(orderInfoVO);
            }
            orderVO.setProductList(list);

            data.add(orderVO);
        }

        //
        ResultVO<List<OrderVO>> resultVO = new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(data);

        return resultVO;
    }


}
