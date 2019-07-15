package com.example.web.VO;

import lombok.Data;

/**
 * http请求返回的最外层对象
 * Created by wangjie_fourth on 2019/4/14.
 */
@Data
public class ResultVO<T> {

    /* 错误码 */
    /*
    * code=0 表示 执行失败
    * code=1 表示 执行成功
    * */
    private Integer code;

    /* 提示信息 */
    /*
    * 失败时，返回相关错误信息
    * 成功时，返回“执行成功”
    * */
    private String msg;

    /* 具体内容 */
    /*
    * 失败时，无数据
    * 成功时，返回成功数据
    * */
    private T data;
}
