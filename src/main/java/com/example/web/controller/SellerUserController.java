package com.example.web.controller;

import com.example.domain.dataobject.OrderMaster;
import com.example.domain.dataobject.SellerInfo;
import com.example.service.OrderService;
import com.example.service.SellerInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by wangjie_fourth on 2019/5/10.
 */
@Slf4j
@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
public class SellerUserController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private SellerInfoService sellerInfoService;



    @GetMapping("toLogin")
    public ModelAndView ToLogin(
            Map<String,Object> map
    ){
        return new ModelAndView("logIn/login",map);
    }

    @PostMapping("/login")
    public ModelAndView login(
            @RequestParam("form-username")String username,
            @RequestParam("form-password")String password,
            Map<String,Object> map
    ){
        // 验证用户信息
        SellerInfo sellerInfo = sellerInfoService.findByUsernameAndPassword(username, password);
        if(sellerInfo == null){
            map.put("msg","登陆失败");
            map.put("url","/supershop/toLogin");
            return new ModelAndView("commons/error",map);
        }
        //
        Integer pageIndex = 1;
        Integer pageSize = 10;
        // 跳转到未支付状态的订单列表界面
        // 1、
        PageRequest pageRequest = new PageRequest(pageIndex-1,pageSize);
        Page<OrderMaster> orderMasterPage = orderService.getNoPayStatusOfOrderMaster(pageRequest);
        // 2、保存到map
        map.put("currentPage", pageIndex);
        map.put("size", pageSize);
        map.put("orderMasterPage",orderMasterPage);

        return new ModelAndView("order/AllOrderList",map);
    }
}
