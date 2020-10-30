package com.imooc.mall.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xiaoyu
 * Date: 2020/10/30
 * Time: 12:12
 * Description: Be brave or be a loser
 */
@Data
public class CartVo {
    private List<CartProductVo> cartProductVoList;
    private Boolean selectAll;
    private BigDecimal cartTotalPrice;
    private Integer cartTotalQuantity;
}
