package com.imooc.mall.enums;


import lombok.Getter;

/**
 * @author xiaoyu
 */

@Getter
public enum ProductStatusEnum {
    //在售
    ON_SALE(1),
    //下架
    OFF_SALE(2),
    //删除
    DELETE(3),
    ;
    Integer code;

    ProductStatusEnum(Integer code) {
        this.code = code;
    }
}
