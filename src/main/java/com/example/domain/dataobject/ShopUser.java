package com.example.domain.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
/**
 * 用户信息
 * Created by wangjie_fourth on 2019/4/26.
 */
@Entity
@Data
public class ShopUser {
    /* 用户id */
    @Id
    private String userId;
    /* 用户名 */
    private String username;
    /* 密码 */
    private String password;
    /* 微信用户的openid */
    private String userOpenid;
    /* 用户微信头像 */
    private String userIcon;
}
