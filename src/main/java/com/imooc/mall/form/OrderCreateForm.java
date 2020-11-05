package com.imooc.mall.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: xiaoyu
 * Date: 2020/11/5
 * Time: 10:55
 * Description: Be brave or be a loser
 */
@Data
public class OrderCreateForm {
    @NotNull
    private Integer shippingId;
}
