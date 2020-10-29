package com.imooc.mall.vo;

import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xiaoyu
 * Date: 2020/10/29
 * Time: 12:28
 * Description: Be brave or be a loser
 */
@Data
public class CategoryVo {
    private Integer id;
    private Integer parentId;
    private String name;
    private Integer sortOrder;
    private List<CategoryVo> subCategories;

}
