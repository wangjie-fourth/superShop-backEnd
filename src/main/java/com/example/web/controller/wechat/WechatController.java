package com.example.web.controller.wechat;

import com.example.domain.dataobject.ShopUser;
import com.example.service.ShopUserService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by wangjie_fourth on 2019/4/25.
 */
@Controller
@RequestMapping("/wechat")
@Slf4j
public class WechatController {
    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private ShopUserService shopUserService;

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl")String returnUrl){
        //1、配置
        //2、调用方法\
        String url = "http://wangjie.natappvip.cc/supershop/wechat/userInfo";
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAUTH2_SCOPE_USER_INFO, URLEncoder.encode(returnUrl));

        return "redirect:" + redirectUrl;
    }


    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,
                           @RequestParam("state") String returnUrl
    )  {
        // 网页授权
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("【微信网页授权】{}",e);
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();


        String access_toke = wxMpOAuth2AccessToken.getAccessToken();
        // 获取用户信息
        WxMpUser wxMpUser = null;
        try {
            wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        // 存储用户信息
        ShopUser shopUser = new ShopUser();
        shopUser.setUsername(wxMpUser.getNickname());
        shopUser.setPassword("暂无");
        shopUser.setUserOpenid(wxMpUser.getOpenId());
        shopUser.setUserIcon(wxMpUser.getHeadImgUrl());

        // 如果没有注册过，就在表中添加数据；如果注册过，就什么都不做
        ShopUser user = shopUserService.save(shopUser);

        log.info("{}",returnUrl +"#/goods/goodslist/0/" + openId);

        // 跳转到超市界面
        return "redirect:" + returnUrl +"#/goods/goodslist/0/" + openId;
    }

}
