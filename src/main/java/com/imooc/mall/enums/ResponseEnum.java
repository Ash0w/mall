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
public enum ResponseEnum {
    /**
     * Error respose enum.
     */
    ERROR(-1, "服务端错误"),
    /**
     * Success respose enum.
     */
    SUCCESS(0, "成功"),
    /**
     * Password error respose enum.
     */
    PASSWORD_ERROR(1, "密码错误"),
    /**
     * Username exist respose enum.
     */
    USERNAME_EXIST(2, "用户名已存在"),
    /**
     * Param error respose enum.
     */
    PARAM_ERROR(3, "参数错误"),
    /**
     * Email exist respose enum.
     */
    EMAIL_EXIST(4, "邮箱已注册"),
    /**
     * Need login respose enum.
     */
    NEED_LOGIN(10, "用户未登录，请先登录"),
    /**
     * Username or password error respose enum.
     */
    USERNAME_OR_PASSWORD_ERROR(11, "用户名或密码错误"),
    /**
     * Product off sale or delete respose enum.
     */
    PRODUCT_OFF_SALE_OR_DELETE(12, "该商品已下架或删除"),

    PRODUCT_NOT_EXSIT(13, "商品不存在"),

    PRODUCT_STOCK_ERROR(14, "库存不正确"),

    CART_PRODUCT_NOT_EXSIT(15, "购物车里无该商品"),

    DELETE_SHIPPING_FAIL(16, "删除地址失败"),

    UPDATE_SHIPPING_FAIL(17, "更新地址失败"),

    ;
    /**
     * The Code.
     */
    Integer code;
    /**
     * The Desc.
     */
    String desc;

    ResponseEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
