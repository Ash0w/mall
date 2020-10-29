package com.imooc.mall.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: xiaoyu
 * Date: 2020/10/29
 * Time: 16:59
 * Description: Be brave or be a loser
 */
@Data
public class ProductVo {

    private Integer id;

    private Integer categoryId;

    private String name;

    private String subtitle;

    private String mainImage;

    private BigDecimal price;

    private Integer status;

}
