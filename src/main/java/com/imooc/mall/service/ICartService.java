package com.imooc.mall.service;

import com.imooc.mall.from.CartAddFrom;
import com.imooc.mall.from.CartUpdateFrom;
import com.imooc.mall.vo.CartVo;
import com.imooc.mall.vo.ResponseVo;

public interface ICartService {
    /**
     * 购物车-添加商品
     *
     * @param uid         购物车编号
     * @param cartAddFrom 前端的添加商品信息
     * @return 返回
     */
    ResponseVo<CartVo> add(Integer uid, CartAddFrom cartAddFrom);

    ResponseVo<CartVo> list(Integer uid);

    ResponseVo<CartVo> update(Integer uid, Integer productId, CartUpdateFrom cartUpdateFrom);

    ResponseVo<CartVo> delete(Integer uid, Integer productId);

    ResponseVo<CartVo> selectAll(Integer uid);

    ResponseVo<CartVo> unSelectAll(Integer uid);

    ResponseVo<Integer> sum(Integer uid);
}
