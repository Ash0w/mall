package com.imooc.mall.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: xiaoyu
 * Date: 2020/10/30
 * Time: 12:17
 * Description: Be brave or be a loser
 */
@Data
public class CartProductVo {
    private Integer productId;
    /**
     * 购物车里的数量
     */
    private Integer quantity;
    private String productName;
    private String productSubtitle;
    private String productMainImage;
    private BigDecimal productPrice;
    private Integer productStatus;
    /**
     * 总价：数量x单价
     */
    private BigDecimal productTotalPrice;
    private Integer productStock;
    /**
     * 商品是否选中
     */
    private Boolean productSelected;
}
