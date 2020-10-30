package com.imooc.mall.service;

import com.github.pagehelper.PageInfo;
import com.imooc.mall.vo.ProductDetailVo;
import com.imooc.mall.vo.ResponseVo;

/**
 * Created with IntelliJ IDEA.
 * User: xiaoyu
 * Date: 2020/10/29
 * Time: 17:12
 * Description: Be brave or be a loser
 */
public interface IProducrService {
    ResponseVo<PageInfo> list(Integer categoryId, Integer pageNum, Integer pageSize);

    ResponseVo<ProductDetailVo> detail(Integer productId);
}
