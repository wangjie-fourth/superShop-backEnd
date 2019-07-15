package com.example.web.controller.wechat;

import com.example.domain.dataobject.ShopUser;
import com.example.service.ShopUserService;
import com.example.web.VO.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjie_fourth on 2019/4/28.
 */
@RestController
@RequestMapping("/shopUser")
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    @Autowired
    private ShopUserService userService;

    @GetMapping("/getUserInfo")
    public ResultVO<List<ShopUser>> getUserByOpenId(
            @RequestParam("openId")String openId
    ){
        log.info("获取用户信息");
        //
        ShopUser shopUser = userService.findByOpenId(openId);
        List<ShopUser> list = new ArrayList<>();
        list.add(shopUser);
        //
        ResultVO<List<ShopUser>> resultVO = new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(list);
        //
        return resultVO;
    }
}
