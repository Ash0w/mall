package com.imooc.mall.service;

import com.imooc.mall.from.CartAddFrom;
import com.imooc.mall.vo.CartVo;
import com.imooc.mall.vo.ResponseVo;

public interface ICartService {
    ResponseVo<CartVo> add(Integer uid, CartAddFrom cartAddFrom);
}
