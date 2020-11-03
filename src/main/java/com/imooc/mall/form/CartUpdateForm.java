package com.imooc.mall.form;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: xiaoyu
 * Date: 2020/11/3
 * Time: 12:18
 * Description: Be brave or be a loser
 *
 * @author xiaoyu
 */

@Data
public class CartUpdateForm {
    private Integer quantity;
    private Boolean selected;
}
