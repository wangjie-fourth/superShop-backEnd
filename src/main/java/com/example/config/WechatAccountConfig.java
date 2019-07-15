package com.example.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信公共号信息配置对象
 * Created by wangjie_fourth on 2019/4/25.
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {

    /* 公共号的appid */
    private String mpAppId;

    /* 公众号的appSecret */
    private String mpAppSecret;

    /* 商户号 */
    private String mchId;

    /* 开放平台id*/
    private String openAppId;

    /* 开放平台密钥 */
    private String openAppSecret;

    /* 商户密钥 */
    private String mchKey;

    /* 商户证书路径 */
    private String KeyPath;

    /* 微信支付异步通知地址 */
    private String notifyUrl;
}
