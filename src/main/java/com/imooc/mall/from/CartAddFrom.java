package com.imooc.mall.from;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 购物车添加商品
 * Created with IntelliJ IDEA.
 * User: xiaoyu
 * Date: 2020/10/30
 * Time: 12:28
 * Description: Be brave or be a loser
 */
@Data
public class CartAddFrom {
    @NotNull
    private Integer productId;
    private Boolean selected = true;
}
