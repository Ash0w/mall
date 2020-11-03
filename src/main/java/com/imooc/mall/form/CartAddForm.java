package com.imooc.mall.form;

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
public class CartAddForm {
    @NotNull
    private Integer productId;
    private Boolean selected = true;
}
