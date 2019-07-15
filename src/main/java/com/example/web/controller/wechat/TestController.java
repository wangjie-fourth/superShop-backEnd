package com.example.web.controller.wechat;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;

/**
 * Created by wangjie_fourth on 2019/4/26.
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Autowired
    private WxMpService wxMpService;


    @GetMapping("/test")
    public String authorize(@RequestParam("returnUrl")String returnUrl){
        //1、配置
        //2、调用方法\
        String url = "http://wangjie.natappvip.cc/supershop/wechat/userInfo";
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAUTH2_SCOPE_USER_INFO, URLEncoder.encode(returnUrl));
        log.info("进入测试");

        return redirectUrl;
    }
}
