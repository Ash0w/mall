package com.imooc.mall.enums;

import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * User: xiaoyu
 * Date: 2020/10/22
 * Time: 0:23
 * Description: Be brave or be a loser
 */
@Getter
public enum ResposeEnum {
    ERROR(-1, "服务端错误"),
    SUCCESS(0, "成功"),
    PASSWORD_ERROR(1, "密码错误"),
    USERNAME_EXIST(2, "用户名已存在"),
    PARAM_ERROR(3, "参数错误"),
    EMAIL_EXIST(4, "邮箱已注册"),
    NEED_LOGIN(10, "用户未登录，请先登录"),
    USERNAME_OR_PASSWORD_ERROR(11, "用户名或密码错误"),
    ;
    Integer code;
    String desc;

    ResposeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
